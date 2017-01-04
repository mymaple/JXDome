package com.jx.background.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgMaple;
import com.jx.background.entity.BgMapleDetail;
import com.jx.background.entity.BgRights;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.Freemarker;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgMapleDetailService;
import com.jx.background.service.BgMapleService;
import com.jx.background.util.BgSessionUtil;

/** 
 * 类名称：BgMapleDetailController
 * 创建人：maple
 * 创建时间：2016-12-30
 */
@Controller
@RequestMapping(value="/background/mapleDetail")
public class BgMapleDetailController extends BaseController {
	
	@Resource(name="bgMapleDetailService")
	private BgMapleDetailService bgMapleDetailService;
	@Resource(name="bgMapleService")
	private BgMapleService bgMapleService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgMapleDetail");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String mapleId = pd.getString("mapleId");
			BgMaple bgMaple = bgMapleService.findById(Integer.parseInt(mapleId));
			bgPage.setPd(pd);
			List<PageData>	bgMapleDetailList = bgMapleDetailService.listPage(bgPage);	//列出bgMapleDetail列表
			
			mv.addObject("bgMaple", bgMaple);
			mv.addObject("bgMapleDetailList", bgMapleDetailList);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			
			mv.setViewName("background/maple/bgMapleDetailList");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(){
		logBefore(logger, "去新增bgMapleDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleDetailEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add() throws Exception{
		logBefore(logger, "新增bgMapleDetail");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("mapleDetailCodeUpper", MapleStringUtil.firstToUpper(pd.getString("mapleDetailCode")));
		bgMapleDetailService.addByPd(pd);
		
		mv.addObject("msg","success");
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgMapleDetail页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = bgMapleDetailService.findPdByPd(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleDetailEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改bgMapleDetail");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		bgMapleDetailService.editByPd(pd);
		mv.addObject("msg","success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String mapleDetailId)throws Exception{
		logBefore(logger, "删除bgUser");
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		try{
			bgMapleDetailService.deleteByPd(pd);
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
	@RequestMapping(value="/batchDelete")
	@ResponseBody
	public Object batchDelete() {
		logBefore(logger, "批量删除bgMapleDetail");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String mapleDetailIds = pd.getString("mapleDetailIds");
			if(null != mapleDetailIds && !"".equals(mapleDetailIds)){
				bgMapleDetailService.batchDeleteByIds(mapleDetailIds);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出bgMapleDetail到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("代码生成详细 主键id");   //1
			titles.add("属性代号");	//0+2
			titles.add("属性名称");	//1+2
			titles.add("属性类型");	//2+2
			titles.add("长度");	//3+2
			titles.add("小数长度");	//4+2
			titles.add("是否录入");	//5+2
			titles.add("是否null");	//6+2
			titles.add("默认值");	//7+2
			titles.add("排序编号");	//8+2
			titles.add("状态标识");	//9+2
			titles.add("有效标识");	//10+2
			titles.add("创建人员id");	//11+2
			titles.add("创建时间");	//12+2
			titles.add("修改人员id");	//13+2
			titles.add("修改时间");	//14+2
			dataMap.put("titles", titles);
			List<BgMapleDetail> varOList = bgMapleDetailService.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getMapleDetailId());
				vpd.put("var2", varOList.get(i).getMapleDetailCode());	//2
				vpd.put("var3", varOList.get(i).getMapleDetailName());	//3
				vpd.put("var4", varOList.get(i).getMapleDetailType());	//4
				vpd.put("var5", varOList.get(i).getLength());	//5
				vpd.put("var6", varOList.get(i).getDecimalLength());	//6
				vpd.put("var7", varOList.get(i).getIsEdit());	//7
				vpd.put("var8", varOList.get(i).getIsNull());	//8
				vpd.put("var9", varOList.get(i).getDefaultValue());	//9
				vpd.put("var10", varOList.get(i).getOrderNum());	//10
				vpd.put("var11", varOList.get(i).getStatus());	//11
				vpd.put("var12", varOList.get(i).getEffective());	//12
				vpd.put("var13", varOList.get(i).getCreateUserId());	//13
				vpd.put("var14", varOList.get(i).getCreateTime());	//14
				vpd.put("var15", varOList.get(i).getModifyUserId());	//15
				vpd.put("var16", varOList.get(i).getModifyTime());	//16
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	
	/**
	 * 生成代码
	 */
	@RequestMapping(value="/toCreateCode")
	public void toCreateCode(HttpServletResponse response)throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		String mapleId = pd.getString("mapleId");
		BgMaple bgMaple = bgMapleService.findById(Integer.parseInt(mapleId));
		List<BgMapleDetail>	bgMapleDetailList = bgMapleDetailService.listAllByPd(pd);
		List<BgMapleDetail> bgMapleDetailKeyList = new ArrayList<BgMapleDetail>();
		for(int i = 0;i<bgMapleDetailList.size();i++){
			if("01".equals(bgMapleDetailList.get(i).getIsKey()))
				bgMapleDetailKeyList.add(bgMapleDetailList.get(i));
		}

		Map<String, Object> root = new HashMap<String, Object>(); // 创建数据模型
		
		root.put("bgMaple", bgMaple);
		root.put("bgMapleDetailList", bgMapleDetailList);
		root.put("bgMapleDetailKeyList", bgMapleDetailKeyList);
		root.put("nowDate", new Date()); // 当前日期

		MapleFileUtil.delFolder(PathUtil.getClasspath() + "admin/ftl"); // 生成代码前,先清空之前生成的代码
		/* ============================================================================================= */

		String filePath = "admin/ftl/code/"; // 存放路径
		String ftlPath = "createCode"; // ftl路径

//		/* 生成controller */
		Freemarker.printFile("controllerTemplate.ftl", root, bgMaple.getControllerPackage() + "/controller/" + bgMaple.getMapleControllerUpper() + "Controller.java", filePath, ftlPath);
//
		/* 生成service */
		Freemarker.printFile("serviceTemplate.ftl", root, bgMaple.getEntityPackage() + "/service/" + bgMaple.getMapleEntityUpper() + "Service.java", filePath, ftlPath);

		/* 生成entity */
		Freemarker.printFile("entityTemplate.ftl", root, bgMaple.getEntityPackage() + "/entity/" + bgMaple.getMapleEntityUpper() + ".java", filePath, ftlPath);
		
//		/* 生成mybatis xml Mysql*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis/" + bgMaple.getEntityPackage() + "/" + bgMaple.getMapleEntityUpper() + "Mapper.xml", filePath, ftlPath);
//		/* 生成mybatis xml Oracle*/
//		//Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/" + packageName + "/" + objectName + "Mapper.xml", filePath, ftlPath);
//
//		/* 生成SQL脚本 Mysql*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/" + bgMaple.getMapleEntityUpper() + ".sql", filePath, ftlPath);
//		
		/* 生成SQL脚本 Oracle*/
		//Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/" + tabletop + objectName.toUpperCase() + ".sql", filePath, ftlPath);

		/* 生成jsp页面 */
		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/" + bgMaple.getControllerPackage() + bgMaple.getMapleCode() + "/" + bgMaple.getMapleControllerLower() + "List.jsp", filePath, ftlPath);
		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/" + bgMaple.getControllerPackage() + bgMaple.getMapleCode() + "/" + bgMaple.getMapleControllerLower() + "Edit.jsp", filePath, ftlPath);

		/* 生成说明文档 */
//		Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);

		// this.print("oracle_SQL_Template.ftl", root); 控制台打印

		/* 生成的全部代码压缩成zip文件 */
		MapleFileUtil.zip(PathUtil.getClasspath() + "admin/ftl/code", PathUtil.getClasspath() + "admin/ftl/code.zip");

		/* 下载代码 */
		MapleFileUtil.fileDownload(response, PathUtil.getClasspath() + "admin/ftl/code.zip", bgMaple.getMapleCode()+"Code.zip");
	}
	
}
