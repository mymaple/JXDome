package com.jx.common.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;

public class ComProductCategoryDetail extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 商品分类详情 主键id */
	@NotBlank(message="商品分类详情 主键id 不能为空", groups={ValidationEdit.class})
	private String productCategoryDetailId;
	
	/** 商品分类 id */
	@NotBlank(message="商品分类id 不能为空", groups={ValidationAdd.class})
	private String productCategoryId;
	
	/** 商品 */
	@NotBlank(message="商品 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productId;
		
	
	
	/**
	 * 设置商品分类详情 主键id
	 * 
	 * @param String productCategoryDetailId
	 */
	public void setProductCategoryDetailId(String productCategoryDetailId) {
		this.productCategoryDetailId = StringUtils.trim(productCategoryDetailId);
	}
	
	/**
	 * 获取商品分类详情 主键id
	 * 
	 * @return String productCategoryDetailId
	 */
	public String getProductCategoryDetailId() {
		return this.productCategoryDetailId;
	}
	
	/**
	 * 设置 商品分类 id 
	 * 
	 * @param String productCategoryId
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = StringUtils.trim(productCategoryId);
	}
	
	/**
	 * 获取 商品分类 id 
	 * 
	 * @return String productCategoryId
	 */
	public String getProductCategoryId() {
		return this.productCategoryId;
	}
	
	/**
	 * 设置 商品
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = StringUtils.trim(productId);
	}
	
	/**
	 * 获取 商品
	 * 
	 * @return String productId
	 */
	public String getProductId() {
		return this.productId;
	}
	
	
	/**************************table prop  end  *********************************/
}