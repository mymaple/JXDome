package com.jx.background.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class BgRole extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 后台角色 主键id */
	@NotBlank(message="后台角色 主键id 不能为空", groups={ValidationEdit.class})
	private String roleId;
	
	/** 后台角色代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="后台角色代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String roleCode;
		
	/** 后台角色名称 */
	@NotBlank(message="后台角色名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String roleName;
		
	/** 后台角色类型 */
	@NotBlank(message="后台角色类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String roleType;
		
	/** 后台角色状态 */
	private String roleStatus;
		
	/** 角色权限 */
	private String roleRights;
		
	/** 新增权限 */
	private String addRights;
		
	/** 删除权限 */
	private String delRights;
		
	/** 修改权限 */
	private String editRights;
		
	/** 查询权限 */
	private String seleRights;
		
	
	
	/**
	 * 设置后台角色 主键id
	 * 
	 * @param String roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = MapleStringUtil.trim(roleId);
	}
	
	/**
	 * 获取后台角色 主键id
	 * 
	 * @return String roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 后台角色代号
	 * 
	 * @param String roleCode
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = MapleStringUtil.trim(roleCode);
	}
	
	/**
	 * 获取 后台角色代号
	 * 
	 * @return String roleCode
	 */
	public String getRoleCode() {
		return this.roleCode;
	}
	
	/**
	 * 设置 后台角色名称
	 * 
	 * @param String roleName
	 */
	public void setRoleName(String roleName) {
		this.roleName = MapleStringUtil.trim(roleName);
	}
	
	/**
	 * 获取 后台角色名称
	 * 
	 * @return String roleName
	 */
	public String getRoleName() {
		return this.roleName;
	}
	
	/**
	 * 设置 后台角色类型
	 * 
	 * @param String roleType
	 */
	public void setRoleType(String roleType) {
		this.roleType = MapleStringUtil.trim(roleType);
	}
	
	/**
	 * 获取 后台角色类型
	 * 
	 * @return String roleType
	 */
	public String getRoleType() {
		return this.roleType;
	}
	
	/**
	 * 设置 后台角色状态
	 * 
	 * @param String roleStatus
	 */
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = MapleStringUtil.trim(roleStatus);
	}
	
	/**
	 * 获取 后台角色状态
	 * 
	 * @return String roleStatus
	 */
	public String getRoleStatus() {
		return this.roleStatus;
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
	 * 设置 查询权限
	 * 
	 * @param String seleRights
	 */
	public void setSeleRights(String seleRights) {
		this.seleRights = MapleStringUtil.trim(seleRights);
	}
	
	/**
	 * 获取 查询权限
	 * 
	 * @return String seleRights
	 */
	public String getSeleRights() {
		return this.seleRights;
	}
	
	
	/**************************table prop  end  *********************************/
}