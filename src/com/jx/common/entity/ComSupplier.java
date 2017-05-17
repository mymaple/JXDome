package com.jx.common.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;

public class ComSupplier extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 供应商 主键id */
	@NotBlank(message="供应商 主键id 不能为空", groups={ValidationEdit.class})
	private String supplierId;
	
	/** 供应商代号 */
	private String supplierCode;
		
	/** 供应商名称 */
	@NotBlank(message="供应商名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String supplierName;
		
	/** 供应商类型 */
	@NotBlank(message="供应商类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String supplierType;
		
	/** 供应商状态 */
	private String supplierStatus;
		
	/** 联系人 */
	@NotBlank(message="联系人 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String linkman;
		
	/** 联系电话 */
	@NotBlank(message="联系电话 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String linkPhone;
		
	/** 备注 */
	@NotBlank(message="备注 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String remarks;
		
	
	
	/**
	 * 设置供应商 主键id
	 * 
	 * @param String supplierId
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = StringUtils.trim(supplierId);
	}
	
	/**
	 * 获取供应商 主键id
	 * 
	 * @return String supplierId
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	/**
	 * 设置 供应商代号
	 * 
	 * @param String supplierCode
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = StringUtils.trim(supplierCode);
	}
	
	/**
	 * 获取 供应商代号
	 * 
	 * @return String supplierCode
	 */
	public String getSupplierCode() {
		return this.supplierCode;
	}
	
	/**
	 * 设置 供应商名称
	 * 
	 * @param String supplierName
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = StringUtils.trim(supplierName);
	}
	
	/**
	 * 获取 供应商名称
	 * 
	 * @return String supplierName
	 */
	public String getSupplierName() {
		return this.supplierName;
	}
	
	/**
	 * 设置 供应商类型
	 * 
	 * @param String supplierType
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = StringUtils.trim(supplierType);
	}
	
	/**
	 * 获取 供应商类型
	 * 
	 * @return String supplierType
	 */
	public String getSupplierType() {
		return this.supplierType;
	}
	
	/**
	 * 设置 供应商状态
	 * 
	 * @param String supplierStatus
	 */
	public void setSupplierStatus(String supplierStatus) {
		this.supplierStatus = StringUtils.trim(supplierStatus);
	}
	
	/**
	 * 获取 供应商状态
	 * 
	 * @return String supplierStatus
	 */
	public String getSupplierStatus() {
		return this.supplierStatus;
	}
	
	/**
	 * 设置 联系人
	 * 
	 * @param String linkman
	 */
	public void setLinkman(String linkman) {
		this.linkman = StringUtils.trim(linkman);
	}
	
	/**
	 * 获取 联系人
	 * 
	 * @return String linkman
	 */
	public String getLinkman() {
		return this.linkman;
	}
	
	/**
	 * 设置 联系电话
	 * 
	 * @param String linkPhone
	 */
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = StringUtils.trim(linkPhone);
	}
	
	/**
	 * 获取 联系电话
	 * 
	 * @return String linkPhone
	 */
	public String getLinkPhone() {
		return this.linkPhone;
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