package com.jx.${bgMaple.controllerPackage}.controller;

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

import com.jx.${bgMaple.controllerPackage}.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.entity.${bgMaple.mapleEntityUpper};
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.${bgMaple.entityPackage}.service.${bgMaple.mapleEntityUpper}Service;

/** 
 * 类名称：${bgMaple.mapleControllerUpper}Controller
 * 创建人：maple
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value="/${bgMaple.controllerPackage}/${bgMaple.mapleCode}")
public class ${bgMaple.mapleControllerUpper}Controller extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "${bgMaple.controllerPackage}/${bgMaple.mapleCode}";
	
	@Resource(name="${bgMaple.mapleEntityLower}Service")
	private ${bgMaple.mapleEntityUpper}Service ${bgMaple.mapleEntityLower}Service;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		logBefore(logger, "列表${bgMaple.mapleEntityLower}");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgPage.setPd(pd);
			List<PageData>	${bgMaple.mapleEntityLower}List = ${bgMaple.mapleEntityLower}Service.listPage(bgPage);	//列出${bgMaple.mapleEntityLower}列表
			
			mv.addObject("${bgMaple.mapleEntityLower}List", ${bgMaple.mapleEntityLower}List);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}List");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		logBefore(logger, "去新增${bgMaple.mapleEntityLower}页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			mv.addObject("pathMsg", "add");
			mv.addObject("pd", pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Valid ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}, BindingResult result) throws Exception{
		logBefore(logger, "新增${bgMaple.mapleEntityLower}");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
	            return mv;  
	        }
			
			<#list bgMapleDetailList as bgMapleDetail>
				<#if bgMapleDetail.isEdit == '00'>
				<#if bgMapleDetail.mapleDetailType == '01'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>"${bgMapleDetail.defaultValue}"<#else>""</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '02'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '03'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '04'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
				</#if>
				</#if>
			</#list>
			
			${bgMaple.mapleEntityLower}Service.add(${bgMaple.mapleEntityLower});
			mv.addObject("msg","success");
		} catch (Exception e) {
			mv.addObject("msg","false");
			logger.error(e.toString(), e);
		}
		mv.setViewName("${bgMaple.controllerPackage}/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(<#list bgMapleDetailKeyList as bgMapleDetail>@RequestParam String ${bgMapleDetail.mapleDetailCode}<#if bgMapleDetail_has_next> ,</#if></#list>) throws Exception{
		logBefore(logger, "去修改${bgMaple.mapleEntityLower}页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = ${bgMaple.mapleEntityLower}Service.findById(<#list bgMapleDetailKeyList as bgMapleDetail>${bgMapleDetail.mapleDetailCode}<#if bgMapleDetail_has_next> ,</#if></#list>);	//根据ID读取
			mv.addObject("pathMsg", "edit");
			mv.addObject("${bgMaple.mapleEntityLower}", ${bgMaple.mapleEntityLower});
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Valid ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}, BindingResult result) throws Exception{
		logBefore(logger, "修改${bgMaple.mapleEntityLower}");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		try {
			pd = this.getPageData();
			Date nowTime = new Date();
			
			if(result.hasErrors()) {
				mv.setViewName("${bgMaple.controllerPackage}/${bgMaple.mapleCode}/${bgMaple.mapleControllerLower}Edit");
	            return mv;  
	        }
	        
	        <#list bgMapleDetailList as bgMapleDetail>
				<#if bgMapleDetail.isEdit == '00'>
				<#if bgMapleDetail.mapleDetailType == '01'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>"${bgMapleDetail.defaultValue}"<#else>""</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '02'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '03'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
				<#elseif bgMapleDetail.mapleDetailType == '04'>
			${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
				</#if>
				</#if>
			</#list>
	        
			${bgMaple.mapleEntityLower}Service.edit(${bgMaple.mapleEntityLower});
			mv.addObject("msg","success");
		} catch (Exception e) {
			mv.addObject("msg","false");
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("${bgMaple.controllerPackage}/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(<#list bgMapleDetailKeyList as bgMapleDetail>@RequestParam String ${bgMapleDetail.mapleDetailCode}<#if bgMapleDetail_has_next> ,</#if></#list>)throws Exception{
		logBefore(logger, "删除${bgMaple.mapleEntityLower}");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		PageData pd = new PageData();
		
		try{
			pd = this.getPageData();
			${bgMaple.mapleEntityLower}Service.deleteById(<#list bgMapleDetailKeyList as bgMapleDetail>${bgMapleDetail.mapleDetailCode}<#if bgMapleDetail_has_next> ,</#if></#list>);
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "false";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() {
		logBefore(logger, "批量删除${bgMaple.mapleEntityLower}");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			String ${bgMaple.mapleCode}Ids = pd.getString("${bgMaple.mapleCode}Ids");
			if(null != ${bgMaple.mapleCode}Ids && !"".equals(${bgMaple.mapleCode}Ids)){
				${bgMaple.mapleEntityLower}Service.batchDeleteByIds(${bgMaple.mapleCode}Ids.split(","));
				pd.put("msg", "success");
			}else{
				pd.put("msg", "false");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			pd.put("msg", "false");
		} 
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出${bgMaple.mapleEntityLower}到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
		<#list bgMapleDetailList as bgMapleDetail>
			titles.add("${bgMapleDetail.mapleDetailName}");	//${bgMapleDetail_index}
		</#list>
			dataMap.put("titles", titles);
			List<${bgMaple.mapleEntityUpper}> varOList = ${bgMaple.mapleEntityLower}Service.listByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
			<#list bgMapleDetailList as bgMapleDetail>
				vpd.put("var${bgMapleDetail_index}", varOList.get(i).get${(bgMapleDetail.mapleDetailCodeUpper)}());	//${bgMapleDetail_index}
			</#list>
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.addObject("pathObj","${bgMaple.mapleCode}");
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
		ModelAndView mv = new ModelAndView();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.isEdit == '01'>	
			titles.add("${bgMapleDetail.mapleDetailName}");	//${bgMapleDetail_index}
		</#if>
	</#list>
			dataMap.put("titles", titles);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
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
		try {
			Date nowTime = new Date();
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
				String fileName =  MapleFileUtil.fileUp(file, filePath, "userexcel");							//执行上传
				List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
				/*存入数据库操作======================================*/
				
				${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isEdit == '00'>
					<#if bgMapleDetail.mapleDetailType == '01'>
				${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>"${bgMapleDetail.defaultValue}"<#else>""</#if>);
					<#elseif bgMapleDetail.mapleDetailType == '02'>
				${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
					<#elseif bgMapleDetail.mapleDetailType == '03'>
				${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
					<#elseif bgMapleDetail.mapleDetailType == '04'>
				${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
					</#if>
					</#if>
				</#list>
				
				/**
				<#list bgMapleDetailList as bgMapleDetail>
					<#if bgMapleDetail.isEdit == '01'>	
				 * var${bgMapleDetail_index} :${bgMapleDetail.mapleDetailName};	//${bgMapleDetail_index}
					</#if>
				</#list>
				 */
				for(int i=0;i<listPd.size();i++){	
					<#list bgMapleDetailList as bgMapleDetail>
						<#if bgMapleDetail.isEdit == '01'>
						<#if bgMapleDetail.mapleDetailType == '01'>
					${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>"${bgMapleDetail.defaultValue}"<#else>""</#if>);
						<#elseif bgMapleDetail.mapleDetailType == '02'>
					${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
						<#elseif bgMapleDetail.mapleDetailType == '03'>
					${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
						<#elseif bgMapleDetail.mapleDetailType == '04'>
					${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
						</#if>
						</#if>
					</#list>
					${bgMaple.mapleEntityLower}Service.add(${bgMaple.mapleEntityLower});
				}
				/*存入数据库操作======================================*/
				mv.addObject("msg","success");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
