package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 指标 */
	private String target;
	
	/** 上级菜单 */
	private BgMenu parentBgMenu;
	
	/** 子菜单列表 */
	private List<BgMenu> subBgMenuList;
	
	/** 是否有次菜单权限 */
	private boolean hasRight = false;
	
	
	/**
	 * 获取 指标
	 * 
	 * @return String target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * 设置 指标
	 * 
	 * @param String target
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * 获取 上级菜单
	 * 
	 * @return BgMenu parentBgMenu
	 */
	public BgMenu getParentBgMenu() {
		return this.parentBgMenu;
	}
	
	/**
	 * 设置 上级菜单
	 * 
	 * @param BgMenu parentBgMenu
	 */
	public void setParentBgMenu(BgMenu parentBgMenu) {
		this.parentBgMenu = parentBgMenu;
	}
	
	/**
	 * 获取 子菜单列表
	 * 
	 * @return List<BgMenu> subBgMenuList
	 */
	public List<BgMenu> getSubBgMenuList() {
		return this.subBgMenuList;
	}
	
	/**
	 * 设置 子菜单列表
	 * 
	 * @param List<BgMenu> subBgMenuList
	 */
	public void setSubBgMenuList(List<BgMenu> subBgMenuList) {
		this.subBgMenuList = subBgMenuList;
	}
	
	/**
	 * 获取 是否有此菜单 
	 * 
	 * @return boolean hasRight
	 */
	public boolean isHasRight() {
		return this.hasRight;
	}
	
	/**
	 * 设置 是否有此菜单 
	 * 1是,0否
	 * 
	 * @param boolean hasRight
	 */
	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 后台菜单表 主键id */
	private int menuId;
	
	/** 菜单名称 */
	private String menuName;
	
	/** 菜单标记名称 */
	private String menuCode;
	
	/** 菜单标识 */
	private int menuTag;
		
	/** 菜单链接 */
	private String menuUrl;
		
	/** 上级id */
	private int parentId;
		
	/** 菜单排序 */
	private String menuOrder;
		
	/** 菜单图标 */
	private String menuIcon;
		
	/** 菜单类型 */
	private String menuType;
		
	/** 状态 */
	private String status;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 后台菜单表 主键id
	 * 
	 * @param int menuId
	 */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	
	/**
	 * 获取 后台菜单表 主键id
	 * 
	 * @return int menuId
	 */
	public int getMenuId() {
		return this.menuId;
	}
	
	/**
	 * 设置 菜单名称
	 * 
	 * @param String menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = MapleStringUtil.trim(menuName);
	}
	
	/**
	 * 获取 菜单名称
	 * 
	 * @return String menuName
	 */
	public String getMenuName() {
		return this.menuName;
	}
	
	/**
	 * 设置 菜单标记名称
	 * 
	 * @param String menuCode
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = MapleStringUtil.trim(menuCode);
	}
	
	/**
	 * 获取 菜单标记名称
	 * 
	 * @return String menuCode
	 */
	public String getMenuCode() {
		return this.menuCode;
	}
	
	/**
	 * 设置 菜单标识
	 * 
	 * @param int menuTag
	 */
	public void setMenuTag(int menuTag) {
		this.menuTag = menuTag;
	}
	
	/**
	 * 获取 菜单标识
	 * 
	 * @return int menuTag
	 */
	public int getMenuTag() {
		return this.menuTag;
	}
	
	/**
	 * 设置 菜单链接
	 * 
	 * @param String menuUrl
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = MapleStringUtil.trim(menuUrl);
	}
	
	/**
	 * 获取 菜单链接
	 * 
	 * @return String menuUrl
	 */
	public String getMenuUrl() {
		return this.menuUrl;
	}
	
	/**
	 * 设置 上级id
	 * 
	 * @param int parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取 上级id
	 * 
	 * @return int parentId
	 */
	public int getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置 菜单排序
	 * 
	 * @param String menuOrder
	 */
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = MapleStringUtil.trim(menuOrder);
	}
	
	/**
	 * 获取 菜单排序
	 * 
	 * @return String menuOrder
	 */
	public String getMenuOrder() {
		return this.menuOrder;
	}
	
	/**
	 * 设置 菜单图标
	 * 
	 * @param String menuIcon
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = MapleStringUtil.trim(menuIcon);
	}
	
	/**
	 * 获取 菜单图标
	 * 
	 * @return String menuIcon
	 */
	public String getMenuIcon() {
		return this.menuIcon;
	}
	
	/**
	 * 设置 菜单类型
	 * 
	 * @param String menuType
	 */
	public void setMenuType(String menuType) {
		this.menuType = MapleStringUtil.trim(menuType);
	}
	
	/**
	 * 获取 菜单类型
	 * 
	 * @return String menuType
	 */
	public String getMenuType() {
		return this.menuType;
	}
	
	/**
	 * 设置 状态
	 * 
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = MapleStringUtil.trim(status);
	}
	
	/**
	 * 获取 状态
	 * 
	 * @return String status
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置 修改时间
	 * 
	 * @param Date modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date modifyTime
	 */
	public Date getModifyTime() {
		return this.modifyTime;
	}	
		
	public BgMenu(){
		init();
	}
	
	public void init() {
		setMenuId(0);
	
		setMenuName("");
		setMenuUrl("");
		setParentId(0);
		setMenuOrder("");
		setMenuIcon("");
		setMenuType("");
		setStatus("");
	}
	/**************************table prop  end  *********************************/
}