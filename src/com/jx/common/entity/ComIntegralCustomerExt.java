package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComIntegralCustomerExt extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 积分客户扩展 主键id */
	@NotBlank(message="积分客户扩展 主键id 不能为空", groups={ValidationEdit.class})
	private String integralCustomerExtId;
	
	/** 积分客户 */
	@NotBlank(message="积分客户 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralCustomerId;
		
	/** 扩展属性名 */
	@NotBlank(message="扩展属性名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String name;
		
	/** 扩展属性值 */
	@NotBlank(message="扩展属性值 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String value;
		
	/** 扩展属性代号 */
	@NotBlank(message="扩展属性代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String code;
		
	
	
	/**
	 * 设置积分客户扩展 主键id
	 * 
	 * @param String integralCustomerExtId
	 */
	public void setIntegralCustomerExtId(String integralCustomerExtId) {
		this.integralCustomerExtId = MapleStringUtil.trim(integralCustomerExtId);
	}
	
	/**
	 * 获取积分客户扩展 主键id
	 * 
	 * @return String integralCustomerExtId
	 */
	public String getIntegralCustomerExtId() {
		return this.integralCustomerExtId;
	}
	
	/**
	 * 设置 积分客户
	 * 
	 * @param String integralCustomerId
	 */
	public void setIntegralCustomerId(String integralCustomerId) {
		this.integralCustomerId = MapleStringUtil.trim(integralCustomerId);
	}
	
	/**
	 * 获取 积分客户
	 * 
	 * @return String integralCustomerId
	 */
	public String getIntegralCustomerId() {
		return this.integralCustomerId;
	}
	
	/**
	 * 设置 扩展属性名
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = MapleStringUtil.trim(name);
	}
	
	/**
	 * 获取 扩展属性名
	 * 
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置 扩展属性值
	 * 
	 * @param String value
	 */
	public void setValue(String value) {
		this.value = MapleStringUtil.trim(value);
	}
	
	/**
	 * 获取 扩展属性值
	 * 
	 * @return String value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 设置 扩展属性代号
	 * 
	 * @param String code
	 */
	public void setCode(String code) {
		this.code = MapleStringUtil.trim(code);
	}
	
	/**
	 * 获取 扩展属性代号
	 * 
	 * @return String code
	 */
	public String getCode() {
		return this.code;
	}
	
	
	/**************************table prop  end  *********************************/
}