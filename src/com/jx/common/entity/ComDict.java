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
	
	/** 数据字典表 主键id */
	private int dictId;
	
	/** 字典名称 */
	private String name;
		
	/** 编码 */
	private String encode;
		
	/** 排序 */
	private String orderBy;
		
	/** 上级ID */
	private int parentId;
		
	/** 级别 */
	private String level;
		
	/** 组合编码 */
	private String allEncode;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	/**
	 * 设置 数据字典表 主键id
	 * 
	 * @param int dictId
	 */
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}
	
	/**
	 * 获取 数据字典表 主键id
	 * 
	 * @return int dictId
	 */
	public int getDictId() {
		return this.dictId;
	}
	
	/**
	 * 设置 字典名称
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = MapleStringUtil.trim(name);
	}
	
	/**
	 * 获取 字典名称
	 * 
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置 编码
	 * 
	 * @param String encode
	 */
	public void setEncode(String encode) {
		this.encode = MapleStringUtil.trim(encode);
	}
	
	/**
	 * 获取 编码
	 * 
	 * @return String encode
	 */
	public String getEncode() {
		return this.encode;
	}
	
	/**
	 * 设置 排序
	 * 
	 * @param String orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = MapleStringUtil.trim(orderBy);
	}
	
	/**
	 * 获取 排序
	 * 
	 * @return String orderBy
	 */
	public String getOrderBy() {
		return this.orderBy;
	}
	
	/**
	 * 设置 上级ID
	 * 
	 * @param int parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取 上级ID
	 * 
	 * @return int parentId
	 */
	public int getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置 级别
	 * 
	 * @param String level
	 */
	public void setLevel(String level) {
		this.level = MapleStringUtil.trim(level);
	}
	
	/**
	 * 获取 级别
	 * 
	 * @return String level
	 */
	public String getLevel() {
		return this.level;
	}
	
	/**
	 * 设置 组合编码
	 * 
	 * @param String allEncode
	 */
	public void setAllEncode(String allEncode) {
		this.allEncode = MapleStringUtil.trim(allEncode);
	}
	
	/**
	 * 获取 组合编码
	 * 
	 * @return String allEncode
	 */
	public String getAllEncode() {
		return this.allEncode;
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
		setDictId(0);
	
		setName("");
		setEncode("");
		setOrderBy("");
		setParentId(0);
		setLevel("");
		setAllEncode("");
		try {
			setModifyTimeStr("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**************************table prop  end  *********************************/
}