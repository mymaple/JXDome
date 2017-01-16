package com.jx.background.util;

import java.util.List;

import org.apache.shiro.session.Session;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.background.entity.BgRole;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.RightsHelper;

/**
 * 权限处理
 * @author:fh
 */
public class JudgeRightsUtil {

	/**
	 * 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
	 * @param menuCode 菜单标识名称
	 * @param type 类型(add、del、edit、sele)
	 * @return
	 */
	public static boolean hasRights(String menuCode, String type) {
		
		if(MapleStringUtil.isEmpty(menuCode) || MapleStringUtil.isEmpty(type)){
			return false;
		}
		
		//是否为同菜单操作
		BgRights bgRights = BgSessionUtil.getSessionBgRights();
		if(menuCode.equals(bgRights.getMenuCode())){
			if("add".equals(type)){
				return bgRights.isAdd();
			}else if("del".equals(type)){
				return bgRights.isDel();
			}else if("edit".equals(type)){
				return bgRights.isEdit();
			}else if("sele".equals(type)){
				return bgRights.isSele();
			}
		}
		
		return hasRightsRecur(menuCode, type, BgSessionUtil.getSessionBgAllMenuInRankList(), bgRights);
	}
	
	
	/**校验菜单权限并初始按钮权限用于页面按钮显示与否(递归处理)
	 * @param menuList:传入的总菜单(设置菜单时，.do前面的不要重复)
	 * @param menuUrl:访问地址
	 * @param type 类型(add、del、edit、sele)
	 * @return
	 */
	public static boolean hasRightsRecur(String menuCode, String type, List<BgMenu> bgMenuList, BgRights bgRights){
		for (int i = 0; i < bgMenuList.size(); i++) {
			BgMenu bgMenu = bgMenuList.get(i);
			if(menuCode.equals(bgMenu.getMenuCode())){
				if(!bgMenu.isHasMenu()){
					return false;
				}else{
					
					BgRole bgRole =  BgSessionUtil.getSessionBgUserRole().getBgRole();
					String MenuId = String.valueOf(bgMenu.getMenuId());
					bgRights.setMenuCode(menuCode);
					bgRights.setAdd(RightsHelper.testRights(bgRole.getAddRights(),MenuId));
					bgRights.setDel(RightsHelper.testRights(bgRole.getDelRights(),MenuId));
					bgRights.setEdit(RightsHelper.testRights(bgRole.getEditRights(),MenuId));
					bgRights.setSele(RightsHelper.testRights(bgRole.getSeleRights(),MenuId));
					BgSessionUtil.setSessionBgRights(bgRights);
					if("add".equals(type)){
						return bgRights.isAdd();
					}else if("del".equals(type)){
						return bgRights.isDel();
					}else if("edit".equals(type)){
						return bgRights.isEdit();
					}else if("sele".equals(type)){
						return bgRights.isSele();
					}
				}
			}else{
				if(!hasRightsRecur(menuCode, type, bgMenu.getSubBgMenuList(), bgRights)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
	 * @param menuTag 菜单标识
	 * @return
	 */
	public static BgRights getRights(String menuCode) {
		
		if(MapleStringUtil.isEmpty(menuCode)){
			return null;
		}
		
		//是否为同菜单操作
		BgRights bgRights = BgSessionUtil.getSessionBgRights();
		if(menuCode.equals(bgRights.getMenuCode())){
			return bgRights;
		}
		
		return getRightsRecur(menuCode, BgSessionUtil.getSessionBgAllMenuInRankList(), bgRights);
	}
	
	/**校验菜单权限并初始权限用于页面按钮显示与否(递归处理)
	 * @param menuTag:访问菜单标识
	 * @param bgMenuList:传入的总菜单
	 * @param bgRights：当前权限组
	 * @return
	 */
	@SuppressWarnings("unused")
	public static BgRights getRightsRecur(String menuCode, List<BgMenu> bgMenuList, BgRights bgRights){
		
		for (int i = 0; i < bgMenuList.size(); i++) {
			BgMenu bgMenu = bgMenuList.get(i);
			if(menuCode.equals(bgMenu.getMenuCode())){
				if(!bgMenu.isHasMenu()){
					return null;
				}else{
					BgRole bgRole =  BgSessionUtil.getSessionBgUserRole().getBgRole();
					String MenuId = String.valueOf(bgMenu.getMenuId());
					bgRights.setMenuCode(menuCode);
					bgRights.setAdd(RightsHelper.testRights(bgRole.getAddRights(),MenuId));
					bgRights.setDel(RightsHelper.testRights(bgRole.getDelRights(),MenuId));
					bgRights.setEdit(RightsHelper.testRights(bgRole.getEditRights(),MenuId));
					bgRights.setSele(RightsHelper.testRights(bgRole.getSeleRights(),MenuId));
					BgSessionUtil.setSessionBgRights(bgRights);
					return bgRights;
				}
			}else{
				return getRightsRecur(menuCode, bgMenu.getSubBgMenuList(), bgRights);
			}
		}
		return null;
	}
}
