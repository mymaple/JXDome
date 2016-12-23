package com.jx.background.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgUser;

public class BgSessionUtil {
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	/**获得  Session key对应的Obj
	 * @return
	 */
	public static Object getSessionAttr(String key){
		return getSession().getAttribute(key);
	}
	
	
	/**设置  Session key对应的Obj
	 * @return
	 */
	public static void setSessionAttr(String key, Object obj){
		Session session = getSession();
		if(session.getAttribute(key) != null){
			session.removeAttribute(key);
		}
		session.setAttribute(key, obj);
	}
	
	
	
	/**
	 * 后台 验证码
	 */
	public static final String SESSION_BG_VERIFICATIONCODE_STR = "sessionBgVerificationCode";
	
	/**
	 * 后台 将要换的菜单类型
	 */
	public static final String SESSION_BG_CHANGEMENU_STR = "sessionBgChangeMenu";

	/**
	 * 后台 当前用户角色
	 */
	public static final String SESSION_BG_USER_ROLE_OBJ = "seesionBgUserRole";
	
	/**
	 * 后台 管理权限
	 */
	public static final String SESSION_BG_OPERATERIGHTS_OBJ = "sessionBgOperateRights";
	
	/**
	 * 后台 当前菜单列表
	 */
	public static final String SESSION_BG_MENU_INCURRTEN_LIST = "sessionBgMenuInCurrentList";
	
	/**
	 * 后台 全部菜单列表 分级
	 */
	public static final String SESSION_BG_ALLMENU_INRANK_LIST = "sessionBgAllMenuInRankList";
	

	
	/**
	 * 获取 后台 验证码
	 * @return
	 */
	public static String getSeesionBgVerificationCode(){
		return (String)getSession().getAttribute(SESSION_BG_VERIFICATIONCODE_STR);
	}
	
	/**
	 * 设置 后台 验证码
	 * @param verificationCode
	 */
	public static void setSeesionBgVerificationCode(String verificationCode){
		setSessionAttr(SESSION_BG_VERIFICATIONCODE_STR, verificationCode);
	}
	
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
	 * 获取 后台 当前用户角色
	 * @return
	 */
	public static BgUser getSessionBgUserRole(){
		return (BgUser)getSession().getAttribute(SESSION_BG_USER_ROLE_OBJ);
	}
	
	/**
	 * 设置 后台 当前用户角色
	 * @param bgUser
	 */
	public static void setSessionBgUserRole(BgUser bgUser){
		setSessionAttr(SESSION_BG_USER_ROLE_OBJ, bgUser);
	}
	
	/**
	 * 获取 后台 当前用户角色
	 * @return
	 */
	public static BgUser getSessionBgOperateRights(){
		return (BgUser)getSession().getAttribute(SESSION_BG_USER_ROLE_OBJ);
	}
	
	/**
	 * 设置 后台 当前用户角色
	 * @param bgUser
	 */
	public static void setSessionBgOperateRights(BgUser bgUser){
		setSessionAttr(SESSION_BG_USER_ROLE_OBJ, bgUser);
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
