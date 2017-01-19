package com.jx.background.util;

import java.math.BigInteger;
import java.util.List;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.background.entity.BgRole;
import com.jx.common.util.MapleStringUtil;

/**
 * 权限处理
 * @author:maple
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
					String menuTag = bgMenu.getMenuTag();
					bgRights.setMenuCode(menuCode);
					bgRights.setAdd(testRights(bgRole.getAddRights(),menuTag));
					bgRights.setDel(testRights(bgRole.getDelRights(),menuTag));
					bgRights.setEdit(testRights(bgRole.getEditRights(),menuTag));
					bgRights.setSele(testRights(bgRole.getSeleRights(),menuTag));
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
					String menuTag = String.valueOf(bgMenu.getMenuTag());
					bgRights.setMenuCode(menuCode);
					bgRights.setAdd(testRights(bgRole.getAddRights(),menuTag));
					bgRights.setDel(testRights(bgRole.getDelRights(),menuTag));
					bgRights.setEdit(testRights(bgRole.getEditRights(),menuTag));
					bgRights.setSele(testRights(bgRole.getSeleRights(),menuTag));
					BgSessionUtil.setSessionBgRights(bgRights);
					return bgRights;
				}
			}else{
				return getRightsRecur(menuCode, bgMenu.getSubBgMenuList(), bgRights);
			}
		}
		return null;
	}
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public static List<BgMenu> bgMenuListTestRights(List<BgMenu> bgMenuList,String roleRights){
		for(int i=0;i<bgMenuList.size();i++){
			bgMenuList.get(i).setHasMenu(testRights(roleRights, bgMenuList.get(i).getMenuTag()));
			if(bgMenuList.get(i).isHasMenu() && "01".equals(bgMenuList.get(i).getMenuStatus())){				//判断是否有此菜单权限并且是否隐藏
				bgMenuListTestRights(bgMenuList.get(i).getSubBgMenuList(), roleRights);							//是：继续排查其子菜单
			}
		}
		return bgMenuList;
	}
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public static List<BgMenu> getBgMenuListByRoleRights(List<BgMenu> bgMenuList,String roleRights){
		for(int i=0;i<bgMenuList.size();i++){
			bgMenuList.get(i).setHasMenu(testRights(roleRights, bgMenuList.get(i).getMenuId()));
			if(bgMenuList.get(i).isHasMenu() && "01".equals(bgMenuList.get(i).getMenuStatus())){				//判断是否有此菜单权限并且是否隐藏
				bgMenuListTestRights(bgMenuList.get(i).getSubBgMenuList(), roleRights);				//是：继续排查其子菜单
			}else{
				bgMenuList.remove(i);
				i--;
			}
		}
		return bgMenuList;
	}
	
	/**
	 * 利用BigInteger对权限进行2的权的和计算
	 * @param rights int型权限编码数组
	 * @return 2的权的和
	 */
	public static BigInteger sumRights(int[] rights) {
		BigInteger num = new BigInteger("0");
		for (int i = 0; i < rights.length; i++) {
			num = num.setBit(rights[i]);
		}
		return num;
	}

	/**
	 * 利用BigInteger对权限进行2的权的和计算
	 * @param rights String型权限编码数组
	 * @return 2的权的和
	 */
	public static BigInteger sumRights(String[] rights) {
		BigInteger num = new BigInteger("0");
		for (int i = 0; i < rights.length; i++) {
			num = num.setBit(Integer.parseInt(rights[i]));
		}
		return num;
	}

	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(BigInteger sum, int targetRights) {
		return sum.testBit(targetRights);
	}

	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(String sum, int targetRights) {
		if (MapleStringUtil.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum), targetRights);
	}

	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(String sum, String targetRights) {
		if (MapleStringUtil.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum), targetRights);
	}

	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(BigInteger sum, String targetRights) {
		return testRights(sum, Integer.parseInt(targetRights));
	}
	
}
