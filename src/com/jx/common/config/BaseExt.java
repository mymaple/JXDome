package com.jx.common.config;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.jx.common.util.MapleStringUtil;

/**
 * 扩展属性基类
 */
public class BaseExt {
	
	
	/** 扩展属性代号 */
	private String extCode;
	
	/** 扩展属性名 */
	private String extName;
		
	/** 扩展属性值 */
	private String extValue;
		
	

	/**
	 * 获取 扩展属性代号
	 * 
	 * @return String extCode
	 */
	public String getExtCode() {
		return this.extCode;
	}

	/**
	 * 设置 扩展属性代号
	 * 
	 * @param String extCode
	 */
	public void setExtCode(String extCode) {
		this.extCode = MapleStringUtil.trim(extCode);
	}

	/**
	 * 获取 扩展属性名 
	 * 
	 * @return String extName
	 */
	public String getExtName() {
		return this.extName;
	}

	/**
	 * 设置 扩展属性名 
	 * 
	 * @param String extName
	 */
	public void setExtName(String extName) {
		this.extName = MapleStringUtil.trim(extName);
	}

	/**
	 * 获取 扩展属性值 
	 * 
	 * @return String extValue
	 */
	public String getExtValue() {
		return this.extValue;
	}

	/**
	 * 设置 扩展属性值 
	 * 
	 * @param String extValue
	 */
	public void setExtValue(String extValue) {
		this.extValue = MapleStringUtil.trim(extValue);
	}

	/** 排序编号 */
	private String orderNum;
	
	/** 有效性 */
	private String effective;
		
	/** 创建人员id */
	private String createUserId;
		
	/** 创建时间 */
	private Date createTime;
		
	/** 修改人员id */
	private String modifyUserId;
		
	/** 修改时间 */
	private Date modifyTime;
	
	/** 修改时间 */
	private Date lastModifyTime;
	
	/**
	 * 设置 排序编号
	 * 
	 * @param String orderNum
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = MapleStringUtil.trim(orderNum);
	}
	
	/**
	 * 获取 排序编号
	 * 
	 * @return String orderNum
	 */
	public String getOrderNum() {
		return this.orderNum;
	}
	
	/**
	 * 设置 有效性
	 * 
	 * @param String effective
	 */
	public void setEffective(String effective) {
		this.effective = StringUtils.trim(effective);
	}
	
	/**
	 * 获取 有效性
	 * 
	 * @return String effective
	 */
	public String getEffective() {
		return this.effective;
	}
	
	/**
	 * 设置 创建人员id
	 * 
	 * @param String createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = MapleStringUtil.trim(createUserId);
	}
	
	/**
	 * 获取 创建人员id
	 * 
	 * @return String createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	
	/**
	 * 设置 创建时间
	 * 
	 * @param Date createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 创建时间
	 * 
	 * @return Date createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}	
		
	/**
	 * 设置 修改人员id
	 * 
	 * @param String modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = MapleStringUtil.trim(modifyUserId);
	}
	
	/**
	 * 获取 修改人员id
	 * 
	 * @return String modifyUserId
	 */
	public String getModifyUserId() {
		return this.modifyUserId;
	}
	
	/**
	 * 设置 修改时间
	 * 
	 * @param Date modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date modifyTime
	 */
	public Date getModifyTime() {
		return this.modifyTime;
	}	
		
	/**
	 * 设置 修改时间
	 * 
	 * @param Date lastModifyTime
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date lastModifyTime
	 */
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}	
}
