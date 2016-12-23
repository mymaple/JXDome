package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 后台角色表 主键id */
	private int roleId;
	
	/** 角色名称 */
	private String roleName;
		
	/** 角色权限 */
	private String roleRights;
		
	/** 上级id */
	private int parentId;
		
	/** 新增权限 */
	private String addRights;
		
	/** 删除权限 */
	private String delRights;
		
	/** 修改权限 */
	private String editRights;
		
	/** 查看权限 */
	private String seleRights;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 后台角色表 主键id
	 * 
	 * @param int roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 获取 后台角色表 主键id
	 * 
	 * @return int roleId
	 */
	public int getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 角色名称
	 * 
	 * @param String roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = MapleStringUtil.trim(roleName);
	}
	
	/**
	 * 获取 角色名称
	 * 
	 * @return String roleName
	 */
	public String getRoleName() {
		return this.roleName;
	}
	
	/**
	 * 设置 角色权限
	 * 
	 * @param String roleRights
	 */
	public void setRoleRights(String roleRights) {
		this.roleRights = MapleStringUtil.trim(roleRights);
	}
	
	/**
	 * 获取 角色权限
	 * 
	 * @return String roleRights
	 */
	public String getRoleRights() {
		return this.roleRights;
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
	 * 设置 新增权限
	 * 
	 * @param String addRights
	 */
	public void setAddRights(String addRights) {
		this.addRights = MapleStringUtil.trim(addRights);
	}
	
	/**
	 * 获取 新增权限
	 * 
	 * @return String addRights
	 */
	public String getAddRights() {
		return this.addRights;
	}
	
	/**
	 * 设置 删除权限
	 * 
	 * @param String delRights
	 */
	public void setDelRights(String delRights) {
		this.delRights = MapleStringUtil.trim(delRights);
	}
	
	/**
	 * 获取 删除权限
	 * 
	 * @return String delRights
	 */
	public String getDelRights() {
		return this.delRights;
	}
	
	/**
	 * 设置 修改权限
	 * 
	 * @param String editRights
	 */
	public void setEditRights(String editRights) {
		this.editRights = MapleStringUtil.trim(editRights);
	}
	
	/**
	 * 获取 修改权限
	 * 
	 * @return String editRights
	 */
	public String getEditRights() {
		return this.editRights;
	}
	
	/**
	 * 设置 查看权限
	 * 
	 * @param String seleRights
	 */
	public void setSeleRights(String seleRights) {
		this.seleRights = MapleStringUtil.trim(seleRights);
	}
	
	/**
	 * 获取 查看权限
	 * 
	 * @return String seleRights
	 */
	public String getSeleRights() {
		return this.seleRights;
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
		
	public void setModifyTimeStr(String modifyTimeStr) throws Exception{
		modifyTimeStr = MapleStringUtil.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(MapleDateUtil.parseDate(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getModifyTimeStr(){
		return MapleDateUtil.getFormatedDateString(getModifyTime());
	}	
	
	
	public BgRole(){
		init();
	}
	
	public void init() {
		setRoleId(0);
	
		setRoleName("");
		setRoleRights("");
		setParentId(0);
		setAddRights("");
		setDelRights("");
		setEditRights("");
		setSeleRights("");
		try {
			setModifyTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************table prop  end  *********************************/
}