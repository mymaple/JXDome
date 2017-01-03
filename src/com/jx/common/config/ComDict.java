package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class ComDict implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 数据字典主键id */
	private String dictId;
		
	/** 数据字典代号 */
	private String dictCode;
		
	/** 数据字典名称 */
	private String dictName;
		
	/** 数据字典类型 */
	private String dictType;
		
	/** 数据字典状态 */
	private String dictStatus;
		
	/** 数据字典值 */
	private String dictValue;
		
	/** 级别 */
	private int level;
		
	/** 上级id */
	private String parentId;
		
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
		
	
	/**
	 * 设置 数据字典主键id
	 * 
	 * @param String dictId
	 */
	public void setDictId(String dictId) {
		this.dictId = MapleStringUtil.trim(dictId);
	}
	
	/**
	 * 获取 数据字典主键id
	 * 
	 * @return String dictId
	 */
	public String getDictId() {
		return this.dictId;
	}
	
	/**
	 * 设置 数据字典代号
	 * 
	 * @param String dictCode
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = MapleStringUtil.trim(dictCode);
	}
	
	/**
	 * 获取 数据字典代号
	 * 
	 * @return String dictCode
	 */
	public String getDictCode() {
		return this.dictCode;
	}
	
	/**
	 * 设置 数据字典名称
	 * 
	 * @param String dictName
	 */
	public void setDictName(String dictName) {
		this.dictName = MapleStringUtil.trim(dictName);
	}
	
	/**
	 * 获取 数据字典名称
	 * 
	 * @return String dictName
	 */
	public String getDictName() {
		return this.dictName;
	}
	
	/**
	 * 设置 数据字典类型
	 * 
	 * @param String dictType
	 */
	public void setDictType(String dictType) {
		this.dictType = MapleStringUtil.trim(dictType);
	}
	
	/**
	 * 获取 数据字典类型
	 * 
	 * @return String dictType
	 */
	public String getDictType() {
		return this.dictType;
	}
	
	/**
	 * 设置 数据字典状态
	 * 
	 * @param String dictStatus
	 */
	public void setDictStatus(String dictStatus) {
		this.dictStatus = MapleStringUtil.trim(dictStatus);
	}
	
	/**
	 * 获取 数据字典状态
	 * 
	 * @return String dictStatus
	 */
	public String getDictStatus() {
		return this.dictStatus;
	}
	
	/**
	 * 设置 数据字典值
	 * 
	 * @param String dictValue
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = MapleStringUtil.trim(dictValue);
	}
	
	/**
	 * 获取 数据字典值
	 * 
	 * @return String dictValue
	 */
	public String getDictValue() {
		return this.dictValue;
	}
	
	/**
	 * 设置 级别
	 * 
	 * @param int level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * 获取 级别
	 * 
	 * @return int level
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * 设置 上级id
	 * 
	 * @param String parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = MapleStringUtil.trim(parentId);
	}
	
	/**
	 * 获取 上级id
	 * 
	 * @return String parentId
	 */
	public String getParentId() {
		return this.parentId;
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
	 * 设置 有效性
	 * 
	 * @param String effective
	 */
	public void setEffective(String effective) {
		this.effective = MapleStringUtil.trim(effective);
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
	
	

	
	public ComDict(){
		init();
	}
	
	public void init() {
	
		setDictId("");
		setDictCode("");
		setDictName("");
		setDictType("");
		setDictStatus("");
		setDictValue("");
		setLevel(0);
		setParentId("");
		setOrderNum("");
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