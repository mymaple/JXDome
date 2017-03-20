package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleDateUtil;

public class ComOrder extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 订单 主键id */
	@NotBlank(message="订单 主键id 不能为空", groups={ValidationEdit.class})
	private String orderId;
	
	/** 订单代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="订单代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String orderCode;
		
	/** 订单名称 */
	@NotBlank(message="订单名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String orderName;
		
	/** 订单类型 */
	@NotBlank(message="订单类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String orderType;
		
	/** 订单状态 */
	@NotBlank(message="订单状态 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String orderStatus;
		
	/** 订单商品总数 */
	@Pattern(regexp = Const.REG_COM_FFZS_STR, message="订单商品总数 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String orderProductCount;
		
	/** 商品总价 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="商品总价 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String allPrice;
		
	/** 运费 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="运费 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String freight;
		
	/** 总优惠 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="总优惠 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String allDisPrice;
		
	/** 钱包支付 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="钱包支付 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String walletPay;
		
	/** 实付款 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="实付款 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String allActPrice;
		
	/** 供应商 */
	private String supplierName;
		
	/** 供应商id */
	@NotBlank(message="供应商id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String supplierId;
		
	/** 收货地址 */
	@NotBlank(message="收货地址 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String receiveAddressId;
		
	/** 付款时间 */
	private Date payTime;
		
	/** 发货时间 */
	private Date sendTime;
		
	/** 交易号 */
	@NotBlank(message="交易号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String tradeNum;
		
	/** 付款方式 */
	@NotBlank(message="付款方式 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String payMethod;
		
	/** 物流公司 */
	@NotBlank(message="物流公司 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wlgs;
		
	/** 运单编号 */
	@NotBlank(message="运单编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wlNum;
		
	
	
	/**
	 * 设置订单 主键id
	 * 
	 * @param String orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = StringUtils.trim(orderId);
	}
	
	/**
	 * 获取订单 主键id
	 * 
	 * @return String orderId
	 */
	public String getOrderId() {
		return this.orderId;
	}
	
	/**
	 * 设置 订单代号
	 * 
	 * @param String orderCode
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = StringUtils.trim(orderCode);
	}
	
	/**
	 * 获取 订单代号
	 * 
	 * @return String orderCode
	 */
	public String getOrderCode() {
		return this.orderCode;
	}
	
	/**
	 * 设置 订单名称
	 * 
	 * @param String orderName
	 */
	public void setOrderName(String orderName) {
		this.orderName = StringUtils.trim(orderName);
	}
	
	/**
	 * 获取 订单名称
	 * 
	 * @return String orderName
	 */
	public String getOrderName() {
		return this.orderName;
	}
	
	/**
	 * 设置 订单类型
	 * 
	 * @param String orderType
	 */
	public void setOrderType(String orderType) {
		this.orderType = StringUtils.trim(orderType);
	}
	
	/**
	 * 获取 订单类型
	 * 
	 * @return String orderType
	 */
	public String getOrderType() {
		return this.orderType;
	}
	
	/**
	 * 设置 订单状态
	 * 
	 * @param String orderStatus
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = StringUtils.trim(orderStatus);
	}
	
	/**
	 * 获取 订单状态
	 * 
	 * @return String orderStatus
	 */
	public String getOrderStatus() {
		return this.orderStatus;
	}
	
	/**
	 * 设置 订单商品总数
	 * 
	 * @param String orderProductCount
	 */
	public void setOrderProductCount(String orderProductCount) {
		this.orderProductCount = orderProductCount;
	}
	
	/**
	 * 获取 订单商品总数
	 * 
	 * @return String orderProductCount
	 */
	public String getOrderProductCount() {
		return this.orderProductCount;
	}
	
	/**
	 * 设置 商品总价
	 * 
	 * @param String allPrice
	 */
	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}
	
	/**
	 * 获取 商品总价
	 * 
	 * @return String allPrice
	 */
	public String getAllPrice() {
		return this.allPrice;
	}
	
	
	/**
	 * 设置 运费
	 * 
	 * @param String freight
	 */
	public void setFreight(String freight) {
		this.freight = freight;
	}
	
	/**
	 * 获取 运费
	 * 
	 * @return String freight
	 */
	public String getFreight() {
		return this.freight;
	}
	
	
	/**
	 * 设置 总优惠
	 * 
	 * @param String allDisPrice
	 */
	public void setAllDisPrice(String allDisPrice) {
		this.allDisPrice = allDisPrice;
	}
	
	/**
	 * 获取 总优惠
	 * 
	 * @return String allDisPrice
	 */
	public String getAllDisPrice() {
		return this.allDisPrice;
	}
	
	
	/**
	 * 设置 钱包支付
	 * 
	 * @param String walletPay
	 */
	public void setWalletPay(String walletPay) {
		this.walletPay = walletPay;
	}
	
	/**
	 * 获取 钱包支付
	 * 
	 * @return String walletPay
	 */
	public String getWalletPay() {
		return this.walletPay;
	}
	
	
	/**
	 * 设置 实付款
	 * 
	 * @param String allActPrice
	 */
	public void setAllActPrice(String allActPrice) {
		this.allActPrice = allActPrice;
	}
	
	/**
	 * 获取 实付款
	 * 
	 * @return String allActPrice
	 */
	public String getAllActPrice() {
		return this.allActPrice;
	}
	
	
	/**
	 * 设置 供应商
	 * 
	 * @param String supplierName
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = StringUtils.trim(supplierName);
	}
	
	/**
	 * 获取 供应商
	 * 
	 * @return String supplierName
	 */
	public String getSupplierName() {
		return this.supplierName;
	}
	
	/**
	 * 设置 供应商id
	 * 
	 * @param String supplierId
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = StringUtils.trim(supplierId);
	}
	
	/**
	 * 获取 供应商id
	 * 
	 * @return String supplierId
	 */
	public String getSupplierId() {
		return this.supplierId;
	}
	
	/**
	 * 设置 收货地址
	 * 
	 * @param String receiveAddressId
	 */
	public void setReceiveAddressId(String receiveAddressId) {
		this.receiveAddressId = StringUtils.trim(receiveAddressId);
	}
	
	/**
	 * 获取 收货地址
	 * 
	 * @return String receiveAddressId
	 */
	public String getReceiveAddressId() {
		return this.receiveAddressId;
	}
	
	/**
	 * 设置 付款时间
	 * 
	 * @param Date payTime
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * 获取 付款时间
	 * 
	 * @return Date payTime
	 */
	public Date getPayTime() {
		return this.payTime;
	}	
		
	public void setPayTimeStr(String payTimeStr) throws Exception{
		payTimeStr = StringUtils.trim(payTimeStr);
		if(!payTimeStr.equals("")){
			try{
				setPayTime(MapleDateUtil.parseDateStr(payTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getPayTimeStr(){
		return MapleDateUtil.formatDate(getPayTime());
	}	
	
	/**
	 * 设置 发货时间
	 * 
	 * @param Date sendTime
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	/**
	 * 获取 发货时间
	 * 
	 * @return Date sendTime
	 */
	public Date getSendTime() {
		return this.sendTime;
	}	
		
	public void setSendTimeStr(String sendTimeStr) throws Exception{
		sendTimeStr = StringUtils.trim(sendTimeStr);
		if(!sendTimeStr.equals("")){
			try{
				setSendTime(MapleDateUtil.parseDateStr(sendTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getSendTimeStr(){
		return MapleDateUtil.formatDate(getSendTime());
	}	
	
	/**
	 * 设置 交易号
	 * 
	 * @param String tradeNum
	 */
	public void setTradeNum(String tradeNum) {
		this.tradeNum = StringUtils.trim(tradeNum);
	}
	
	/**
	 * 获取 交易号
	 * 
	 * @return String tradeNum
	 */
	public String getTradeNum() {
		return this.tradeNum;
	}
	
	/**
	 * 设置 付款方式
	 * 
	 * @param String payMethod
	 */
	public void setPayMethod(String payMethod) {
		this.payMethod = StringUtils.trim(payMethod);
	}
	
	/**
	 * 获取 付款方式
	 * 
	 * @return String payMethod
	 */
	public String getPayMethod() {
		return this.payMethod;
	}
	
	/**
	 * 设置 物流公司
	 * 
	 * @param String wlgs
	 */
	public void setWlgs(String wlgs) {
		this.wlgs = StringUtils.trim(wlgs);
	}
	
	/**
	 * 获取 物流公司
	 * 
	 * @return String wlgs
	 */
	public String getWlgs() {
		return this.wlgs;
	}
	
	/**
	 * 设置 运单编号
	 * 
	 * @param String wlNum
	 */
	public void setWlNum(String wlNum) {
		this.wlNum = StringUtils.trim(wlNum);
	}
	
	/**
	 * 获取 运单编号
	 * 
	 * @return String wlNum
	 */
	public String getWlNum() {
		return this.wlNum;
	}
	
	
	/**************************table prop  end  *********************************/
}