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
import com.jx.background.service.BgMapleService;

/** 
 * 类名称：BgMapleController
 * 创建人：maple
 * 创建时间：2016-12-30
 */
@Controller
@RequestMapping(value="/background/maple")
public class BgMapleController extends BaseController {
	
	@Resource(name="bgMapleService")
	private BgMapleService bgMapleService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgMaple");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgPage.setPd(pd);
			List<PageData>	varList = bgMapleService.listAllPd(bgPage);	//列出bgMaple列表
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			
			mv.setViewName("background/maple/bgMapleList");
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
		logBefore(logger, "去新增bgMaple页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleEdit");
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
		logBefore(logger, "新增bgMaple");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		bgMapleService.addByPd(pd);
		
		mv.addObject("msg","success");
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgMaple页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = bgMapleService.findPdByPd(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/maple/bgMapleEdit");
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
		logBefore(logger, "修改bgMaple");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		bgMapleService.editByPd(pd);
		mv.addObject("msg","success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除bgMaple");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgMapleService.deleteByPd(pd);
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
		logBefore(logger, "批量删除bgMaple");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String mapleIds = pd.getString("mapleIds");
			if(null != mapleIds && !"".equals(mapleIds)){
				bgMapleService.batchDeleteByIds(mapleIds);
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
		logBefore(logger, "导出bgMaple到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("代码生成 主键id");   //1
			titles.add("代号");	//0+2
			titles.add("名称");	//1+2
			titles.add("类型");	//2+2
			titles.add("控制器包代号");	//3+2
			titles.add("实体类包代号");	//4+2
			titles.add("控制器中的代号（大写）");	//5+2
			titles.add("控制器中的代号（小写）");	//6+2
			titles.add("实体类中的代号（大写）");	//7+2
			titles.add("实体类中的代号（小写）");	//8+2
			titles.add("数据表代号");	//9+2
			titles.add("排序编号");	//10+2
			titles.add("状态标识");	//11+2
			titles.add("有效标识");	//12+2
			titles.add("新增人员");	//13+2
			titles.add("新增时间");	//14+2
			titles.add("修改人员id");	//15+2
			titles.add("修改时间");	//16+2
			dataMap.put("titles", titles);
			List<BgMaple> varOList = bgMapleService.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getMapleId();
				vpd.put("var2", varOList.get(i).getMapleCode());	//2
				vpd.put("var3", varOList.get(i).getMapleName());	//3
				vpd.put("var4", varOList.get(i).getMapleType());	//4
				vpd.put("var5", varOList.get(i).getControllerPackage());	//5
				vpd.put("var6", varOList.get(i).getEntityPackage());	//6
				vpd.put("var7", varOList.get(i).getMapleControllerUpper());	//7
				vpd.put("var8", varOList.get(i).getMapleControllerLower());	//8
				vpd.put("var9", varOList.get(i).getMapleEntityUpper());	//9
				vpd.put("var10", varOList.get(i).getMapleEntityLower());	//10
				vpd.put("var11", varOList.get(i).getTableCode());	//11
				vpd.put("var12", varOList.get(i).getOrderNum());	//12
				vpd.put("var13", varOList.get(i).getStatus());	//13
				vpd.put("var14", varOList.get(i).getEffective());	//14
				vpd.put("var15", varOList.get(i).getCreateUserId());	//15
				vpd.put("var16", varOList.get(i).getCreateTime());	//16
				vpd.put("var17", varOList.get(i).getModifyUserId());	//17
				vpd.put("var18", varOList.get(i).getModifyTime());	//18
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
