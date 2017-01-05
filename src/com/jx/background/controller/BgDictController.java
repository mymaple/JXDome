package com.jx.background.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComDict;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComDictService;

/** 
 * 类名称：BgDictController
 * 创建人：maple
 * 创建时间：2017-01-05
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
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		logBefore(logger, "列表comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try{
			bgPage.setPd(pd);
			List<PageData>	comDictList = comDictService.listPage(bgPage);	//列出comDict列表
			
			mv.addObject("comDictList", comDictList);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("列表失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);
		mv.setViewName("background/dict/bgDictList");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		logBefore(logger, "去新增comDict页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try {
			mv.addObject("pathMsg", "add");
			mv.addObject("pd", pd);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("去新增页面失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);					
		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Valid ComDict comDict, BindingResult result) throws Exception{
		logBefore(logger, "新增comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try {
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("background/bgSaveResult");
	            return mv;  
	        }
			
			comDict.setDictId(this.get32UUID());
			comDict.setParentId("");
			comDict.setDictStatus("00");
			comDict.setLevel(0);
			comDict.setOrderNum("");
			comDict.setEffective("01");
			comDict.setCreateUserId("");
			comDict.setCreateTime(nowTime);
			comDict.setModifyUserId("");
			comDict.setModifyTime(nowTime);
			
			comDictService.add(comDict);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("新增失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String dictId, @RequestParam String parentId) throws Exception{
		logBefore(logger, "去修改comDict页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try {
			ComDict comDict = comDictService.findById(dictId, parentId);	//根据ID读取
			mv.addObject("pathMsg", "edit");
			mv.addObject(comDict);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("去修改页面失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);						
		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Valid ComDict comDict, BindingResult result) throws Exception{
		logBefore(logger, "修改comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try {
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("background/bgSaveResult");
	            return mv;  
	        }
	        
			comDict.setParentId("");
			comDict.setDictStatus("00");
			comDict.setLevel(0);
			comDict.setOrderNum("");
			comDict.setEffective("01");
			comDict.setCreateUserId("");
			comDict.setCreateTime(nowTime);
			comDict.setModifyUserId("");
			comDict.setModifyTime(nowTime);
	        
			comDictService.edit(comDict);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("编辑失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String dictId, @RequestParam String parentId) throws Exception{
		logBefore(logger, "删除comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try{
			comDictService.deleteById(dictId, parentId);	//根据ID删除
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("删除失败");
			logger.error(e.toString(), e);
		}
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() {
		logBefore(logger, "批量删除comDict");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try {
			String ids = pd.getString("ids");
			
			if(null != ids && !"".equals(ids)){
				comDictService.batchDeleteByIds(ids.split(","));	//根据ID删除
				pd.put("msg", "success");
			}else{
				pd.put("msg", "false");
			}
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("批量删除失败");
			logger.error(e.toString(), e);
		}
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/toExportExcel")
	public ModelAndView toExportExcel(){
		logBefore(logger, "导出comDict到excel");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("数据字典 主键id");
			titles.add("上级id");	//1
			titles.add("数据字典代号");	//2
			titles.add("数据字典名称");	//3
			titles.add("数据字典类型");	//4
			titles.add("数据字典状态");	//5
			titles.add("数据字典值");	//6
			titles.add("级别");	//7
			titles.add("排序编号");	//8
			titles.add("有效性");	//9
			titles.add("创建人员id");	//10
			titles.add("创建时间");	//11
			titles.add("修改人员id");	//12
			titles.add("修改时间");	//13
			dataMap.put("titles", titles);
			List<ComDict> varOList = comDictService.listByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var0",varOList.get(i).getDictId());
				vpd.put("var1", varOList.get(i).getParentId());	//1
				vpd.put("var2", varOList.get(i).getDictCode());	//2
				vpd.put("var3", varOList.get(i).getDictName());	//3
				vpd.put("var4", varOList.get(i).getDictType());	//4
				vpd.put("var5", varOList.get(i).getDictStatus());	//5
				vpd.put("var6", varOList.get(i).getDictValue());	//6
				vpd.put("var7", varOList.get(i).getLevel());	//7
				vpd.put("var8", varOList.get(i).getOrderNum());	//8
				vpd.put("var9", varOList.get(i).getEffective());	//9
				vpd.put("var10", varOList.get(i).getCreateUserId());	//10
				vpd.put("var11", varOList.get(i).getCreateTime());	//11
				vpd.put("var12", varOList.get(i).getModifyUserId());	//12
				vpd.put("var13", varOList.get(i).getModifyTime());	//13
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("列表失败");
			logger.error(e.toString(), e);
		}
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
		mv.addObject("pathObj","dict");
		mv.setViewName("background/bgUploadExcel");
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
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("数据字典代号");	//1
			titles.add("数据字典名称");	//2
			titles.add("数据字典类型");	//3
			titles.add("数据字典值");	//5
			dataMap.put("titles", titles);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("下载模版失败");
			logger.error(e.toString(), e);
		}
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
		try {
			Date nowTime = new Date();
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
				String fileName =  MapleFileUtil.fileUp(file, filePath, "dictexcel");		//执行上传
				List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
				/*存入数据库操作======================================*/
				
				ComDict comDict = new ComDict();
				comDict.setParentId("");
				comDict.setDictStatus("00");
				comDict.setLevel(0);
				comDict.setOrderNum("");
				comDict.setEffective("01");
				comDict.setCreateUserId("");
				comDict.setCreateTime(nowTime);
				comDict.setModifyUserId("");
				comDict.setModifyTime(nowTime);
				
				/**
				 * var1 :数据字典代号;	//1
				 * var2 :数据字典名称;	//2
				 * var3 :数据字典类型;	//3
				 * var5 :数据字典值;	//5
				 */
				for(int i=0;i<listPd.size();i++){	
					comDict.setDictId(this.get32UUID());
					comDict.setParentId(this.get32UUID());
					comDict.setDictCode(listPd.get(i).getString("var0"));
					comDict.setDictName(listPd.get(i).getString("var1"));
					comDict.setDictType(listPd.get(i).getString("var2"));
					comDict.setDictValue(listPd.get(i).getString("var3"));
					comDictService.add(comDict);
				}
				/*存入数据库操作======================================*/
				mv.addObject("msg","success");
			}
			resultInfo.setResultCode("success");
		} catch(Exception e){
			resultInfo.setResultCode("false");
			resultInfo.setResultContent("EXCEL导入失败");
			logger.error(e.toString(), e);
		}
		mv.addObject(resultInfo);
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
