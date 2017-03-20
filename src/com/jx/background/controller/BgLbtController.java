package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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
import com.jx.common.entity.ComLbt;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComLbtService;

/** 
 * 类名称：BgLbtController
 * 创建人：maple
 * 创建时间：2017-03-18
 */
@Controller
@RequestMapping(value="/background/lbt")
public class BgLbtController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_lbt";
	
	@Resource(name="comLbtService")
	private ComLbtService comLbtService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comLbtList = comLbtService.listPage(bgPage);	//列出comLbt列表
		
		mv.addObject("comLbtList", comLbtList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/lbt/bgLbtList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComLbt comLbt = new ComLbt();
		comLbt.setLbtCode("");
		comLbt.setLbtType("01");
		comLbt.setLbtImgSrc("");
		comLbt.setDescription("");
		comLbt.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comLbt);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/lbt/bgLbtEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComLbt comLbt, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comLbt");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComLbt> comLbtList = comLbtService.otherHaveCode("", comLbt.getLbtCode());
		if(MapleUtil.notEmptyList(comLbtList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comLbtService.add(comLbt);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String lbtId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComLbt comLbt = comLbtService.findById(lbtId);	//根据ID读取
		if(comLbt == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comLbt);
		resultInfo.setResultCode("success");
		mv.setViewName("background/lbt/bgLbtEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComLbt comLbt, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comLbt");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComLbt> comLbtList = comLbtService.otherHaveCode(comLbt.getLbtId(), comLbt.getLbtCode());	
		if(MapleUtil.notEmptyList(comLbtList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comLbtService.edit(comLbt);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String lbtId, @RequestParam String lbtCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComLbt> comLbtList = comLbtService.otherHaveCode(lbtId, lbtCode);	
		if(MapleUtil.emptyList(comLbtList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String lbtId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comLbtService.deleteById(lbtId);	//根据ID删除
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
		comLbtService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("轮播图 主键id");		//0
		titles.add("轮播图代号");	//1
		titles.add("轮播图类型");	//2
		titles.add("轮播图状态");	//3
		titles.add("轮播图图片");	//4
		titles.add("描述");	//5
		titles.add("排序编号");	//6
		titles.add("有效标志");	//7
		titles.add("创建人员id");	//8
		titles.add("创建时间");	//9
		titles.add("修改人员id");	//10
		titles.add("修改时间");	//11
		dataMap.put("titles", titles);
		List<ComLbt> varOList = comLbtService.list(null);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getLbtId());			//0
			vpd.put("var1", varOList.get(i).getLbtCode());	//1
			vpd.put("var2", varOList.get(i).getLbtType());	//2
			vpd.put("var3", varOList.get(i).getLbtStatus());	//3
			vpd.put("var4", varOList.get(i).getLbtImgSrc());	//4
			vpd.put("var5", varOList.get(i).getDescription());	//5
			vpd.put("var6", varOList.get(i).getOrderNum());		//6
			vpd.put("var7", varOList.get(i).getEffective());	//7
			vpd.put("var8", varOList.get(i).getCreateUserId());	//8
			vpd.put("var9", varOList.get(i).getCreateTime());	//9
			vpd.put("var10", varOList.get(i).getModifyUserId());//10
			vpd.put("var11", varOList.get(i).getModifyTime());	//11
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		mv.addObject("controllerPath", "background_lbt");
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
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("轮播图代号");	//0
		titles.add("轮播图类型");	//1
		titles.add("轮播图图片");	//2
		titles.add("描述");	//3
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "lbtexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComLbt comLbt = new ComLbt();
				
		/**
		 * var0 :轮播图代号;	//0
		 * var1 :轮播图类型;	//1
		 * var2 :轮播图图片;	//2
		 * var3 :描述;	//3
		 */
		for(int i=0;i<listPd.size();i++){	
			comLbt.setLbtId(this.get32UUID());
			comLbt.setLbtCode(listPd.get(i).getString("var0"));
			comLbt.setLbtType(listPd.get(i).getString("var1"));
			comLbt.setLbtImgSrc(listPd.get(i).getString("var2"));
			comLbt.setDescription(listPd.get(i).getString("var3"));
			comLbtService.add(comLbt);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
