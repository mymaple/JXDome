package com.jx.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class ComDict implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComDict> subComDictList;
	
	/** 子列表 路径*/
	private String subComDictPath;
	
	/** 是否有此数据字典 */
	private boolean hasDict;
	
	
	/**
	 * 获取 指标
	 * 
	 * @return String target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * 设置 指标
	 * 
	 * @param String target
	 */
	public void setTarget(String target) {
		this.target = MapleStringUtil.trim(target);
	}
	
	/**
	 * 获取 子列表
	 * 
	 * @return List<ComDict> subComDictList
	 */
	public List<ComDict> getSubComDictList() {
		return this.subComDictList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComDict> subComDictList
	 */
	public void setSubComDictList(List<ComDict> subComDictList) {
		this.subComDictList = subComDictList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComDictPath
	 */
	public String getSubComDictPath() {
		return this.subComDictPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComDictPath
	 */
	public void setSubComDictPath(String subComDictPath) {
		this.subComDictPath = MapleStringUtil.trim(subComDictPath);
	}
	
	/**
	 * 获取 是否有此数据字典 
	 * 
	 * @return boolean hasDict
	 */
	public boolean isHasDict() {
		return this.hasDict;
	}
	
	/**
	 * 设置 是否有此数据字典
	 * 
	 * @param boolean hasDict
	 */
	public void setHasDict(boolean hasDict) {
		this.hasDict = hasDict;
	}
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 数据字典 主键id */
	private String dictId;
	
	/** 上级 id */
	private String parentId;
	
	/** 数据字典代号 */
	private String dictCode;
		
	/** 数据字典名称 */
	private String dictName;
		
	/** 数据字典类型 */
	private String dictType;
		
	/** 数据字典值 */
	private String dictValue;
		
	/** 数据字典状态 */
	private String dictStatus;
		
	/** 级别 */
	private int level;
		
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
	 * 设置数据字典 主键id
	 * 
	 * @param String dictId
	 */
	public void setDictId(String dictId) {
		this.dictId = MapleStringUtil.trim(dictId);
	}
	
	/**
	 * 获取数据字典 主键id
	 * 
	 * @return String dictId
	 */
	public String getDictId() {
		return this.dictId;
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
	
	
	/**************************table prop  end  *********************************/
}