package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComSparepart extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 零配件 主键id */
	@NotBlank(message="零配件 主键id 不能为空", groups={ValidationEdit.class})
	private String sparepartId;
	
	/** 零配件代号 */
	@NotBlank(message="零配件代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String sparepartCode;
		
	/** 零配件名称 */
	@NotBlank(message="零配件名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sparepartName;
		
	/** 零配件类型 */
	@NotBlank(message="零配件类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String sparepartType;
		
	/** 零配件状态 */
	private String sparepartStatus;
		
	/** 总积分 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="总积分 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String allIntegral;
		
	/** 大区经理可获积分 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="大区经理可获积分 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integral1;
		
	/** 小区经理可获积分 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="小区经理可获积分 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integral2;
		
	/** 4S店可获积分 */
	@Pattern(regexp = Const.REG_COM_FFXS_STR, message="4S店可获积分 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
	private String integral3;
		
	
	
	/**
	 * 设置零配件 主键id
	 * 
	 * @param String sparepartId
	 */
	public void setSparepartId(String sparepartId) {
		this.sparepartId = MapleStringUtil.trim(sparepartId);
	}
	
	/**
	 * 获取零配件 主键id
	 * 
	 * @return String sparepartId
	 */
	public String getSparepartId() {
		return this.sparepartId;
	}
	
	/**
	 * 设置 零配件代号
	 * 
	 * @param String sparepartCode
	 */
	public void setSparepartCode(String sparepartCode) {
		this.sparepartCode = MapleStringUtil.trim(sparepartCode);
	}
	
	/**
	 * 获取 零配件代号
	 * 
	 * @return String sparepartCode
	 */
	public String getSparepartCode() {
		return this.sparepartCode;
	}
	
	/**
	 * 设置 零配件名称
	 * 
	 * @param String sparepartName
	 */
	public void setSparepartName(String sparepartName) {
		this.sparepartName = MapleStringUtil.trim(sparepartName);
	}
	
	/**
	 * 获取 零配件名称
	 * 
	 * @return String sparepartName
	 */
	public String getSparepartName() {
		return this.sparepartName;
	}
	
	/**
	 * 设置 零配件类型
	 * 
	 * @param String sparepartType
	 */
	public void setSparepartType(String sparepartType) {
		this.sparepartType = MapleStringUtil.trim(sparepartType);
	}
	
	/**
	 * 获取 零配件类型
	 * 
	 * @return String sparepartType
	 */
	public String getSparepartType() {
		return this.sparepartType;
	}
	
	/**
	 * 设置 零配件状态
	 * 
	 * @param String sparepartStatus
	 */
	public void setSparepartStatus(String sparepartStatus) {
		this.sparepartStatus = MapleStringUtil.trim(sparepartStatus);
	}
	
	/**
	 * 获取 零配件状态
	 * 
	 * @return String sparepartStatus
	 */
	public String getSparepartStatus() {
		return this.sparepartStatus;
	}
	
	/**
	 * 设置 总积分
	 * 
	 * @param String allIntegral
	 */
	public void setAllIntegral(String allIntegral) {
		this.allIntegral = allIntegral;
	}
	
	/**
	 * 获取 总积分
	 * 
	 * @return String allIntegral
	 */
	public String getAllIntegral() {
		return this.allIntegral;
	}
	
	/**
	 * 设置 大区经理可获积分
	 * 
	 * @param String integral1
	 */
	public void setIntegral1(String integral1) {
		this.integral1 = integral1;
	}
	
	/**
	 * 获取 大区经理可获积分
	 * 
	 * @return String integral1
	 */
	public String getIntegral1() {
		return this.integral1;
	}
	
	/**
	 * 设置 小区经理可获积分
	 * 
	 * @param String integral2
	 */
	public void setIntegral2(String integral2) {
		this.integral2 = integral2;
	}
	
	/**
	 * 获取 小区经理可获积分
	 * 
	 * @return String integral2
	 */
	public String getIntegral2() {
		return this.integral2;
	}
	
	/**
	 * 设置 4S店可获积分
	 * 
	 * @param String integral3
	 */
	public void setIntegral3(String integral3) {
		this.integral3 = integral3;
	}
	
	/**
	 * 获取 4S店可获积分
	 * 
	 * @return String integral3
	 */
	public String getIntegral3() {
		return this.integral3;
	}
	
	
	/**************************table prop  end  *********************************/
}