package com.jx.background.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class BgMenu extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<BgMenu> subBgMenuList;
	
	/** 子列表 路径*/
	private String subBgMenuPath;
	
	/** 是否有此后台菜单 */
	private boolean hasMenu;

	
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
		this.target = MapleStringUtil.trim(target);
	}
	
	/**
	 * 获取 子列表
	 * 
	 * @return List<BgMenu> subBgMenuList
	 */
	public List<BgMenu> getSubBgMenuList() {
		return this.subBgMenuList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<BgMenu> subBgMenuList
	 */
	public void setSubBgMenuList(List<BgMenu> subBgMenuList) {
		this.subBgMenuList = subBgMenuList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subBgMenuPath
	 */
	public String getSubBgMenuPath() {
		return this.subBgMenuPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subBgMenuPath
	 */
	public void setSubBgMenuPath(String subBgMenuPath) {
		this.subBgMenuPath = MapleStringUtil.trim(subBgMenuPath);
	}
	
	/**
	 * 获取 是否有此后台菜单 
	 * 
	 * @return boolean hasMenu
	 */
	public boolean isHasMenu() {
		return this.hasMenu;
	}
	
	/**
	 * 设置 是否有此后台菜单
	 * 
	 * @param boolean hasMenu
	 */
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	
	
	/**************************custom prop end**********************************/
	
	public interface ValidationIcon {
		
	}
	
	/**************************table prop satrt*********************************/
	
	/** 后台菜单 主键id */
	@NotBlank(message="后台菜单 主键id 不能为空", groups={ValidationEdit.class, ValidationIcon.class})
	private String menuId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 后台菜单代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="后台菜单代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String menuCode;
		
	/** 后台菜单名称 */
	@NotBlank(message="后台菜单名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String menuName;
		
	/** 后台菜单类型 */
	@NotBlank(message="后台菜单类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String menuType;
		
	/** 后台菜单状态 */
	private String menuStatus;
		
	/** 菜单数字标记 */
	@Pattern(regexp = Const.REG_COM_FFZS_STR, message="菜单数字标记 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String menuTag;
		
	/** 菜单链接 */
	@NotBlank(message="菜单链接 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String menuUrl;
		
	/** 菜单图标 */
	@NotBlank(message="菜单图标 不能为空", groups={ValidationIcon.class})
	private String menuIcon;
		
	
	
	/**
	 * 设置后台菜单 主键id
	 * 
	 * @param String menuId
	 */
	public void setMenuId(String menuId) {
		this.menuId = MapleStringUtil.trim(menuId);
	}
	
	/**
	 * 获取后台菜单 主键id
	 * 
	 * @return String menuId
	 */
	public String getMenuId() {
		return this.menuId;
	}
	
	/**
	 * 设置 上级id
	 * 
	 * @param String parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = MapleStringUtil.trim(parentId);
	}
	
	/**
	 * 获取 上级id
	 * 
	 * @return String parentId
	 */
	public String getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置 后台菜单代号
	 * 
	 * @param String menuCode
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = MapleStringUtil.trim(menuCode);
	}
	
	/**
	 * 获取 后台菜单代号
	 * 
	 * @return String menuCode
	 */
	public String getMenuCode() {
		return this.menuCode;
	}
	
	/**
	 * 设置 后台菜单名称
	 * 
	 * @param String menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = MapleStringUtil.trim(menuName);
	}
	
	/**
	 * 获取 后台菜单名称
	 * 
	 * @return String menuName
	 */
	public String getMenuName() {
		return this.menuName;
	}
	
	/**
	 * 设置 后台菜单类型
	 * 
	 * @param String menuType
	 */
	public void setMenuType(String menuType) {
		this.menuType = MapleStringUtil.trim(menuType);
	}
	
	/**
	 * 获取 后台菜单类型
	 * 
	 * @return String menuType
	 */
	public String getMenuType() {
		return this.menuType;
	}
	
	/**
	 * 设置 后台菜单状态
	 * 
	 * @param String menuStatus
	 */
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = MapleStringUtil.trim(menuStatus);
	}
	
	/**
	 * 获取 后台菜单状态
	 * 
	 * @return String menuStatus
	 */
	public String getMenuStatus() {
		return this.menuStatus;
	}
	
	/**
	 * 设置 菜单数字标记
	 * 
	 * @param String menuTag
	 */
	public void setMenuTag(String menuTag) {
		this.menuTag = menuTag;
	}
	
	/**
	 * 获取 菜单数字标记
	 * 
	 * @return String menuTag
	 */
	public String getMenuTag() {
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
	
	
	/**************************table prop  end  *********************************/
}