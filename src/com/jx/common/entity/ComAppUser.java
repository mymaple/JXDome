package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;

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
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 平台用户 主键id */
	@NotBlank(message="平台用户 主键id 不能为空", groups={ValidationEdit.class})
	private String appUserId;
	
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
		
	/** 电子邮箱 */
	@NotBlank(message="电子邮箱 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String email;
		
	/** 密码 */
	@NotBlank(message="密码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String password;
		
	/** 用户的标识 */
	@NotBlank(message="用户的标识 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String openId;
		
	/** 性别 */
	@NotBlank(message="性别 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sex;
		
	/** 用户头像 */
	@NotBlank(message="用户头像 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String headImgSrc;
		
	/** 生日 */
	private Date brithday;
		
	/** 上级id */
	@NotBlank(message="上级id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String parentId;
		
	/** 微信二维码 */
	@NotBlank(message="微信二维码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wxQRcodeSrc;
		
	/** 微信二维码有效期 */
	private Date wxQRcodeExpiry;
		
	/** 媒体文件id */
	@NotBlank(message="媒体文件id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mediaId;
		
	/** 媒体文件有效时间 */
	private Date mediaExpiry;
		
	
	
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
	 * 设置 用户的标识
	 * 
	 * @param String openId
	 */
	public void setOpenId(String openId) {
		this.openId = MapleStringUtil.trim(openId);
	}
	
	/**
	 * 获取 用户的标识
	 * 
	 * @return String openId
	 */
	public String getOpenId() {
		return this.openId;
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
	 * 设置 微信二维码
	 * 
	 * @param String wxQRcodeSrc
	 */
	public void setWxQRcodeSrc(String wxQRcodeSrc) {
		this.wxQRcodeSrc = MapleStringUtil.trim(wxQRcodeSrc);
	}
	
	/**
	 * 获取 微信二维码
	 * 
	 * @return String wxQRcodeSrc
	 */
	public String getWxQRcodeSrc() {
		return this.wxQRcodeSrc;
	}
	
	/**
	 * 设置 微信二维码有效期
	 * 
	 * @param Date wxQRcodeExpiry
	 */
	public void setWxQRcodeExpiry(Date wxQRcodeExpiry) {
		this.wxQRcodeExpiry = wxQRcodeExpiry;
	}
	
	/**
	 * 获取 微信二维码有效期
	 * 
	 * @return Date wxQRcodeExpiry
	 */
	public Date getWxQRcodeExpiry() {
		return this.wxQRcodeExpiry;
	}	
		
	public void setWxQRcodeExpiryStr(String wxQRcodeExpiryStr) throws Exception{
		wxQRcodeExpiryStr = MapleStringUtil.trim(wxQRcodeExpiryStr);
		if(!wxQRcodeExpiryStr.equals("")){
			try{
				setWxQRcodeExpiry(MapleDateUtil.parseDateStr(wxQRcodeExpiryStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getWxQRcodeExpiryStr(){
		return MapleDateUtil.formatDate(getWxQRcodeExpiry());
	}	
	
	/**
	 * 设置 媒体文件id
	 * 
	 * @param String mediaId
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = MapleStringUtil.trim(mediaId);
	}
	
	/**
	 * 获取 媒体文件id
	 * 
	 * @return String mediaId
	 */
	public String getMediaId() {
		return this.mediaId;
	}
	
	/**
	 * 设置 媒体文件有效时间
	 * 
	 * @param Date mediaExpiry
	 */
	public void setMediaExpiry(Date mediaExpiry) {
		this.mediaExpiry = mediaExpiry;
	}
	
	/**
	 * 获取 媒体文件有效时间
	 * 
	 * @return Date mediaExpiry
	 */
	public Date getMediaExpiry() {
		return this.mediaExpiry;
	}	
		
	public void setMediaExpiryStr(String mediaExpiryStr) throws Exception{
		mediaExpiryStr = MapleStringUtil.trim(mediaExpiryStr);
		if(!mediaExpiryStr.equals("")){
			try{
				setMediaExpiry(MapleDateUtil.parseDateStr(mediaExpiryStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getMediaExpiryStr(){
		return MapleDateUtil.formatDate(getMediaExpiry());
	}	
	
	
	/**************************table prop  end  *********************************/
}