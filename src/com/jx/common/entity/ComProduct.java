package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;

public class ComProduct extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 路径 商品 头像
	 */
	public static final String PATH_IMG_PRODUCT_HEADIMG = "uploadFiles/image/product/headImg";
	
	/**
	 * 路径 商品 长框图
	 */
	public static final String PATH_IMG_PRODUCT_IMG1 = "uploadFiles/image/product/img1";
	
	/**
	 * 路径 商品 滚播图
	 */
	public static final String PATH_IMG_PRODUCT_IMG2 = "uploadFiles/image/product/img2";
	
	/**
	 * 路径 商品 详情图
	 */
	public static final String PATH_IMG_PRODUCT_IMG3 = "uploadFiles/image/product/img3";
	
	
	/**************************custom prop satrt********************************/
	
	/** 产品 规格 */
	private ComProductStyle comProductStyle ;
	
	/**
	 * 获得 产品 规格
	 * 
	 * @param ComProductStyle comProductStyle
	 */
	public ComProductStyle getComProductStyle() {
		return this.comProductStyle;
	}
	
	/**
	 * 设置 产品 规格
	 * 
	 * @param ComProductStyle comProductStyle
	 */
	public void setComProductStyle(ComProductStyle comProductStyle) {
		this.comProductStyle = comProductStyle;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 产品 主键id */
	@NotBlank(message="产品 主键id 不能为空", groups={ValidationEdit.class})
	private String productId;
	
	/** 供应商Id */
	@NotBlank(message="供应商Id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String supplierId;
		
	/** 产品代号 */
	private String productCode;
		
	/** 产品名称 */
	@NotBlank(message="产品名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productName;
		
	/** 产品类型 */
	@NotBlank(message="产品类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productType;
		
	/** 产品状态 */
	@NotBlank(message="产品状态不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productStatus;
		
	/** 产品模型 */
	@NotBlank(message="产品模型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productModel;
		
	/** 摘要 */
	@NotBlank(message="摘要 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String summary;
		
	/** 简介 */
	@NotBlank(message="简介 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String introduction;
		
	/** 产品头像 */
	@NotBlank(message="产品头像 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String headImgSrc;
		
	/** 长框图 */
	@NotBlank(message="长框图 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String imgSrc1;
		
	/** 滚播图 */
	@NotBlank(message="滚播图 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String imgSrc2;
		
	/** 详情图 */
	@NotBlank(message="详情图 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String imgSrc3;
		
	
	
	/**
	 * 设置产品 主键id
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = StringUtils.trim(productId);
	}
	
	/**
	 * 获取产品 主键id
	 * 
	 * @return String productId
	 */
	public String getProductId() {
		return this.productId;
	}
	
	/**
	 * 设置 供应商Id
	 * 
	 * @param String supplierId
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = StringUtils.trim(supplierId);
	}
	
	/**
	 * 获取 供应商Id
	 * 
	 * @return String supplierId
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	/**
	 * 设置 产品代号
	 * 
	 * @param String productCode
	 */
	public void setProductCode(String productCode) {
		this.productCode = StringUtils.trim(productCode);
	}
	
	/**
	 * 获取 产品代号
	 * 
	 * @return String productCode
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	/**
	 * 设置 产品名称
	 * 
	 * @param String productName
	 */
	public void setProductName(String productName) {
		this.productName = StringUtils.trim(productName);
	}
	
	/**
	 * 获取 产品名称
	 * 
	 * @return String productName
	 */
	public String getProductName() {
		return this.productName;
	}
	
	/**
	 * 设置 产品类型
	 * 
	 * @param String productType
	 */
	public void setProductType(String productType) {
		this.productType = StringUtils.trim(productType);
	}
	
	/**
	 * 获取 产品类型
	 * 
	 * @return String productType
	 */
	public String getProductType() {
		return this.productType;
	}
	
	/**
	 * 设置 产品状态
	 * 
	 * @param String productStatus
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = StringUtils.trim(productStatus);
	}
	
	/**
	 * 获取 产品状态
	 * 
	 * @return String productStatus
	 */
	public String getProductStatus() {
		return this.productStatus;
	}
	
	/**
	 * 设置 产品模型
	 * 
	 * @param String productModel
	 */
	public void setProductModel(String productModel) {
		this.productModel = StringUtils.trim(productModel);
	}
	
	/**
	 * 获取 产品模型
	 * 
	 * @return String productModel
	 */
	public String getProductModel() {
		return this.productModel;
	}
	
	/**
	 * 设置 摘要
	 * 
	 * @param String summary
	 */
	public void setSummary(String summary) {
		this.summary = StringUtils.trim(summary);
	}
	
	/**
	 * 获取 摘要
	 * 
	 * @return String summary
	 */
	public String getSummary() {
		return this.summary;
	}
	
	/**
	 * 设置 简介
	 * 
	 * @param String introduction
	 */
	public void setIntroduction(String introduction) {
		this.introduction = StringUtils.trim(introduction);
	}
	
	/**
	 * 获取 简介
	 * 
	 * @return String introduction
	 */
	public String getIntroduction() {
		return this.introduction;
	}
	
	/**
	 * 设置 产品头像
	 * 
	 * @param String headImgSrc
	 */
	public void setHeadImgSrc(String headImgSrc) {
		this.headImgSrc = StringUtils.trim(headImgSrc);
	}
	
	/**
	 * 获取 产品头像
	 * 
	 * @return String headImgSrc
	 */
	public String getHeadImgSrc() {
		return this.headImgSrc;
	}
	
	/**
	 * 设置 长框图
	 * 
	 * @param String imgSrc1
	 */
	public void setImgSrc1(String imgSrc1) {
		this.imgSrc1 = StringUtils.trim(imgSrc1);
	}
	
	/**
	 * 获取 长框图
	 * 
	 * @return String imgSrc1
	 */
	public String getImgSrc1() {
		return this.imgSrc1;
	}
	
	/**
	 * 设置 滚播图
	 * 
	 * @param String imgSrc2
	 */
	public void setImgSrc2(String imgSrc2) {
		this.imgSrc2 = StringUtils.trim(imgSrc2);
	}
	
	/**
	 * 获取 滚播图
	 * 
	 * @return String imgSrc2
	 */
	public String getImgSrc2() {
		return this.imgSrc2;
	}
	
	/**
	 * 设置 详情图
	 * 
	 * @param String imgSrc3
	 */
	public void setImgSrc3(String imgSrc3) {
		this.imgSrc3 = StringUtils.trim(imgSrc3);
	}
	
	/**
	 * 获取 详情图
	 * 
	 * @return String imgSrc3
	 */
	public String getImgSrc3() {
		return this.imgSrc3;
	}
	
	
	/**************************table prop  end  *********************************/
}