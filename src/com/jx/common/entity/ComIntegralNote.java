package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComIntegralNote extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 积分记录 主键id */
	@NotBlank(message="积分记录 主键id 不能为空", groups={ValidationEdit.class})
	private String integralNoteId;
	
	/** 积分记录代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="积分记录代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String integralNoteCode;
		
	/** 积分记录名称 */
	@NotBlank(message="积分记录名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralNoteName;
		
	/** 积分记录类型 */
	@NotBlank(message="积分记录类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralNoteType;
		
	/** 积分交易状态 */
	private String integralDealStatus;
		
	/** 积分交易数量 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="积分交易数量 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralDealCount;
		
	/** 交易前积分数量 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="交易前积分数量 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralCountBefore;
		
	/** 交易后积分数量 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="交易后积分数量 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralCountAfter;
		
	
	
	/**
	 * 设置积分记录 主键id
	 * 
	 * @param String integralNoteId
	 */
	public void setIntegralNoteId(String integralNoteId) {
		this.integralNoteId = MapleStringUtil.trim(integralNoteId);
	}
	
	/**
	 * 获取积分记录 主键id
	 * 
	 * @return String integralNoteId
	 */
	public String getIntegralNoteId() {
		return this.integralNoteId;
	}
	
	/**
	 * 设置 积分记录代号
	 * 
	 * @param String integralNoteCode
	 */
	public void setIntegralNoteCode(String integralNoteCode) {
		this.integralNoteCode = MapleStringUtil.trim(integralNoteCode);
	}
	
	/**
	 * 获取 积分记录代号
	 * 
	 * @return String integralNoteCode
	 */
	public String getIntegralNoteCode() {
		return this.integralNoteCode;
	}
	
	/**
	 * 设置 积分记录名称
	 * 
	 * @param String integralNoteName
	 */
	public void setIntegralNoteName(String integralNoteName) {
		this.integralNoteName = MapleStringUtil.trim(integralNoteName);
	}
	
	/**
	 * 获取 积分记录名称
	 * 
	 * @return String integralNoteName
	 */
	public String getIntegralNoteName() {
		return this.integralNoteName;
	}
	
	/**
	 * 设置 积分记录类型
	 * 
	 * @param String integralNoteType
	 */
	public void setIntegralNoteType(String integralNoteType) {
		this.integralNoteType = MapleStringUtil.trim(integralNoteType);
	}
	
	/**
	 * 获取 积分记录类型
	 * 
	 * @return String integralNoteType
	 */
	public String getIntegralNoteType() {
		return this.integralNoteType;
	}
	
	/**
	 * 设置 积分交易状态
	 * 
	 * @param String integralDealStatus
	 */
	public void setIntegralDealStatus(String integralDealStatus) {
		this.integralDealStatus = MapleStringUtil.trim(integralDealStatus);
	}
	
	/**
	 * 获取 积分交易状态
	 * 
	 * @return String integralDealStatus
	 */
	public String getIntegralDealStatus() {
		return this.integralDealStatus;
	}
	
	/**
	 * 设置 积分交易数量
	 * 
	 * @param String integralDealCount
	 */
	public void setIntegralDealCount(String integralDealCount) {
		this.integralDealCount = integralDealCount;
	}
	
	/**
	 * 获取 积分交易数量
	 * 
	 * @return String integralDealCount
	 */
	public String getIntegralDealCount() {
		return this.integralDealCount;
	}
	
	/**
	 * 设置 交易前积分数量
	 * 
	 * @param String integralCountBefore
	 */
	public void setIntegralCountBefore(String integralCountBefore) {
		this.integralCountBefore = integralCountBefore;
	}
	
	/**
	 * 获取 交易前积分数量
	 * 
	 * @return String integralCountBefore
	 */
	public String getIntegralCountBefore() {
		return this.integralCountBefore;
	}
	
	/**
	 * 设置 交易后积分数量
	 * 
	 * @param String integralCountAfter
	 */
	public void setIntegralCountAfter(String integralCountAfter) {
		this.integralCountAfter = integralCountAfter;
	}
	
	/**
	 * 获取 交易后积分数量
	 * 
	 * @return String integralCountAfter
	 */
	public String getIntegralCountAfter() {
		return this.integralCountAfter;
	}
	
	
	/**************************table prop  end  *********************************/
}