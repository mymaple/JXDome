package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.background.config.BgPage;
import com.jx.common.util.DateUtil;
import com.jx.common.util.StringUtil;

public class BgUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 角色 */
	private BgRole bgRole; 
	
	/** 后台分页 */
	private BgPage bgPage; 
	
	
	/**
	 * 获取 角色
	 * 
	 * @return int userId
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
	 * 获取 后台分页
	 * 
	 * @return int userId
	 */
	public BgPage getBgPage() {
		return bgPage;
	}

	/**
	 * 设置 后台分页
	 * 
	 * @param BgPage bgPage
	 */
	public void setBgPage(BgPage bgPage) {
		this.bgPage = bgPage;
	}
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 后台用户表 主键id */
	private int userId;
	
	/** 用户名 */
	private String userName;
		
	/** 密码 */
	private String password;
		
	/** 真实姓名 */
	private String realName;
		
	/** 用户权限 */
	private String userRights;
		
	/** 角色id */
	private int roleId;
		
	/** 最后登录时间 */
	private Date lastLoginTime;
		
	/** 最后登录IP */
	private String lastLoginIp;
		
	/** 用户头像路径 */
	private String userIconSrc;
		
	/** 用户编号 */
	private String userNumber;
		
	/** 电子邮箱 */
	private String email;
		
	/** 手机号码 */
	private String phone;
		
	/** 状态 */
	private String status;
		
	/** 备注信息 */
	private String remarks;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 后台用户表 主键id
	 * 
	 * @param int userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取 后台用户表 主键id
	 * 
	 * @return int userId
	 */
	public int getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置 用户名
	 * 
	 * @param String userName
	 */
	public void setUserName(String userName) {
		this.userName = StringUtil.trim(userName);
	}
	
	/**
	 * 获取 用户名
	 * 
	 * @return String userName
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置 密码
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = StringUtil.trim(password);
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
	 * 设置 真实姓名
	 * 
	 * @param String realName
	 */
	public void setRealName(String realName) {
		this.realName = StringUtil.trim(realName);
	}
	
	/**
	 * 获取 真实姓名
	 * 
	 * @return String realName
	 */
	public String getRealName() {
		return this.realName;
	}
	
	/**
	 * 设置 用户权限
	 * 
	 * @param String userRights
	 */
	public void setUserRights(String userRights) {
		this.userRights = StringUtil.trim(userRights);
	}
	
	/**
	 * 获取 用户权限
	 * 
	 * @return String userRights
	 */
	public String getUserRights() {
		return this.userRights;
	}
	
	/**
	 * 设置 角色id
	 * 
	 * @param int roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 获取 角色id
	 * 
	 * @return int roleId
	 */
	public int getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 最后登录时间
	 * 
	 * @param Date lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	/**
	 * 获取 最后登录时间
	 * 
	 * @return Date lastLoginTime
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}	
		
	public void setLastLoginTimeStr(String lastLoginTimeStr) throws Exception{
		lastLoginTimeStr = StringUtil.trim(lastLoginTimeStr);
		if(!lastLoginTimeStr.equals("")){
			try{
				setLastLoginTime(DateUtil.parseDate(lastLoginTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getLastLoginTimeStr(){
		return DateUtil.getFormatedDateString(getLastLoginTime());
	}	
	
	/**
	 * 设置 最后登录IP
	 * 
	 * @param String lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = StringUtil.trim(lastLoginIp);
	}
	
	/**
	 * 获取 最后登录IP
	 * 
	 * @return String lastLoginIp
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	/**
	 * 设置 用户头像路径
	 * 
	 * @param String userIconSrc
	 */
	public void setUserIconSrc(String userIconSrc) {
		this.userIconSrc = StringUtil.trim(userIconSrc);
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
	 * 设置 用户编号
	 * 
	 * @param String userNumber
	 */
	public void setUserNumber(String userNumber) {
		this.userNumber = StringUtil.trim(userNumber);
	}
	
	/**
	 * 获取 用户编号
	 * 
	 * @return String userNumber
	 */
	public String getUserNumber() {
		return this.userNumber;
	}
	
	/**
	 * 设置 电子邮箱
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = StringUtil.trim(email);
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
		this.phone = StringUtil.trim(phone);
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
	 * 设置 状态
	 * 
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = StringUtil.trim(status);
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
	 * 设置 备注信息
	 * 
	 * @param String remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = StringUtil.trim(remarks);
	}
	
	/**
	 * 获取 备注信息
	 * 
	 * @return String remarks
	 */
	public String getRemarks() {
		return this.remarks;
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
		modifyTimeStr = StringUtil.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(DateUtil.parseDate(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getModifyTimeStr(){
		return DateUtil.getFormatedDateString(getModifyTime());
	}	
	
	
	public BgUser(){
		init();
	}
	
	public void init() {
		setUserId(0);
	
		setUserName("");
		setPassword("");
		setRealName("");
		setUserRights("");
		setRoleId(0);
		try {
			setLastLoginTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setLastLoginIp("");
		setUserIconSrc("");
		setUserNumber("");
		setEmail("");
		setPhone("");
		setStatus("");
		setRemarks("");
		try {
			setModifyTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************table prop  end  *********************************/
}