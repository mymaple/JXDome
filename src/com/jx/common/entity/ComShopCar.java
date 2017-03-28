package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;

public class ComShopCar extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	ComProduct comProduct = new ComProduct();
	
	public ComProduct getComProduct() {
		return this.comProduct;
	}

	public void setComProduct(ComProduct comProduct) {
		this.comProduct = comProduct;
	}
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	

	/** 购物车 主键id */
	@NotBlank(message="购物车 主键id 不能为空", groups={ValidationEdit.class})
	private String shopCarId;
	
	/** 用户 */
	@NotBlank(message="用户 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserId;
		
	/** 商品规格 */
	@NotBlank(message="商品规格 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productStyleId;
		
	/** 数量 */
	@Pattern(regexp = Const.REG_COM_ZZS_STR, message="数量 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String count;
		
	/** 购物车状态 */
	@NotBlank(message="购物车状态 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String shopCarStatus;
		
	
	
	/**
	 * 设置购物车 主键id
	 * 
	 * @param String shopCarId
	 */
	public void setShopCarId(String shopCarId) {
		this.shopCarId = StringUtils.trim(shopCarId);
	}
	
	/**
	 * 获取购物车 主键id
	 * 
	 * @return String shopCarId
	 */
	public String getShopCarId() {
		return this.shopCarId;
	}
	
	/**
	 * 设置 用户
	 * 
	 * @param String appUserId
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = StringUtils.trim(appUserId);
	}
	
	/**
	 * 获取 用户
	 * 
	 * @return String appUserId
	 */
	public String getAppUserId() {
		return this.appUserId;
	}
	
	/**
	 * 设置 商品规格
	 * 
	 * @param String productStyleId
	 */
	public void setProductStyleId(String productStyleId) {
		this.productStyleId = StringUtils.trim(productStyleId);
	}
	
	/**
	 * 获取 商品规格
	 * 
	 * @return String productStyleId
	 */
	public String getProductStyleId() {
		return this.productStyleId;
	}
	
	/**
	 * 设置 数量
	 * 
	 * @param String count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
	/**
	 * 获取 数量
	 * 
	 * @return String count
	 */
	public String getCount() {
		return this.count;
	}
	
	/**
	 * 设置 购物车状态
	 * 01:加入购物车，02：生成订单，03：失效
	 * 
	 * @param String shopCarStatus
	 */
	public void setShopCarStatus(String shopCarStatus) {
		this.shopCarStatus = StringUtils.trim(shopCarStatus);
	}
	
	/**
	 * 获取 购物车状态
	 * 01:加入购物车，02：生成订单，03：失效
	 * 
	 * @return String shopCarStatus
	 */
	public String getShopCarStatus() {
		return this.shopCarStatus;
	}
	
	/**************************table prop  end  *********************************/
}