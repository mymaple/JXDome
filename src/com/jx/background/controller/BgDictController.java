package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationAdd;
import com.jx.common.config.BaseEntity.ValidationEdit;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComDict;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComDictService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgDictController
 * 创建人：maple
 * 创建时间：2017-01-10
 */
@Controller
@RequestMapping(value="/background/dict")
public class BgDictController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/dict";
	
	@Resource(name="comDictService")
	private ComDictService comDictService;
	
	
	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		JSONArray arr = JSONArray.fromObject(comDictService.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("dictId", "id").replaceAll("parentId", "pId")
				.replaceAll("dictName", "name").replaceAll("subComDictList", "nodes")
				.replaceAll("hasDict", "checked").replaceAll("subComDictPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
		mv.addObject("pd", pd);
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgMainTree");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData> comDictList = comDictService.listPage(bgPage);	//列出comDict列表
			
		mv.addObject("comDictList", comDictList);
		mv.addObject("parentComDict", comDictService.findById(pd.getString("pId")));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/dict/bgDictList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id	
		ComDict parentComDict = comDictService.findById(parentId);
		if(!"0".equals(parentId) && parentComDict==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComDict comDict = new ComDict();
		comDict.setParentId(parentId);
		comDict.setDictCode("");
		comDict.setDictName("");
		comDict.setDictType("01");
		comDict.setDictValue("");
		comDict.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comDict);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/dict/bgDictEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComDict comDict, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comDict");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comDict.getParentId();
		ComDict parentComDict = comDictService.findById(parentId);
		if(!"0".equals(parentId) && parentComDict==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComDict> comDictList = comDictService.otherHaveCode("", comDict.getDictCode());
		if(MapleUtil.notEmptyList(comDictList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comDictService.add(comDict);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String dictId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComDict comDict = comDictService.findById(dictId);	//根据ID读取
		if(comDict == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comDict);
		resultInfo.setResultCode("success");
		mv.setViewName("background/dict/bgDictEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComDict comDict, BindingResult result) throws Exception{
		logBefore(logger, "修改comDict");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comDict");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComDict> comDictList = comDictService.otherHaveCode(comDict.getDictId(), comDict.getDictCode());	
		if(MapleUtil.notEmptyList(comDictList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comDictService.edit(comDict);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String dictId, @RequestParam String dictCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComDict> comDictList = comDictService.otherHaveCode(dictId, dictCode);	
		if(MapleUtil.emptyList(comDictList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String dictId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comDictService.deleteInRank(dictId);	//根据ID删除
		resultInfo.setResultCode("success");

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		String ids = pd.getString("ids");
		if(MapleStringUtil.isEmpty(ids)){
			return AppUtil.returnResult(pd, resultInfo);
		}
		comDictService.batchDeleteInRank(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/toExportExcel")
	public ModelAndView toExportExcel() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("数据字典 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("数据字典代号");	//2
		titles.add("数据字典名称");	//3
		titles.add("数据字典类型");	//4
		titles.add("数据字典状态");	//5
		titles.add("数据字典值");	//6
		titles.add("级别");	//7
		titles.add("排序编号");	//8
		titles.add("有效标志");	//9
		titles.add("创建人员id");	//10
		titles.add("创建时间");	//11
		titles.add("修改人员id");	//12
		titles.add("修改时间");	//13
		dataMap.put("titles", titles);
		List<ComDict> varOList = comDictService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getDictId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getDictCode());	//2
			vpd.put("var3", varOList.get(i).getDictName());	//3
			vpd.put("var4", varOList.get(i).getDictType());	//4
			vpd.put("var5", varOList.get(i).getDictStatus());	//5
			vpd.put("var6", varOList.get(i).getDictValue());	//6
			vpd.put("var7", varOList.get(i).getLevel());	//7
			vpd.put("var8", varOList.get(i).getOrderNum());		//8
			vpd.put("var9", varOList.get(i).getEffective());	//9
			vpd.put("var10", varOList.get(i).getCreateUserId());	//10
			vpd.put("var11", varOList.get(i).getCreateTime());	//11
			vpd.put("var12", varOList.get(i).getModifyUserId());//12
			vpd.put("var13", varOList.get(i).getModifyTime());	//13
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView toUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
			
		ComDict parentComDict = comDictService.findById(pId);
		if(!"0".equals(pId) && parentComDict==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
		mv.setViewName("background/bgUploadExcel");

		mv.addObject(resultInfo);					
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcelModel")
	public ModelAndView downExcelModel()throws Exception{
		logBefore(logger, "导出bgUser到excel");
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("数据字典代号");	//0
		titles.add("数据字典名称");	//1
		titles.add("数据字典类型");	//2
		titles.add("数据字典值");	//3
		dataMap.put("titles", titles);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
		
	
	/**从EXCEL导入到数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/uploadExcel")
	public ModelAndView uploadExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "dictexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComDict comDict = new ComDict();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		comDict.setParentId(pId);
				
		/**
		 * var0 :数据字典代号;	//0
		 * var1 :数据字典名称;	//1
		 * var2 :数据字典类型;	//2
		 * var3 :数据字典值;	//3
		 */
		for(int i=0;i<listPd.size();i++){	
			comDict.setDictId(this.get32UUID());
			comDict.setDictCode(listPd.get(i).getString("var0"));
			comDict.setDictName(listPd.get(i).getString("var1"));
			comDict.setDictType(listPd.get(i).getString("var2"));
			comDict.setDictValue(listPd.get(i).getString("var3"));
			comDictService.add(comDict);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
