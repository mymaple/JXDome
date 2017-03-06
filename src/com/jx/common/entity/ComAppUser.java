package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class ComAppUser extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComAppUser> subComAppUserList;
	
	/** 子列表 路径*/
	private String subComAppUserPath;
	
	/** 是否有此平台用户 */
	private boolean hasAppUser;

	
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
	 * @return List<ComAppUser> subComAppUserList
	 */
	public List<ComAppUser> getSubComAppUserList() {
		return this.subComAppUserList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComAppUser> subComAppUserList
	 */
	public void setSubComAppUserList(List<ComAppUser> subComAppUserList) {
		this.subComAppUserList = subComAppUserList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComAppUserPath
	 */
	public String getSubComAppUserPath() {
		return this.subComAppUserPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComAppUserPath
	 */
	public void setSubComAppUserPath(String subComAppUserPath) {
		this.subComAppUserPath = MapleStringUtil.trim(subComAppUserPath);
	}
	
	/**
	 * 获取 是否有此平台用户 
	 * 
	 * @return boolean hasAppUser
	 */
	public boolean isHasAppUser() {
		return this.hasAppUser;
	}
	
	/**
	 * 设置 是否有此平台用户
	 * 
	 * @param boolean hasAppUser
	 */
	public void setHasAppUser(boolean hasAppUser) {
		this.hasAppUser = hasAppUser;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 平台用户 主键id */
	@NotBlank(message="平台用户 主键id 不能为空", groups={ValidationEdit.class})
	private String appUserId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 角色 */
	@NotBlank(message="角色 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String roleId;
		
	/** 平台用户代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="平台用户代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String appUserCode;
		
	/** 平台用户名称 */
	@NotBlank(message="平台用户名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserName;
		
	/** 平台用户类型 */
	@NotBlank(message="平台用户类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserType;
		
	/** 平台用户状态 */
	private String appUserStatus;
		
	/** 平台用户编号 */
	@NotBlank(message="平台用户编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserNum;
		
	/** 电话号码 */
	@NotBlank(message="电话号码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String phone;
		
	/** 密码 */
	@NotBlank(message="密码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String password;
		
	/** 性别 */
	@NotBlank(message="性别 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sex;
		
	/** 用户头像 */
	private String headImgSrc;
		
	/** 生日 */
	private Date brithday;
		
	/** 备注信息 */
	@NotBlank(message="备注信息 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String remarks;
		
	/** 级别 */
	private String level;
		
	
	
	/**
	 * 设置平台用户 主键id
	 * 
	 * @param String appUserId
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = MapleStringUtil.trim(appUserId);
	}
	
	/**
	 * 获取平台用户 主键id
	 * 
	 * @return String appUserId
	 */
	public String getAppUserId() {
		return this.appUserId;
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
	 * 设置 角色
	 * 
	 * @param String roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = MapleStringUtil.trim(roleId);
	}
	
	/**
	 * 获取 角色
	 * 
	 * @return String roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 平台用户代号
	 * 
	 * @param String appUserCode
	 */
	public void setAppUserCode(String appUserCode) {
		this.appUserCode = MapleStringUtil.trim(appUserCode);
	}
	
	/**
	 * 获取 平台用户代号
	 * 
	 * @return String appUserCode
	 */
	public String getAppUserCode() {
		return this.appUserCode;
	}
	
	/**
	 * 设置 平台用户名称
	 * 
	 * @param String appUserName
	 */
	public void setAppUserName(String appUserName) {
		this.appUserName = MapleStringUtil.trim(appUserName);
	}
	
	/**
	 * 获取 平台用户名称
	 * 
	 * @return String appUserName
	 */
	public String getAppUserName() {
		return this.appUserName;
	}
	
	/**
	 * 设置 平台用户类型
	 * 
	 * @param String appUserType
	 */
	public void setAppUserType(String appUserType) {
		this.appUserType = MapleStringUtil.trim(appUserType);
	}
	
	/**
	 * 获取 平台用户类型
	 * 
	 * @return String appUserType
	 */
	public String getAppUserType() {
		return this.appUserType;
	}
	
	/**
	 * 设置 平台用户状态
	 * 
	 * @param String appUserStatus
	 */
	public void setAppUserStatus(String appUserStatus) {
		this.appUserStatus = MapleStringUtil.trim(appUserStatus);
	}
	
	/**
	 * 获取 平台用户状态
	 * 
	 * @return String appUserStatus
	 */
	public String getAppUserStatus() {
		return this.appUserStatus;
	}
	
	/**
	 * 设置 平台用户编号
	 * 
	 * @param String appUserNum
	 */
	public void setAppUserNum(String appUserNum) {
		this.appUserNum = MapleStringUtil.trim(appUserNum);
	}
	
	/**
	 * 获取 平台用户编号
	 * 
	 * @return String appUserNum
	 */
	public String getAppUserNum() {
		return this.appUserNum;
	}
	
	/**
	 * 设置 电话号码
	 * 
	 * @param String phone
	 */
	public void setPhone(String phone) {
		this.phone = MapleStringUtil.trim(phone);
	}
	
	/**
	 * 获取 电话号码
	 * 
	 * @return String phone
	 */
	public String getPhone() {
		return this.phone;
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
	 * 设置 性别
	 * 
	 * @param String sex
	 */
	public void setSex(String sex) {
		this.sex = MapleStringUtil.trim(sex);
	}
	
	/**
	 * 获取 性别
	 * 
	 * @return String sex
	 */
	public String getSex() {
		return this.sex;
	}
	
	/**
	 * 设置 用户头像
	 * 
	 * @param String headImgSrc
	 */
	public void setHeadImgSrc(String headImgSrc) {
		this.headImgSrc = MapleStringUtil.trim(headImgSrc);
	}
	
	/**
	 * 获取 用户头像
	 * 
	 * @return String headImgSrc
	 */
	public String getHeadImgSrc() {
		return this.headImgSrc;
	}
	
	/**
	 * 设置 生日
	 * 
	 * @param Date brithday
	 */
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	
	/**
	 * 获取 生日
	 * 
	 * @return Date brithday
	 */
	public Date getBrithday() {
		return this.brithday;
	}	
		
	public void setBrithdayStr(String brithdayStr) throws Exception{
		brithdayStr = MapleStringUtil.trim(brithdayStr);
		if(!brithdayStr.equals("")){
			try{
				setBrithday(MapleDateUtil.parseDateStr(brithdayStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getBrithdayStr(){
		return MapleDateUtil.formatDate(getBrithday());
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
	
	/**
	 * 设置 级别
	 * 
	 * @param String level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 获取 级别
	 * 
	 * @return String level
	 */
	public String getLevel() {
		return this.level;
	}
	
	
	/**************************table prop  end  *********************************/
}