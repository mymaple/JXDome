package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComReceiveAddress extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 收货地址 主键id */
	@NotBlank(message="收货地址 主键id 不能为空", groups={ValidationEdit.class,ValidationWxEdit.class})
	private String receiveAddressId;
	
	/** 平台用户 */
	@NotBlank(message="平台用户 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserId;
		
	/** 收货人 */
	@NotBlank(message="收货人 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String receicerName;
		
	/** 手机号码 */
	@NotBlank(message="手机号码 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String phone;
		
	/** 省 */
	@NotBlank(message="省份 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String province;
		
	/** 城市 */
	@NotBlank(message="城市 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String city;
		
	/** 区 */
	@NotBlank(message="城区 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String district;
		
	/** 街道 */
	@NotBlank(message="街道 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String street;
		
	/** 详细地址 */
	@NotBlank(message="详细地址 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String detail;
		
	/** 默认状态 */
	@NotBlank(message="默认状态 不能为空", groups={ValidationAdd.class, ValidationEdit.class,ValidationWxAdd.class, ValidationWxEdit.class})
	private String defaultStatus;
		
	
	
	/**
	 * 设置收货地址 主键id
	 * 
	 * @param String receiveAddressId
	 */
	public void setReceiveAddressId(String receiveAddressId) {
		this.receiveAddressId = MapleStringUtil.trim(receiveAddressId);
	}
	
	/**
	 * 获取收货地址 主键id
	 * 
	 * @return String receiveAddressId
	 */
	public String getReceiveAddressId() {
		return this.receiveAddressId;
	}
	
	/**
	 * 设置 平台用户
	 * 
	 * @param String appUserId
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = MapleStringUtil.trim(appUserId);
	}
	
	/**
	 * 获取 平台用户
	 * 
	 * @return String appUserId
	 */
	public String getAppUserId() {
		return this.appUserId;
	}
	
	/**
	 * 设置 收货人
	 * 
	 * @param String receicerName
	 */
	public void setReceicerName(String receicerName) {
		this.receicerName = MapleStringUtil.trim(receicerName);
	}
	
	/**
	 * 获取 收货人
	 * 
	 * @return String receicerName
	 */
	public String getReceicerName() {
		return this.receicerName;
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
	 * 设置 省
	 * 
	 * @param String province
	 */
	public void setProvince(String province) {
		this.province = MapleStringUtil.trim(province);
	}
	
	/**
	 * 获取 省
	 * 
	 * @return String province
	 */
	public String getProvince() {
		return this.province;
	}
	
	/**
	 * 设置 城市
	 * 
	 * @param String city
	 */
	public void setCity(String city) {
		this.city = MapleStringUtil.trim(city);
	}
	
	/**
	 * 获取 城市
	 * 
	 * @return String city
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * 设置 区
	 * 
	 * @param String district
	 */
	public void setDistrict(String district) {
		this.district = MapleStringUtil.trim(district);
	}
	
	/**
	 * 获取 区
	 * 
	 * @return String district
	 */
	public String getDistrict() {
		return this.district;
	}
	
	/**
	 * 设置 街道
	 * 
	 * @param String street
	 */
	public void setStreet(String street) {
		this.street = MapleStringUtil.trim(street);
	}
	
	/**
	 * 获取 街道
	 * 
	 * @return String street
	 */
	public String getStreet() {
		return this.street;
	}
	
	/**
	 * 设置 详细地址
	 * 
	 * @param String detail
	 */
	public void setDetail(String detail) {
		this.detail = MapleStringUtil.trim(detail);
	}
	
	/**
	 * 获取 详细地址
	 * 
	 * @return String detail
	 */
	public String getDetail() {
		return this.detail;
	}
	
	/**
	 * 设置 默认状态
	 * 
	 * @param String defaultStatus
	 */
	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = MapleStringUtil.trim(defaultStatus);
	}
	
	/**
	 * 获取 默认状态
	 * 
	 * @return String defaultStatus
	 */
	public String getDefaultStatus() {
		return this.defaultStatus;
	}
	
	
	/**************************table prop  end  *********************************/
}