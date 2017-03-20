package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;

public class ComOrderDetail extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 订单商品 主键id */
	@NotBlank(message="订单商品 主键id 不能为空", groups={ValidationEdit.class})
	private String orderDetailId;
	
	/** 订单商品 id */
	@NotBlank(message="订单商品id 不能为空", groups={ValidationAdd.class})
	private String orderId;
	
	/** 商品Id */
	@NotBlank(message="商品Id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productId;
		
	/** 商品名称 */
	@NotBlank(message="商品名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productName;
		
	/** 摘要 */
	@NotBlank(message="摘要 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String summary;
		
	/** 商品类型名称 */
	@NotBlank(message="商品类型名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productStyleName;
		
	/** 产品头像 */
	@NotBlank(message="产品头像 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String headImgSrc;
		
	/** 原价 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="原价 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String originalPrice;
		
	/** 现价 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="现价 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String currentPrice;
		
	/** 购买数量 */
	@Pattern(regexp = Const.REG_COM_FFZS_STR, message="购买数量 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String count;
		
	
	
	/**
	 * 设置订单商品 主键id
	 * 
	 * @param String orderDetailId
	 */
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = StringUtils.trim(orderDetailId);
	}
	
	/**
	 * 获取订单商品 主键id
	 * 
	 * @return String orderDetailId
	 */
	public String getOrderDetailId() {
		return this.orderDetailId;
	}
	
	/**
	 * 设置 订单商品 id 
	 * 
	 * @param String orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = StringUtils.trim(orderId);
	}
	
	/**
	 * 获取 订单商品 id 
	 * 
	 * @return String orderId
	 */
	public String getOrderId() {
		return this.orderId;
	}
	
	/**
	 * 设置 商品Id
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = StringUtils.trim(productId);
	}
	
	/**
	 * 获取 商品Id
	 * 
	 * @return String productId
	 */
	public String getProductId() {
		return this.productId;
	}
	
	/**
	 * 设置 商品名称
	 * 
	 * @param String productName
	 */
	public void setProductName(String productName) {
		this.productName = StringUtils.trim(productName);
	}
	
	/**
	 * 获取 商品名称
	 * 
	 * @return String productName
	 */
	public String getProductName() {
		return this.productName;
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
	 * 设置 商品类型名称
	 * 
	 * @param String productStyleName
	 */
	public void setProductStyleName(String productStyleName) {
		this.productStyleName = StringUtils.trim(productStyleName);
	}
	
	/**
	 * 获取 商品类型名称
	 * 
	 * @return String productStyleName
	 */
	public String getProductStyleName() {
		return this.productStyleName;
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
	 * 设置 原价
	 * 
	 * @param String originalPrice
	 */
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	/**
	 * 获取 原价
	 * 
	 * @return String originalPrice
	 */
	public String getOriginalPrice() {
		return this.originalPrice;
	}
	
	
	/**
	 * 设置 现价
	 * 
	 * @param String currentPrice
	 */
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	/**
	 * 获取 现价
	 * 
	 * @return String currentPrice
	 */
	public String getCurrentPrice() {
		return this.currentPrice;
	}
	
	
	/**
	 * 设置 购买数量
	 * 
	 * @param String count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
	/**
	 * 获取 购买数量
	 * 
	 * @return String count
	 */
	public String getCount() {
		return this.count;
	}
	
	
	/**************************table prop  end  *********************************/
}