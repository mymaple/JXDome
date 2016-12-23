package com.jx.common.util;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.Const;

/**
 * 权限处理
 * @author:fh
 */
public class Jurisdiction {

	/**
	 * 访问权限及初始化按钮权限(控制按钮的显示)
	 * @param menuUrl 菜单路径
	 * @return
	 */
	public static boolean hasJurisdiction(String menuUrl) {
		// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
		/**
		 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
		 */

		return testRight(BgSessionUtil.getSessionBgAllMenuInRankList(), menuUrl, BgSessionUtil.getSession());
	}
	
	
	/**校验菜单权限并初始按钮权限用于页面按钮显示与否(递归处理)
	 * @param menuList:传入的总菜单(设置菜单时，.do前面的不要重复)
	 * @param menuUrl:访问地址
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean testRight(List<BgMenu> bgMenuList,String menuUrl,Session session){
		for (int i = 0; i < bgMenuList.size(); i++) {
			BgMenu bgMenu = bgMenuList.get(i);
			if(bgMenu.getMenuUrl().split(".do")[0].equals(menuUrl.split(".do")[0])){
				if(!bgMenu.isHasRight()){
					return false;
				}else{
					BgRole bgRole =  BgSessionUtil.getSeesionBgUserRole().getBgRole();
					String MenuId = String.valueOf(bgMenu.getMenuId());
					BgRole bgOperateRights = new BgRole();
					bgOperateRights.setAddRights(RightsHelper.testRights(bgRole.getAddRights(),MenuId)?"1":"0");
					bgOperateRights.setDelRights(RightsHelper.testRights(bgRole.getDelRights(),MenuId)?"1":"0");
					bgOperateRights.setEditRights(RightsHelper.testRights(bgRole.getEditRights(),MenuId)?"1":"0");
					bgOperateRights.setSeleRights(RightsHelper.testRights(bgRole.getSeleRights(),MenuId)?"1":"0");
					if(session.getAttribute(Const.SESSION_BG_OPERATERIGHTS_OBJ) != null){
						session.removeAttribute(Const.SESSION_BG_OPERATERIGHTS_OBJ);
					}
					session.setAttribute(Const.SESSION_BG_OPERATERIGHTS_OBJ,bgOperateRights);
					return true;
				}
			}else{
				if(!testRight(bgMenuList, menuUrl, session)){
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	

	/**
	 * 按钮权限(方法中校验)
	 * @param menuUrl 菜单路径
	 * @param type 类型(add、del、edit、cha)
	 * @return
	 */
	public static boolean buttonJurisdiction(String menuUrl, String type) {
		// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
		/**
		 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
		 */
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Boolean b = true;
		List<BgMenu> menuList = (List) session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST); // 获取菜单列表

		for (int i = 0; i < menuList.size(); i++) {
			for (int j = 0; j < menuList.get(i).getSubBgMenuList().size(); j++) {
				if (menuList.get(i).getSubBgMenuList().get(j).getMenuUrl().split(".do")[0].equals(menuUrl.split(".do")[0])) {
					if (!menuList.get(i).getSubBgMenuList().get(j).isHasRight()) { // 判断有无此菜单权限
						return false;
					} else { // 按钮判断
						Map<String, String> map = (Map<String, String>) session.getAttribute(Const.SESSION_BG_QX_STR);// 按钮权限
						int menuId = menuList.get(i).getSubBgMenuList().get(j).getMenuId();
						String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString(); // 获取当前登录者loginname
						Boolean isAdmin = "admin".equals(userName);
						if ("add".equals(type)) {
							return ((RightsHelper.testRights(map.get("adds"), menuId)) || isAdmin);
						} else if ("del".equals(type)) {
							return ((RightsHelper.testRights(map.get("dels"), menuId)) || isAdmin);
						} else if ("edit".equals(type)) {
							return ((RightsHelper.testRights(map.get("edits"), menuId)) || isAdmin);
						} else if ("cha".equals(type)) {
							return ((RightsHelper.testRights(map.get("chas"), menuId)) || isAdmin);
						}
					}
				}
			}
		}
		return true;
	}
	

	
}
