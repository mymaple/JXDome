package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgMapleDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 代码生成详细 主键id */
	private int mapleDetailId;
	
	/** 属性代号 */
	private String mapleDetailCode;
		
	/** 属性名称 */
	private String mapleDetailName;
		
	/** 属性类型 */
	private String mapleDetailType;
	
	/** 属性代号(大写) */
	private String mapleDetailCodeUpper;
		
	/** 长度 */
	private String length;
		
	/** 小数长度 */
	private String decimalLength;
		
	/** 是否录入 */
	private String isEdit;
		
	/** 是否null */
	private String isNull;
		
	/** 默认值 */
	private String defaultValue;
	
	/** 默认值 */
	private String mapleId;
		
	/** 排序编号 */
	private String orderNum;
		
	/** 状态标识 */
	private String status;
		
	/** 有效标识 */
	private String effective;
		
	/** 创建人员id */
	private String createUserId;
		
	/** 创建时间 */
	private Date createTime;
		
	/** 修改人员id */
	private String modifyUserId;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	public String getMapleDetailCodeUpper() {
		return mapleDetailCodeUpper;
	}

	public void setMapleDetailCodeUpper(String mapleDetailCodeUpper) {
		this.mapleDetailCodeUpper = mapleDetailCodeUpper;
	}

	/**
	 * 设置 代码生成详细 主键id
	 * 
	 * @param int mapleDetailId
	 */
	public void setMapleDetailId(int mapleDetailId) {
		this.mapleDetailId = mapleDetailId;
	}
	
	/**
	 * 获取 代码生成详细 主键id
	 * 
	 * @return int mapleDetailId
	 */
	public int getMapleDetailId() {
		return this.mapleDetailId;
	}
	
	/**
	 * 设置 属性代号
	 * 
	 * @param String mapleDetailCode
	 */
	public void setMapleDetailCode(String mapleDetailCode) {
		this.mapleDetailCode = MapleStringUtil.trim(mapleDetailCode);
	}
	
	/**
	 * 获取 属性代号
	 * 
	 * @return String mapleDetailCode
	 */
	public String getMapleDetailCode() {
		return this.mapleDetailCode;
	}
	
	/**
	 * 设置 属性名称
	 * 
	 * @param String mapleDetailName
	 */
	public void setMapleDetailName(String mapleDetailName) {
		this.mapleDetailName = MapleStringUtil.trim(mapleDetailName);
	}
	
	/**
	 * 获取 属性名称
	 * 
	 * @return String mapleDetailName
	 */
	public String getMapleDetailName() {
		return this.mapleDetailName;
	}
	
	/**
	 * 设置 属性类型
	 * 
	 * @param String mapleDetailType
	 */
	public void setMapleDetailType(String mapleDetailType) {
		this.mapleDetailType = MapleStringUtil.trim(mapleDetailType);
	}
	
	/**
	 * 获取 属性类型
	 * 
	 * @return String mapleDetailType
	 */
	public String getMapleDetailType() {
		return this.mapleDetailType;
	}
	
	/**
	 * 设置 长度
	 * 
	 * @param String length
	 */
	public void setLength(String length) {
		this.length = MapleStringUtil.trim(length);
	}
	
	/**
	 * 获取 长度
	 * 
	 * @return String length
	 */
	public String getLength() {
		return this.length;
	}
	
	/**
	 * 设置 小数长度
	 * 
	 * @param String decimalLength
	 */
	public void setDecimalLength(String decimalLength) {
		this.decimalLength = MapleStringUtil.trim(decimalLength);
	}
	
	/**
	 * 获取 小数长度
	 * 
	 * @return String decimalLength
	 */
	public String getDecimalLength() {
		return this.decimalLength;
	}
	
	/**
	 * 设置 是否录入
	 * 
	 * @param String isEdit
	 */
	public void setIsEdit(String isEdit) {
		this.isEdit = MapleStringUtil.trim(isEdit);
	}
	
	/**
	 * 获取 是否录入
	 * 
	 * @return String isEdit
	 */
	public String getIsEdit() {
		return this.isEdit;
	}
	
	/**
	 * 设置 是否null
	 * 
	 * @param String isNull
	 */
	public void setIsNull(String isNull) {
		this.isNull = MapleStringUtil.trim(isNull);
	}
	
	/**
	 * 获取 是否null
	 * 
	 * @return String isNull
	 */
	public String getIsNull() {
		return this.isNull;
	}
	
	/**
	 * 设置 默认值
	 * 
	 * @param String defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = MapleStringUtil.trim(defaultValue);
	}
	
	/**
	 * 获取 默认值
	 * 
	 * @return String defaultValue
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}
	
	/**
	 * 设置 默认值
	 * 
	 * @param String mapleId
	 */
	public void setMapleId(String mapleId) {
		this.mapleId = MapleStringUtil.trim(mapleId);
	}
	
	/**
	 * 获取 默认值
	 * 
	 * @return String mapleId
	 */
	public String getMapleId() {
		return this.mapleId;
	}
	
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
	 * 设置 状态标识
	 * 
	 * @param String status
	 */
	public void setStatus(String status) {
		this.status = MapleStringUtil.trim(status);
	}
	
	/**
	 * 获取 状态标识
	 * 
	 * @return String status
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置 有效标识
	 * 
	 * @param String effective
	 */
	public void setEffective(String effective) {
		this.effective = MapleStringUtil.trim(effective);
	}
	
	/**
	 * 获取 有效标识
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
		
	public void setCreateTimeStr(String createTimeStr) throws Exception{
		createTimeStr = MapleStringUtil.trim(createTimeStr);
		if(!createTimeStr.equals("")){
			try{
				setCreateTime(MapleDateUtil.parseDate(createTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getCreateTimeStr(){
		return MapleDateUtil.getFormatedDateString(getCreateTime());
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
		
	public void setModifyTimeStr(String modifyTimeStr) throws Exception{
		modifyTimeStr = MapleStringUtil.trim(modifyTimeStr);
		if(!modifyTimeStr.equals("")){
			try{
				setModifyTime(MapleDateUtil.parseDate(modifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getModifyTimeStr(){
		return MapleDateUtil.getFormatedDateString(getModifyTime());
	}	
	
	
	public BgMapleDetail(){
		init();
	}
	
	public void init() {
		setMapleDetailId(0);
	
		setMapleDetailCode("");
		setMapleDetailName("");
		setMapleDetailType("");
		setLength("");
		setDecimalLength("");
		setIsEdit("");
		setIsNull("");
		setDefaultValue("");
		setOrderNum("");
		setStatus("");
		setEffective("");
		setCreateUserId("");
		try {
			setCreateTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setModifyUserId("");
		try {
			setModifyTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************table prop  end  *********************************/
}