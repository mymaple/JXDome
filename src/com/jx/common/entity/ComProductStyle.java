package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComProductStyle extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 产品规格 主键id */
	@NotBlank(message="产品规格 主键id 不能为空", groups={ValidationEdit.class})
	private String productStyleId;
	
	/** 产品规格初始id */
	private String productStyleInitId;
		
	/** 商品id */
	@NotBlank(message="商品id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productId;
		
	/** 产品规格代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="产品规格代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String productStyleCode;
		
	/** 产品规格名称 */
	@NotBlank(message="产品规格名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productStyleName;
		
	/** 产品规格类型 */
	@NotBlank(message="产品规格类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productStyleType;
		
	/** 产品规格状态 */
	private String productStyleStatus;
		
	/** 库存数量 */
	@Pattern(regexp = Const.REG_COM_FFZS_STR, message="库存数量 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String stockNum;
		
	/** 原价 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="原价 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String originalPrice;
		
	/** 折扣 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="折扣 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String discountRate;
		
	/** 折扣优惠 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="折扣优惠 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String discountPrice;
		
	/** 现价 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="现价 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String currentPrice;
		
	
	
	/**
	 * 设置产品规格 主键id
	 * 
	 * @param String productStyleId
	 */
	public void setProductStyleId(String productStyleId) {
		this.productStyleId = MapleStringUtil.trim(productStyleId);
	}
	
	/**
	 * 获取产品规格 主键id
	 * 
	 * @return String productStyleId
	 */
	public String getProductStyleId() {
		return this.productStyleId;
	}
	
	/**
	 * 设置 产品规格初始id
	 * 
	 * @param String productStyleInitId
	 */
	public void setProductStyleInitId(String productStyleInitId) {
		this.productStyleInitId = MapleStringUtil.trim(productStyleInitId);
	}
	
	/**
	 * 获取 产品规格初始id
	 * 
	 * @return String productStyleInitId
	 */
	public String getProductStyleInitId() {
		return this.productStyleInitId;
	}
	
	/**
	 * 设置 商品id
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = MapleStringUtil.trim(productId);
	}
	
	/**
	 * 获取 商品id
	 * 
	 * @return String productId
	 */
	public String getProductId() {
		return this.productId;
	}
	
	/**
	 * 设置 产品规格代号
	 * 
	 * @param String productStyleCode
	 */
	public void setProductStyleCode(String productStyleCode) {
		this.productStyleCode = MapleStringUtil.trim(productStyleCode);
	}
	
	/**
	 * 获取 产品规格代号
	 * 
	 * @return String productStyleCode
	 */
	public String getProductStyleCode() {
		return this.productStyleCode;
	}
	
	/**
	 * 设置 产品规格名称
	 * 
	 * @param String productStyleName
	 */
	public void setProductStyleName(String productStyleName) {
		this.productStyleName = MapleStringUtil.trim(productStyleName);
	}
	
	/**
	 * 获取 产品规格名称
	 * 
	 * @return String productStyleName
	 */
	public String getProductStyleName() {
		return this.productStyleName;
	}
	
	/**
	 * 设置 产品规格类型
	 * 
	 * @param String productStyleType
	 */
	public void setProductStyleType(String productStyleType) {
		this.productStyleType = MapleStringUtil.trim(productStyleType);
	}
	
	/**
	 * 获取 产品规格类型
	 * 
	 * @return String productStyleType
	 */
	public String getProductStyleType() {
		return this.productStyleType;
	}
	
	/**
	 * 设置 产品规格状态
	 * 
	 * @param String productStyleStatus
	 */
	public void setProductStyleStatus(String productStyleStatus) {
		this.productStyleStatus = MapleStringUtil.trim(productStyleStatus);
	}
	
	/**
	 * 获取 产品规格状态
	 * 
	 * @return String productStyleStatus
	 */
	public String getProductStyleStatus() {
		return this.productStyleStatus;
	}
	
	/**
	 * 设置 库存数量
	 * 
	 * @param String stockNum
	 */
	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	
	/**
	 * 获取 库存数量
	 * 
	 * @return String stockNum
	 */
	public String getStockNum() {
		return this.stockNum;
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
	 * 设置 折扣
	 * 
	 * @param String discountRate
	 */
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	
	/**
	 * 获取 折扣
	 * 
	 * @return String discountRate
	 */
	public String getDiscountRate() {
		return this.discountRate;
	}
	
	/**
	 * 设置 折扣优惠
	 * 
	 * @param String discountPrice
	 */
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	/**
	 * 获取 折扣优惠
	 * 
	 * @return String discountPrice
	 */
	public String getDiscountPrice() {
		return this.discountPrice;
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
	
	/**************************table prop  end  *********************************/
}