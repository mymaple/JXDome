package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgMapleDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 代码生成详情代号（大写） */
	private String mapleDetailCodeUpper;
	
	/**
	 * 设置 代码生成详情代号（大写）
	 * 
	 * @param String mapleDetailCodeUpper
	 */
	public void setMapleDetailCodeUpper(String mapleDetailCodeUpper) {
		this.mapleDetailCodeUpper = MapleStringUtil.trim(mapleDetailCodeUpper);
	}
	
	/**
	 * 获取 代码生成详情代号（大写）
	 * 
	 * @return String mapleDetailCodeUpper
	 */
	public String getMapleDetailCodeUpper() {
		return this.mapleDetailCodeUpper;
	}
	
	/**************************custom prop end**********************************/
	
	//新增校验分组
	public interface ValidationAdd
	{
	//接口中不需要任何定义
	}
	
	//编辑校验分组
	public interface ValidationEdit
	{
	//接口中不需要任何定义
	}
	
	/**************************table prop satrt*********************************/
	
	/** 代码生成详情 主键id */
	@NotEmpty(message="代码生成详情 主键id 不能为空", groups={ValidationEdit.class})
	private String mapleDetailId;
	
	/** 代码生成 id */
	@NotEmpty(message="代码生成id 不能为空", groups={ValidationAdd.class})
	private String mapleId;
	
	/** 代码生成详情代号 */
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]*$", message="代码生成详情代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String mapleDetailCode;
		
	/** 代码生成详情名称 */
	@NotEmpty(message="代码生成详情名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mapleDetailName;
		
	/** 代码生成详情类型 */
	@NotEmpty(message="代码生成详情类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mapleDetailType;
		
	/** 代码生成详情状态 */
	private String mapleDetailStatus;
		
	/** 总长度 */
//	@NotEmpty(message="总长度 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String totalLength;
		
	/** 小数长度 */
//	@NotEmpty(message="小数长度 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String decimalLength;
		
	/** 类型代号 */
//	@NotEmpty(message="类型代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String typeCode;
		
	/** 是否主键 */
	@NotEmpty(message="是否主键 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String isKey;
		
	/** 是否录入 */
	@NotEmpty(message="是否录入 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String isEdit;
		
	/** 是否null */
	@NotEmpty(message="是否null 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String isNull;
		
	/** 默认值 */
//	@NotEmpty(message="默认值 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String defaultValue;
		
	/** 排序编号 */
	@NotEmpty(message="排序编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String orderNum;
		
	/** 有效标志 */
	private String effective;
		
	/** 创建人员id */
	private String createUserId;
		
	/** 创建时间 */
	private Date createTime;
		
	/** 修改人员id */
	private String modifyUserId;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置代码生成详情 主键id
	 * 
	 * @param String mapleDetailId
	 */
	public void setMapleDetailId(String mapleDetailId) {
		this.mapleDetailId = MapleStringUtil.trim(mapleDetailId);
	}
	
	/**
	 * 获取代码生成详情 主键id
	 * 
	 * @return String mapleDetailId
	 */
	public String getMapleDetailId() {
		return this.mapleDetailId;
	}
	
	/**
	 * 设置 代码生成 id 
	 * 
	 * @param String mapleId
	 */
	public void setMapleId(String mapleId) {
		this.mapleId = MapleStringUtil.trim(mapleId);
	}
	
	/**
	 * 获取 代码生成 id 
	 * 
	 * @return String mapleId
	 */
	public String getMapleId() {
		return this.mapleId;
	}
	
	/**
	 * 设置 代码生成详情代号
	 * 
	 * @param String mapleDetailCode
	 */
	public void setMapleDetailCode(String mapleDetailCode) {
		this.mapleDetailCode = MapleStringUtil.trim(mapleDetailCode);
	}
	
	/**
	 * 获取 代码生成详情代号
	 * 
	 * @return String mapleDetailCode
	 */
	public String getMapleDetailCode() {
		return this.mapleDetailCode;
	}
	
	/**
	 * 设置 代码生成详情名称
	 * 
	 * @param String mapleDetailName
	 */
	public void setMapleDetailName(String mapleDetailName) {
		this.mapleDetailName = MapleStringUtil.trim(mapleDetailName);
	}
	
	/**
	 * 获取 代码生成详情名称
	 * 
	 * @return String mapleDetailName
	 */
	public String getMapleDetailName() {
		return this.mapleDetailName;
	}
	
	/**
	 * 设置 代码生成详情类型
	 * 
	 * @param String mapleDetailType
	 */
	public void setMapleDetailType(String mapleDetailType) {
		this.mapleDetailType = MapleStringUtil.trim(mapleDetailType);
	}
	
	/**
	 * 获取 代码生成详情类型
	 * 
	 * @return String mapleDetailType
	 */
	public String getMapleDetailType() {
		return this.mapleDetailType;
	}
	
	/**
	 * 设置 代码生成详情状态
	 * 
	 * @param String mapleDetailStatus
	 */
	public void setMapleDetailStatus(String mapleDetailStatus) {
		this.mapleDetailStatus = MapleStringUtil.trim(mapleDetailStatus);
	}
	
	/**
	 * 获取 代码生成详情状态
	 * 
	 * @return String mapleDetailStatus
	 */
	public String getMapleDetailStatus() {
		return this.mapleDetailStatus;
	}
	
	/**
	 * 设置 总长度
	 * 
	 * @param String totalLength
	 */
	public void setTotalLength(String totalLength) {
		this.totalLength = MapleStringUtil.trim(totalLength);
	}
	
	/**
	 * 获取 总长度
	 * 
	 * @return String totalLength
	 */
	public String getTotalLength() {
		return this.totalLength;
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
	 * 设置 类型代号
	 * 
	 * @param String typeCode
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = MapleStringUtil.trim(typeCode);
	}
	
	/**
	 * 获取 类型代号
	 * 
	 * @return String typeCode
	 */
	public String getTypeCode() {
		return this.typeCode;
	}
	
	/**
	 * 设置 是否主键
	 * 
	 * @param String isKey
	 */
	public void setIsKey(String isKey) {
		this.isKey = MapleStringUtil.trim(isKey);
	}
	
	/**
	 * 获取 是否主键
	 * 
	 * @return String isKey
	 */
	public String getIsKey() {
		return this.isKey;
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
	 * 设置 有效标志
	 * 
	 * @param String effective
	 */
	public void setEffective(String effective) {
		this.effective = MapleStringUtil.trim(effective);
	}
	
	/**
	 * 获取 有效标志
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
	
	
	/**************************table prop  end  *********************************/
}