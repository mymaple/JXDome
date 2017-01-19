package com.jx.background.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class BgUser extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 角色 */
	private BgRole bgRole; 
	
	/** 是否为管理员 */
	private boolean isAdmin;
	
	
	/**
	 * 获取 角色
	 * 
	 * @return BgRole bgRole
	 */
	public BgRole getBgRole() {
		return this.bgRole;
	}
	
	/**
	 * 设置 角色
	 * 
	 * @param BgRole bgRole
	 */
	public void setBgRole(BgRole bgRole) {
		this.bgRole = bgRole;
	}

	
	/**
	 * 获取 是否为管理员
	 * 
	 * @return boolean isAdmin
	 */
	public boolean isAdmin() {
		return this.isAdmin;
	}

	/**
	 * 设置 是否为管理员
	 * 
	 * @param boolean isAdmin
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 后台用户 主键id */
	@NotBlank(message="后台用户 主键id 不能为空", groups={ValidationEdit.class})
	private String userId;
	
	/** 角色id */
	@NotBlank(message="角色id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String roleId;
		
	/** 后台用户代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="后台用户代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String userCode;
		
	/** 密码 */
	@NotBlank(message="密码 不能为空", groups={ValidationAdd.class})
	private String password;
		
	/** 后台用户名称 */
	@NotBlank(message="后台用户名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String userName;
		
	/** 后台用户类型 */
	@NotBlank(message="后台用户类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String userType;
		
	/** 后台用户状态 */
	private String userStatus;
		
	/** 用户头像路径 */
	private String userIconSrc;
		
	/** 电子邮箱 */
	@NotBlank(message="电子邮箱 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	@Email(message="电子邮箱 格式不正确", groups={ValidationAdd.class, ValidationEdit.class})
	private String email;
		
	/** 手机号码 */
	@Pattern(regexp = Const.REG_COM_PHONE_STR, message="手机号码 格式不正确", groups={ValidationAdd.class, ValidationEdit.class})
	private String phone;
		
	/** 备注信息 */
	private String remarks;
		
	
	
	/**
	 * 设置后台用户 主键id
	 * 
	 * @param String userId
	 */
	public void setUserId(String userId) {
		this.userId = MapleStringUtil.trim(userId);
	}
	
	/**
	 * 获取后台用户 主键id
	 * 
	 * @return String userId
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置 角色id
	 * 
	 * @param String roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = MapleStringUtil.trim(roleId);
	}
	
	/**
	 * 获取 角色id
	 * 
	 * @return String roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 后台用户代号
	 * 
	 * @param String userCode
	 */
	public void setUserCode(String userCode) {
		this.userCode = MapleStringUtil.trim(userCode);
	}
	
	/**
	 * 获取 后台用户代号
	 * 
	 * @return String userCode
	 */
	public String getUserCode() {
		return this.userCode;
	}
	
	/**
	 * 设置 密码
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = MapleStringUtil.trim(password);
	}
	
	/**
	 * 获取 密码
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 设置 后台用户名称
	 * 
	 * @param String userName
	 */
	public void setUserName(String userName) {
		this.userName = MapleStringUtil.trim(userName);
	}
	
	/**
	 * 获取 后台用户名称
	 * 
	 * @return String userName
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置 后台用户类型
	 * 
	 * @param String userType
	 */
	public void setUserType(String userType) {
		this.userType = MapleStringUtil.trim(userType);
	}
	
	/**
	 * 获取 后台用户类型
	 * 
	 * @return String userType
	 */
	public String getUserType() {
		return this.userType;
	}
	
	/**
	 * 设置 后台用户状态
	 * 
	 * @param String userStatus
	 */
	public void setUserStatus(String userStatus) {
		this.userStatus = MapleStringUtil.trim(userStatus);
	}
	
	/**
	 * 获取 后台用户状态
	 * 
	 * @return String userStatus
	 */
	public String getUserStatus() {
		return this.userStatus;
	}
	
	/**
	 * 设置 用户头像路径
	 * 
	 * @param String userIconSrc
	 */
	public void setUserIconSrc(String userIconSrc) {
		this.userIconSrc = MapleStringUtil.trim(userIconSrc);
	}
	
	/**
	 * 获取 用户头像路径
	 * 
	 * @return String userIconSrc
	 */
	public String getUserIconSrc() {
		return this.userIconSrc;
	}
	
	/**
	 * 设置 电子邮箱
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = MapleStringUtil.trim(email);
	}
	
	/**
	 * 获取 电子邮箱
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * 设置 手机号码
	 * 
	 * @param String phone
	 */
	public void setPhone(String phone) {
		this.phone = MapleStringUtil.trim(phone);
	}
	
	/**
	 * 获取 手机号码
	 * 
	 * @return String phone
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置 备注信息
	 * 
	 * @param String remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = MapleStringUtil.trim(remarks);
	}
	
	/**
	 * 获取 备注信息
	 * 
	 * @return String remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	
	/**************************table prop  end  *********************************/
}