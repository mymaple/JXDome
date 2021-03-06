package com.jx.common.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;

public class ComProductCategory extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 路径 分类头像
	 */
	public static final String PATH_IMG_PRODUCTCATEGORY_HEADIMG = "uploadFiles/image/productCategory/headImg";
	
	/**
	 * 路径 长框图
	 */
	public static final String PATH_IMG_PRODUCTCATEGORY_IMG1 = "uploadFiles/image/productCategory/img1";
	
	/**
	 * 路径 滚播图
	 */
	public static final String PATH_IMG_PRODUCTCATEGORY_IMG2 = "uploadFiles/image/productCategory/img2";
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 商品分类 主键id */
	@NotBlank(message="商品分类 主键id 不能为空", groups={ValidationEdit.class})
	private String productCategoryId;
	
	/** 商品分类代号 */
	private String productCategoryCode;
		
	/** 商品分类名称 */
	@NotBlank(message="商品分类名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productCategoryName;
		
	/** 商品分类类型 */
	@NotBlank(message="商品分类类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productCategoryType;
		
	/** 商品分类状态 */
	private String productCategoryStatus;
		
	/** 分类头像 */
	@NotBlank(message="分类头像 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String headImgSrc;
		
	/** 长框图 */
	@NotBlank(message="长框图 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String imgSrc1;
		
	/** 滚播图 */
	@NotBlank(message="滚播图 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String imgSrc2;
		
	/** 摘要 */
	@NotBlank(message="摘要 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String summary;
		
	
	
	/**
	 * 设置商品分类 主键id
	 * 
	 * @param String productCategoryId
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = StringUtils.trim(productCategoryId);
	}
	
	/**
	 * 获取商品分类 主键id
	 * 
	 * @return String productCategoryId
	 */
	public String getProductCategoryId() {
		return this.productCategoryId;
	}
	
	/**
	 * 设置 商品分类代号
	 * 
	 * @param String productCategoryCode
	 */
	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = StringUtils.trim(productCategoryCode);
	}
	
	/**
	 * 获取 商品分类代号
	 * 
	 * @return String productCategoryCode
	 */
	public String getProductCategoryCode() {
		return this.productCategoryCode;
	}
	
	/**
	 * 设置 商品分类名称
	 * 
	 * @param String productCategoryName
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = StringUtils.trim(productCategoryName);
	}
	
	/**
	 * 获取 商品分类名称
	 * 
	 * @return String productCategoryName
	 */
	public String getProductCategoryName() {
		return this.productCategoryName;
	}
	
	/**
	 * 设置 商品分类类型
	 * 
	 * @param String productCategoryType
	 */
	public void setProductCategoryType(String productCategoryType) {
		this.productCategoryType = StringUtils.trim(productCategoryType);
	}
	
	/**
	 * 获取 商品分类类型
	 * 
	 * @return String productCategoryType
	 */
	public String getProductCategoryType() {
		return this.productCategoryType;
	}
	
	/**
	 * 设置 商品分类状态
	 * 
	 * @param String productCategoryStatus
	 */
	public void setProductCategoryStatus(String productCategoryStatus) {
		this.productCategoryStatus = StringUtils.trim(productCategoryStatus);
	}
	
	/**
	 * 获取 商品分类状态
	 * 
	 * @return String productCategoryStatus
	 */
	public String getProductCategoryStatus() {
		return this.productCategoryStatus;
	}
	
	/**
	 * 设置 分类头像
	 * 
	 * @param String headImgSrc
	 */
	public void setHeadImgSrc(String headImgSrc) {
		this.headImgSrc = StringUtils.trim(headImgSrc);
	}
	
	/**
	 * 获取 分类头像
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
	
	
	/**************************table prop  end  *********************************/
}