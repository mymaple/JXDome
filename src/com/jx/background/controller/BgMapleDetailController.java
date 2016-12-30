package com.jx.background.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.Jurisdiction;
import com.jx.common.util.ObjectExcelView;
import com.jx.background.service.BgMapleDetailService;

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
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgMapleDetail");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgPage.setPd(pd);
			List<PageData>	varList = bgMapleDetailService.listAllPd(bgPage);	//列出bgMapleDetail列表
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			
			mv.setViewName("background/mapleDetail/bgMapleDetailList");
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
			
			mv.setViewName("background/mapleDetail/bgMapleDetailEdit");
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
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

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
			
			mv.setViewName("background/mapleDetail/bgMapleDetailEdit");
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
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
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
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除bgMapleDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgMapleDetailService.deleteByPd(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDelete")
	@ResponseBody
	public Object batchDelete() {
		logBefore(logger, "批量删除bgMapleDetail");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
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
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
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
				
				vpd.put("var1",varOList.get(i).getMapleDetailId();
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
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
