package com.jx.common.config;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import com.jx.common.util.MapleDateUtil;

/**
 *	基类
 */
public class BaseEntity {
	
	public interface ValidationAdd {

	}
	
	public interface ValidationEdit {
		
	}
	
	public interface ValidationWxAdd {
		
	}
	
	public interface ValidationWxEdit {
		
	}

	
	/** 排序编号 */
	@NotEmpty(message="排序编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
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
	
	/** 旧值 */
	private String oldValue;
	
	/**
	 * 设置 排序编号
	 * 
	 * @param String orderNum
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = StringUtils.trim(orderNum);
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
		this.createUserId = StringUtils.trim(createUserId);
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
	
	public void setCreateTimeStr(String createTimeStr) throws Exception{
		createTimeStr = StringUtils.trim(createTimeStr);
		if(!createTimeStr.equals("")){
			try{
				setCreateTime(MapleDateUtil.parseDateStr(createTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}
	
	public String getCreateTimeStr(){
		return MapleDateUtil.formatDate(getCreateTime());
	}
		
	/**
	 * 设置 修改人员id
	 * 
	 * @param String modifyUserId
	 */
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = StringUtils.trim(modifyUserId);
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
	
	public void setModifyTimeStr(String modifyTimeStr) throws Exception{
		modifyTimeStr = StringUtils.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(MapleDateUtil.parseDateStr(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}
	
	public String getModifyTimeStr(){
		return MapleDateUtil.formatDate(getModifyTime());
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
	
	public void setLastModifyTimeStr(String lastModifyTimeStr) throws Exception{
		lastModifyTimeStr = StringUtils.trim(lastModifyTimeStr);
		if(!lastModifyTimeStr.equals("")){
			try{
				setLastModifyTime(MapleDateUtil.parseDateStr(lastModifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getLastModifyTimeStr(){
		return MapleDateUtil.formatDate(getLastModifyTime());
	}

	/**
	 * 获取 旧值
	 * 
	 * @return String oldValue
	 */
	public String getOldValue() {
		return this.oldValue;
	}

	/**
	 * 设置 旧值
	 * 
	 * @param String oldValue
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = StringUtils.trim(oldValue);
	}
	
	
		
}
