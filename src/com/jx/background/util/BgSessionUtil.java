package com.jx.background.util;

import java.util.List;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.common.config.shiro.ShiroSessionUtil;

public class BgSessionUtil extends ShiroSessionUtil{
	
	/**
	 * 后台 将要换的菜单类型
	 */
	public static final String SESSION_BG_CHANGEMENU_STR = "sessionBgChangeMenu";

	/**
	 * 后台 操作权限
	 */
	public static final String SESSION_BG_RIGHTS_OBJ = "sessionBgRights";
	
	/**
	 * 后台 当前菜单列表
	 */
	public static final String SESSION_BG_MENU_INCURRTEN_LIST = "sessionBgMenuInCurrentList";
	
	/**
	 * 后台 全部菜单列表 分级
	 */
	public static final String SESSION_BG_ALLMENU_INRANK_LIST = "sessionBgAllMenuInRankList";
	

	
	/**
	 * 获取 后台 将要换的菜单类型
	 * @return
	 */
	public static String getSessionBgChangeMenu(){
		return (String)getSession().getAttribute(SESSION_BG_CHANGEMENU_STR);
	}
	
	/**
	 * 设置 后台 将要换的菜单类型
	 * @param changeMenu
	 */
	public static void setSessionBgChangeMenu(String changeMenu){
		setSessionAttr(SESSION_BG_CHANGEMENU_STR, changeMenu);
	}
	
	/**
	 * 获取 后台 操作权限
	 * @return
	 */
	public static BgRights getSessionBgRights(){
		return (BgRights)getSession().getAttribute(SESSION_BG_RIGHTS_OBJ);
	}
	
	/**
	 * 设置 后台 操作权限
	 * @param bgRights
	 */
	public static void setSessionBgRights(BgRights bgRights){
		setSessionAttr(SESSION_BG_RIGHTS_OBJ, bgRights);
	}
	
	/**
	 * 获取 后台 当前菜单列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<BgMenu> getSessionBgMenuInCurrentList(){
		return (List<BgMenu>)getSession().getAttribute(SESSION_BG_MENU_INCURRTEN_LIST);
	}
	
	/**
	 * 设置 后台 当前菜单列表
	 * @param bgMenuList
	 */
	public static void setSessionBgMenuInCurrentList(List<BgMenu> bgMenuList){
		setSessionAttr(SESSION_BG_MENU_INCURRTEN_LIST, bgMenuList);
	}
	
	/**
	 * 获取 后台 全部菜单列表 分级
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<BgMenu> getSessionBgAllMenuInRankList(){
		return (List<BgMenu>)getSession().getAttribute(SESSION_BG_ALLMENU_INRANK_LIST);
	}
	
	/**
	 * 设置 后台 全部菜单列表 分级
	 * @param bgMenuList
	 */
	public static void setSessionBgAllMenuInRankList(List<BgMenu> bgMenuList){
		setSessionAttr(SESSION_BG_ALLMENU_INRANK_LIST, bgMenuList);
	}
	
}
