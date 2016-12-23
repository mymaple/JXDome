package com.jx.background.util;

import java.util.List;

import org.apache.shiro.session.Session;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.common.util.RightsHelper;

/**
 * 权限处理
 * @author:fh
 */
public class JudgeRightsUtil {

	/**
	 * 按钮权限(方法中校验)
	 * @param menuUrl 菜单路径
	 * @param type 类型(all、add、del、edit、sele)
	 * @return
	 */
	public static boolean hasRights(String menuUrl, String type) {
		// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
		/**
		 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
		 */
		
		return testRights(BgSessionUtil.getSessionBgAllMenuInRankList(), menuUrl, BgSessionUtil.getSession(), type);
	}
	
	
	/**校验菜单权限并初始按钮权限用于页面按钮显示与否(递归处理)
	 * @param menuList:传入的总菜单(设置菜单时，.do前面的不要重复)
	 * @param menuUrl:访问地址
	 * @param type 类型(all、add、del、edit、sele)
	 * @return
	 */
	public static boolean testRights(List<BgMenu> bgMenuList, String menuUrl, Session session, String type){
		for (int i = 0; i < bgMenuList.size(); i++) {
			BgMenu bgMenu = bgMenuList.get(i);
			if(bgMenu.getMenuUrl().split(".do")[0].equals(menuUrl.split(".do")[0])){
				if(!bgMenu.isHasRight()){
					return false;
				}else{
					BgRole bgRole =  BgSessionUtil.getSessionBgUserRole().getBgRole();
					String MenuId = String.valueOf(bgMenu.getMenuId());
					if("all".equals(type)){
						BgRole bgOperateRights = new BgRole();
						bgOperateRights.setAddRights(RightsHelper.testRights(bgRole.getAddRights(),MenuId)?"1":"0");
						bgOperateRights.setDelRights(RightsHelper.testRights(bgRole.getDelRights(),MenuId)?"1":"0");
						bgOperateRights.setEditRights(RightsHelper.testRights(bgRole.getEditRights(),MenuId)?"1":"0");
						bgOperateRights.setSeleRights(RightsHelper.testRights(bgRole.getSeleRights(),MenuId)?"1":"0");
						BgSessionUtil.setSessionBgOperateRights(bgOperateRights);
						return true;
					}if("add".equals(type)){
						return RightsHelper.testRights(bgRole.getAddRights(),MenuId);
					}else if("del".equals(type)){
						return RightsHelper.testRights(bgRole.getDelRights(),MenuId);
					}else if("edit".equals(type)){
						return RightsHelper.testRights(bgRole.getEditRights(),MenuId);
					}else if("sele".equals(type)){
						return RightsHelper.testRights(bgRole.getSeleRights(),MenuId);
					}
				}
			}else{
				if(!testRights(bgMenu.getSubBgMenuList(), menuUrl, session, type)){
					return false;
				}
			}
		}
		return true;
	}
	
}
