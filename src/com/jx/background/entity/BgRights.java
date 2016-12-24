package com.jx.background.entity;

import java.io.Serializable;

import com.jx.common.util.MapleStringUtil;

public class BgRights implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 菜单 标记名称 */
	private String menuCode;
	
	/** 权限 增 */
	private boolean add;
	
	/** 权限 删 */
	private boolean del;
	
	/** 权限 改 */
	private boolean edit;
	
	/** 权限 查*/
	private boolean sele;
	
	
	
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
	 * 获取 权限 增 
	 * @return boolean add
	 */
	public boolean isAdd() {
		return this.add;
	}

	/**
	 * 设置 权限 增 
	 * 
	 * @param boolean add
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}

	/**
	 * 获取 权限 删
	 * @return boolean del
	 */
	public boolean isDel() {
		return this.del;
	}

	/**
	 * 设置 权限 删
	 * 
	 * @param boolean del
	 */
	public void setDel(boolean del) {
		this.del = del;
	}

	/**
	 * 获取 权限 改 
	 * @return boolean edit
	 */
	public boolean isEdit() {
		return this.edit;
	}

	/**
	 * 设置 权限 改 
	 * 
	 * @param boolean edit
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	/**
	 * 获取 权限 查
	 * @return boolean add
	 */
	public boolean isSele() {
		return this.sele;
	}

	/**
	 * 设置 权限 查
	 * 
	 * @param boolean add
	 */
	public void setSele(boolean sele) {
		this.sele = sele;
	}
	
	
	
	
}
