package com.jx.common.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;

public class ComAppUserRole extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComAppUserRole> subComAppUserRoleList;
	
	/** 子列表 路径*/
	private String subComAppUserRolePath;
	
	/** 是否有此平台用户角色 */
	private boolean hasAppUserRole;

	/** 角色用户 */
	private List<ComAppUser> comAppUserList;
	
	
	
	/**
	 * 获取 角色用户
	 * 
	 * @param List<ComAppUser> comAppUserList
	 */
	public List<ComAppUser> getComAppUserList() {
		return this.comAppUserList;
	}

	/**
	 * 设置 角色用户
	 * 
	 * @param List<ComAppUser> comAppUserList
	 */
	public void setComAppUserList(List<ComAppUser> comAppUserList) {
		this.comAppUserList = comAppUserList;
	}

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
		this.target = StringUtils.trim(target);
	}
	
	/**
	 * 获取 子列表
	 * 
	 * @return List<ComAppUserRole> subComAppUserRoleList
	 */
	public List<ComAppUserRole> getSubComAppUserRoleList() {
		return this.subComAppUserRoleList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComAppUserRole> subComAppUserRoleList
	 */
	public void setSubComAppUserRoleList(List<ComAppUserRole> subComAppUserRoleList) {
		this.subComAppUserRoleList = subComAppUserRoleList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComAppUserRolePath
	 */
	public String getSubComAppUserRolePath() {
		return this.subComAppUserRolePath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComAppUserRolePath
	 */
	public void setSubComAppUserRolePath(String subComAppUserRolePath) {
		this.subComAppUserRolePath = StringUtils.trim(subComAppUserRolePath);
	}
	
	/**
	 * 获取 是否有此平台用户角色 
	 * 
	 * @return boolean hasAppUserRole
	 */
	public boolean isHasAppUserRole() {
		return this.hasAppUserRole;
	}
	
	/**
	 * 设置 是否有此平台用户角色
	 * 
	 * @param boolean hasAppUserRole
	 */
	public void setHasAppUserRole(boolean hasAppUserRole) {
		this.hasAppUserRole = hasAppUserRole;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 平台用户角色 主键id */
	@NotBlank(message="平台用户角色 主键id 不能为空", groups={ValidationEdit.class})
	private String appUserRoleId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 平台用户角色代号 */
	private String appUserRoleCode;
		
	/** 平台用户角色名称 */
	@NotBlank(message="平台用户角色名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserRoleName;
		
	/** 平台用户角色类型 */
	@NotBlank(message="平台用户角色类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserRoleType;
		
	/** 平台用户角色状态 */
	@NotBlank(message="平台用户角色状态 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserRoleStatus;
		
	/** 备注 */
	@NotBlank(message="备注 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String remarks;
		
	
	
	/**
	 * 设置平台用户角色 主键id
	 * 
	 * @param String appUserRoleId
	 */
	public void setAppUserRoleId(String appUserRoleId) {
		this.appUserRoleId = StringUtils.trim(appUserRoleId);
	}
	
	/**
	 * 获取平台用户角色 主键id
	 * 
	 * @return String appUserRoleId
	 */
	public String getAppUserRoleId() {
		return this.appUserRoleId;
	}
	
	/**
	 * 设置 上级id
	 * 
	 * @param String parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = StringUtils.trim(parentId);
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
	 * 设置 平台用户角色代号
	 * 
	 * @param String appUserRoleCode
	 */
	public void setAppUserRoleCode(String appUserRoleCode) {
		this.appUserRoleCode = StringUtils.trim(appUserRoleCode);
	}
	
	/**
	 * 获取 平台用户角色代号
	 * 
	 * @return String appUserRoleCode
	 */
	public String getAppUserRoleCode() {
		return this.appUserRoleCode;
	}
	
	/**
	 * 设置 平台用户角色名称
	 * 
	 * @param String appUserRoleName
	 */
	public void setAppUserRoleName(String appUserRoleName) {
		this.appUserRoleName = StringUtils.trim(appUserRoleName);
	}
	
	/**
	 * 获取 平台用户角色名称
	 * 
	 * @return String appUserRoleName
	 */
	public String getAppUserRoleName() {
		return this.appUserRoleName;
	}
	
	/**
	 * 设置 平台用户角色类型
	 * 
	 * @param String appUserRoleType
	 */
	public void setAppUserRoleType(String appUserRoleType) {
		this.appUserRoleType = StringUtils.trim(appUserRoleType);
	}
	
	/**
	 * 获取 平台用户角色类型
	 * 
	 * @return String appUserRoleType
	 */
	public String getAppUserRoleType() {
		return this.appUserRoleType;
	}
	
	/**
	 * 设置 平台用户角色状态
	 * 
	 * @param String appUserRoleStatus
	 */
	public void setAppUserRoleStatus(String appUserRoleStatus) {
		this.appUserRoleStatus = StringUtils.trim(appUserRoleStatus);
	}
	
	/**
	 * 获取 平台用户角色状态
	 * 
	 * @return String appUserRoleStatus
	 */
	public String getAppUserRoleStatus() {
		return this.appUserRoleStatus;
	}
	
	/**
	 * 设置 备注
	 * 
	 * @param String remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = StringUtils.trim(remarks);
	}
	
	/**
	 * 获取 备注
	 * 
	 * @return String remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	
	/**************************table prop  end  *********************************/
}