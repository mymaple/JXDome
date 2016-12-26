package com.jx.background.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgMenu;
import com.jx.background.service.BgMenuService;
import com.jx.background.util.BgSessionUtil;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.ObjectExcelView;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgMenuController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/menu")
public class BgMenuController extends BaseController {
	
	
	/**
	 * 后台 菜单标记名称(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/menu";
	
	@Resource(name="bgMenuService")
	private BgMenuService bgMenuService;
	
	
	
	/**
	 * 显示菜单列表ztree(菜单管理)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			JSONArray arr = JSONArray.fromObject(bgMenuService.listAllMenuInRank(0,"1"));
			String json = arr.toString();
			json = json.replaceAll("menuId", "id").replaceAll("parentId", "pId")
					.replaceAll("menuName", "name").replaceAll("subBgMenuList", "nodes")
					.replaceAll("hasRight", "checked").replaceAll("menuUrl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.setViewName("background/menu/bgMenuMain");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgMenu");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String menuId = MapleStringUtil.isEmpty(pd.getString("menuId"))?"0":pd.getString("menuId");
			menuId = MapleStringUtil.isEmpty(pd.getString("id"))?menuId:pd.getString("id");
			
			pd.put("menuId", menuId);
			bgPage.setPd(pd);
			List<PageData> subBgMenuList = bgMenuService.listPage(bgPage);
			
			mv.addObject("subBgMenuList", subBgMenuList);
			mv.addObject("menuId", menuId);
			mv.addObject("msg", MapleStringUtil.isEmpty(pd.getString("msg"))?"list":pd.getString("msg")); //MSG=change 则为编辑或删除后跳转过来的
			mv.addObject("pd", bgMenuService.findPdByPd(pd));//父菜单
			mv.addObject("RIGHTS",BgSessionUtil.getSessionBgRights());	//按钮权限
			
			
			mv.setViewName("background/menu/bgMenuList");
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
		logBefore(logger, "去新增bgMenu页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String menuId = MapleStringUtil.isEmpty(pd.getString("menuId"))?"0":pd.getString("menuId");
			mv.addObject("parentBgMenu", bgMenuService.findPdByPd(pd));	//传入父菜单所有信息
			mv.addObject("menuId", menuId);
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/menu/bgMenuEdit");
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
		logBefore(logger, "新增bgMenu");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			pd.put("menuIcon","menu-icon fa fa-leaf black");//默认菜单图标
			bgMenuService.addByPd(pd); //保存菜单
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("redirect:list?msg='change'&menuId="+pd.get("parentId")); //保存成功跳转到列表页面
//		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgMenu页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = bgMenuService.findPdByPd(pd);	//根据ID读取
			int menuId = Integer.parseInt(pd.get("parentId").toString());
			mv.addObject("parentBgMenu", bgMenuService.findById(menuId));	//传入父菜单所有信息
			mv.addObject("menuId", menuId);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/menu/bgMenuEdit");
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
		logBefore(logger, "修改bgMenu");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			bgMenuService.editByPd(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("redirect:list?msg='change'&menuId="+pd.get("parentId")); //保存成功跳转到列表页面
//		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String menuId)throws Exception{
		logBefore(logger, "删除bgMenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		try{
			
			if(bgMenuService.listSubBgMenuByParentId(Integer.parseInt(menuId)).size() > 0){//判断是否有子菜单，是：不允许删除
				errInfo = "false";
			}else{
				bgMenuService.deleteByPd(pd);
				errInfo = "success";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
		
//		try{
//			bgMenuService.deleteByPd(pd);
//			out.write("success");
//			out.close();
//		} catch(Exception e){
//			logger.error(e.toString(), e);
//		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDelete")
	@ResponseBody
	public Object batchDelete() {
		logBefore(logger, "批量删除bgMenu");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String menuIds = pd.getString("menuIds");
			if(null != menuIds && !"".equals(menuIds)){
				bgMenuService.batchDeleteByIds(menuIds);
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
		logBefore(logger, "导出bgMenu到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("后台菜单表 主键id");   //1
			titles.add("菜单名称");	//0+2
			titles.add("菜单链接");	//1+2
			titles.add("上级id");	//2+2
			titles.add("菜单排序");	//3+2
			titles.add("菜单图标");	//4+2
			titles.add("菜单类型");	//5+2
			titles.add("状态");	//6+2
			titles.add("修改时间");	//7+2
			dataMap.put("titles", titles);
			List<BgMenu> varOList = bgMenuService.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getMenuId());
				vpd.put("var2", varOList.get(i).getMenuName());	//2
				vpd.put("var3", varOList.get(i).getMenuUrl());	//3
				vpd.put("var4", varOList.get(i).getParentId());	//4
				vpd.put("var5", varOList.get(i).getMenuOrder());	//5
				vpd.put("var6", varOList.get(i).getMenuIcon());	//6
				vpd.put("var7", varOList.get(i).getMenuType());	//7
				vpd.put("var8", varOList.get(i).getStatus());	//8
				vpd.put("var9", varOList.get(i).getModifyTime());	//9
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
/*	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}*/
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	
	/**
	 * 请求编辑菜单图标页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toChangeMenuIcon")
	public ModelAndView toChangeMenuIcon()throws Exception{
		if(JudgeRightsUtil.getRights(RIGHTS_BG_MENUCODE_STR).isEdit()){
			return null;
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			mv.addObject("pd", pd);
			mv.setViewName("background/menu/bgMenuIcon");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 保存菜单图标 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/changeMenuIcon")
	public ModelAndView changeMenuIcon()throws Exception{
		if(JudgeRightsUtil.getRights(RIGHTS_BG_MENUCODE_STR).isEdit()){
			return null;
		}
		logBefore(logger, "修改菜单图标");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd = bgMenuService.changeMenuIcon(pd);
			mv.addObject("msg","success");
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
