package com.jx.background.controller;

import java.math.BigInteger;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.RightsHelper;
import com.jx.common.util.Tools;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgRoleController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/role")
public class BgRoleController extends BaseController {
	
	@Resource(name="bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name="bgUserService")
	private BgUserService bgUserService;
	@Resource(name="bgMenuService")
	private BgMenuService bgMenuService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgRole");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String roleId = (null == pd.get("roleId") || "".equals(pd.getString("roleId")))?"1":pd.getString("roleId");
			pd.put("roleId", roleId);
			List<BgRole> subBgRoleList = bgRoleService.listSubBgRoleByParentId(Integer.parseInt(roleId));
			List<BgRole> topBgRoleList = bgRoleService.listSubBgRoleByParentId(0);
			
			mv.addObject("subBgRoleList", subBgRoleList);
			mv.addObject("topBgRoleList", topBgRoleList);
			
//			bgPage.setPd(pd);
//			List<PageData>	varList = bgRoleService.listAllPd(bgPage);	//列出bgRole列表
//			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
//			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			mv.addObject("BGQX", pd);
			
			mv.setViewName("background/role/bgRoleList");
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
		logBefore(logger, "去新增bgRole页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/role/bgRoleEdit");
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
		logBefore(logger, "新增bgRole");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			int parentId = Integer.parseInt(pd.getString("parentId"));
			String roleName = pd.getString("roleName");
			BgRole bgRole = new BgRole();
			
			bgRole.setParentId(parentId);
			bgRole.setRoleName(roleName);
			bgRole.setRoleRights("0");
			bgRole.setAddRights("0");
			bgRole.setEditRights("0");
			bgRole.setDelRights("0");
			bgRole.setSeleRights("0");
			bgRole.setModifyTime(new Date());
			
			bgRole.setParentId(parentId);
			bgRoleService.add(bgRole);
		} catch(Exception e){
			logger.error(e.toString(), e);
			mv.addObject("msg","failed");
		}
		mv.addObject("msg","success");
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgRole页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = bgRoleService.findPdByPd(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/role/bgRoleEdit");
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
		logBefore(logger, "修改bgRole");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String roleName = pd.getString("roleName");
			pd = bgRoleService.findPdByPd(pd);	//根据ID读取
			pd.put("roleName", roleName);
			
			bgRoleService.editByPd(pd);
			
			mv.setViewName("background/role/bgRoleEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String roleId)throws Exception{
		logBefore(logger, "删除bgRole");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		
		try{
			int parentId = Integer.parseInt(roleId);
			List<BgRole> subBgRoleList = bgRoleService.listSubBgRoleByParentId(parentId);
			if(subBgRoleList.size()>0){
				errInfo = "false";											//下级有数据时，删除失败
			}else{
				List<BgUser> bgUserList = bgUserService.listAllByRoleId(parentId);
				if(bgUserList.size()>0){
					errInfo = "false2";
				}else{
					bgRoleService.deleteById(parentId);	//执行删除
					errInfo = "success";
				}
			}
			
			
//			bgRoleService.deleteByPd(pd);
//			out.write("success");
//			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDelete")
	@ResponseBody
	public Object batchDelete() {
		logBefore(logger, "批量删除bgRole");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String roleIds = pd.getString("roleIds");
			if(null != roleIds && !"".equals(roleIds)){
				bgRoleService.batchDeleteByIds(roleIds);
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
		logBefore(logger, "导出bgRole到excel");
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("后台角色表 主键id");   //1
			titles.add("角色名称");	//0+2
			titles.add("角色权限");	//1+2
			titles.add("上级id");	//2+2
			titles.add("新增权限");	//3+2
			titles.add("删除权限");	//4+2
			titles.add("修改权限");	//5+2
			titles.add("查看权限");	//6+2
			titles.add("修改时间");	//7+2
			dataMap.put("titles", titles);
			List<BgRole> varOList = bgRoleService.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getRoleId());
				vpd.put("var2", varOList.get(i).getRoleName());	//2
				vpd.put("var3", varOList.get(i).getRoleRights());	//3
				vpd.put("var4", varOList.get(i).getParentId());	//4
				vpd.put("var5", varOList.get(i).getAddRights());	//5
				vpd.put("var6", varOList.get(i).getDelRights());	//6
				vpd.put("var7", varOList.get(i).getEditRights());	//7
				vpd.put("var8", varOList.get(i).getSeleRights());	//8
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
	
	
	
	
	/**
	 * 显示菜单列表ztree(菜单授权菜单)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toChangeRoleRights")
	public ModelAndView toChangeRoleRights(@RequestParam String roleId,@RequestParam String msg,Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			BgRole bgRole = bgRoleService.findById(Integer.parseInt(roleId));			//根据角色ID获取角色对象
			String roleRights ="";														//取出本角色菜单权限
			if("roleRights".equals(msg)){
				roleRights = bgRole.getRoleRights();
			}else if("addRights".equals(msg)){
				roleRights = bgRole.getAddRights();
			}else if("delRights".equals(msg)){
				roleRights = bgRole.getDelRights();
			}else if("editRights".equals(msg)){
				roleRights = bgRole.getEditRights();
			}else if("seleRights".equals(msg)){
				roleRights = bgRole.getSeleRights();
			}
			List<BgMenu> bgMenuList = bgMenuService.listAllMenuInRank(0,"");			//获取所有菜单
			bgMenuList = this.bgMenuListTestRights(bgMenuList, roleRights);				//根据角色权限处理菜单权限状态(递归处理)
			JSONArray arr = JSONArray.fromObject(bgMenuList);
			String json = arr.toString();
			json = json.replaceAll("menuId", "id").replaceAll("parentId", "pId")
					.replaceAll("menuName", "name").replaceAll("subBgMenuList", "nodes")
					.replaceAll("hasRight", "checked").replaceAll("menuUrl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("roleId",roleId);
			mv.addObject("msg", msg);
			mv.setViewName("background/role/bgRoleRights");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**保存角色菜单权限
	 * @param roleId 角色ID
	 * @param menuIds 菜单ID集合
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/changeRoleRights")
	@ResponseBody
	public Object changeRoleRights()throws Exception{
//		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){} //校验权限
		logBefore(logger, "修改菜单权限");
		PageData pd = new PageData();
		pd = this.getPageData();
		String roleId = pd.getString("roleId");
		String menuIds = pd.getString("menuIds");
		String msg = pd.getString("msg");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		
		try{
			BgRole bgRole = new BgRole();
			bgRole.setRoleId(Integer.parseInt(roleId));
			bgRole.setModifyTime(new Date());
			String rights = "0";
			if(null != menuIds && !"".equals(menuIds.trim())){
				rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds)).toString();//用菜单ID做权处理
			}
			
			if("roleRights".equals(msg)){
				bgRole.setRoleRights(rights);
			}else if("addRights".equals(msg)){
				bgRole.setAddRights(rights);
			}else if("delRights".equals(msg)){
				bgRole.setDelRights(rights);
			}else if("editRights".equals(msg)){
				bgRole.setEditRights(rights);
			}else if("seleRights".equals(msg)){
				bgRole.setSeleRights(rights);
			}
			
			bgRoleService.change(bgRole);				//更新当前角色菜单权限
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<BgMenu> bgMenuListTestRights(List<BgMenu> bgMenuList,String roleRights){
		for(int i=0;i<bgMenuList.size();i++){
			bgMenuList.get(i).setHasRight(RightsHelper.testRights(roleRights, bgMenuList.get(i).getMenuId()));
			if(bgMenuList.get(i).isHasRight() && "1".equals(bgMenuList.get(i).getStatus())){				//判断是否有此菜单权限并且是否隐藏
				this.bgMenuListTestRights(bgMenuList.get(i).getSubBgMenuList(), roleRights);				//是：继续排查其子菜单
			}
		}
		return bgMenuList;
	}
	
	
}
