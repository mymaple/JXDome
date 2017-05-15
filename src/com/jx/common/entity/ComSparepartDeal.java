package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleDateUtil.SDF;
import com.jx.common.util.MapleStringUtil;

public class ComSparepartDeal extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 零部件交易 主键id */
	@NotBlank(message="零部件交易 主键id 不能为空", groups={ValidationEdit.class})
	private String sparepartDealId;
	
	/** 零部件交易订单号 */
	@NotBlank(message="零部件交易订单号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sparepartDealCode;
		
	/** 零部件交易审核状态 */
	private String sparepartDealStatus;
		
	/** 零部件销售客户 */
	@NotBlank(message="零部件销售客户 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appUserId;
		
	/** 零部件 */
	@NotBlank(message="零部件 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sparepartId;
		
	/** 出售数量 */
	@Pattern(regexp = Const.REG_COM_FFZS_STR, message="出售数量 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String count;
		
	/** 交易金额 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="交易金额 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String dealAmt;
		
	/** 订单日期 */
	private Date orderTime;
		
	/** 审核人 */
	private String checkId;
		
	/** 审核结果 */
	private String remarks;
		
	/** 审核日期 */
	private Date checkTime;
		
	
	
	/**
	 * 设置零部件交易 主键id
	 * 
	 * @param String sparepartDealId
	 */
	public void setSparepartDealId(String sparepartDealId) {
		this.sparepartDealId = MapleStringUtil.trim(sparepartDealId);
	}
	
	/**
	 * 获取零部件交易 主键id
	 * 
	 * @return String sparepartDealId
	 */
	public String getSparepartDealId() {
		return this.sparepartDealId;
	}
	
	/**
	 * 设置 零部件交易订单号
	 * 
	 * @param String sparepartDealCode
	 */
	public void setSparepartDealCode(String sparepartDealCode) {
		this.sparepartDealCode = MapleStringUtil.trim(sparepartDealCode);
	}
	
	/**
	 * 获取 零部件交易订单号
	 * 
	 * @return String sparepartDealCode
	 */
	public String getSparepartDealCode() {
		return this.sparepartDealCode;
	}
	
	/**
	 * 设置 零部件交易审核状态
	 * 
	 * @param String sparepartDealStatus
	 */
	public void setSparepartDealStatus(String sparepartDealStatus) {
		this.sparepartDealStatus = MapleStringUtil.trim(sparepartDealStatus);
	}
	
	/**
	 * 获取 零部件交易审核状态
	 * 
	 * @return String sparepartDealStatus
	 */
	public String getSparepartDealStatus() {
		return this.sparepartDealStatus;
	}
	
	/**
	 * 设置 零部件销售客户
	 * 
	 * @param String appUserId
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = MapleStringUtil.trim(appUserId);
	}
	
	/**
	 * 获取 零部件销售客户
	 * 
	 * @return String appUserId
	 */
	public String getAppUserId() {
		return this.appUserId;
	}
	
	/**
	 * 设置 零部件
	 * 
	 * @param String sparepartId
	 */
	public void setSparepartId(String sparepartId) {
		this.sparepartId = MapleStringUtil.trim(sparepartId);
	}
	
	/**
	 * 获取 零部件
	 * 
	 * @return String sparepartId
	 */
	public String getSparepartId() {
		return this.sparepartId;
	}
	
	/**
	 * 设置 出售数量
	 * 
	 * @param String count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
	/**
	 * 获取 出售数量
	 * 
	 * @return String count
	 */
	public String getCount() {
		return this.count;
	}
	
	/**
	 * 设置 交易金额
	 * 
	 * @param String dealAmt
	 */
	public void setDealAmt(String dealAmt) {
		this.dealAmt = dealAmt;
	}
	
	/**
	 * 获取 交易金额
	 * 
	 * @return String dealAmt
	 */
	public String getDealAmt() {
		return this.dealAmt;
	}
	
	/**
	 * 设置 订单日期
	 * 
	 * @param Date orderTime
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	/**
	 * 获取 订单日期
	 * 
	 * @return Date orderTime
	 */
	public Date getOrderTime() {
		return this.orderTime;
	}	
		
	public void setOrderTimeStr(String orderTimeStr) throws Exception{
		orderTimeStr = MapleStringUtil.trim(orderTimeStr);
		if(!orderTimeStr.equals("")){
			try{
				setOrderTime(MapleDateUtil.parseDateStr(SDF.DAY, orderTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getOrderTimeStr(){
		return MapleDateUtil.formatDate(SDF.DAY, getOrderTime());
	}	
	
	/**
	 * 设置 审核人
	 * 
	 * @param String checkId
	 */
	public void setCheckId(String checkId) {
		this.checkId = MapleStringUtil.trim(checkId);
	}
	
	/**
	 * 获取 审核人
	 * 
	 * @return String checkId
	 */
	public String getCheckId() {
		return this.checkId;
	}
	
	/**
	 * 设置 审核结果
	 * 
	 * @param String remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = MapleStringUtil.trim(remarks);
	}
	
	/**
	 * 获取 审核结果
	 * 
	 * @return String remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	/**
	 * 设置 审核日期
	 * 
	 * @param Date checkTime
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	/**
	 * 获取 审核日期
	 * 
	 * @return Date checkTime
	 */
	public Date getCheckTime() {
		return this.checkTime;
	}	
		
	public void setCheckTimeStr(String checkTimeStr) throws Exception{
		checkTimeStr = MapleStringUtil.trim(checkTimeStr);
		if(!checkTimeStr.equals("")){
			try{
				setCheckTime(MapleDateUtil.parseDateStr(checkTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getCheckTimeStr(){
		return MapleDateUtil.formatDate(getCheckTime());
	}	
	
	
	/**************************table prop  end  *********************************/
}