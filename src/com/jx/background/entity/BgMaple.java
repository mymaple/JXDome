package com.jx.background.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class BgMaple implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** 代码生成 主键id */
	private int mapleId;
	
	/** 代号 */
	private String mapleCode;
		
	/** 名称 */
	private String mapleName;
		
	/** 类型 */
	private String mapleType;
	
	/** 代号 */
	private String mapleCodeUpper;
		
	/** 控制器包代号 */
	private String controllerPackage;
		
	/** 实体类包代号 */
	private String entityPackage;
		
	/** 控制器中的代号（大写） */
	private String mapleControllerUpper;
		
	/** 控制器中的代号（小写） */
	private String mapleControllerLower;
		
	/** 实体类中的代号（大写） */
	private String mapleEntityUpper;
		
	/** 实体类中的代号（小写） */
	private String mapleEntityLower;
		
	/** 数据表代号 */
	private String tableCode;
		
	/** 排序编号 */
	private String orderNum;
		
	/** 状态标识 */
	private String status;
		
	/** 有效标识 */
	private String effective;
		
	/** 新增人员 */
	private String createUserId;
		
	/** 新增时间 */
	private Date createTime;
		
	/** 修改人员id */
	private String modifyUserId;
		
	/** 修改时间 */
	private Date modifyTime;
		
	
	
	public String getMapleCodeUpper() {
		return mapleCodeUpper;
	}

	public void setMapleCodeUpper(String mapleCodeUpper) {
		this.mapleCodeUpper = mapleCodeUpper;
	}

	/**
	 * 设置 代码生成 主键id
	 * 
	 * @param int mapleId
	 */
	public void setMapleId(int mapleId) {
		this.mapleId = mapleId;
	}
	
	/**
	 * 获取 代码生成 主键id
	 * 
	 * @return int mapleId
	 */
	public int getMapleId() {
		return this.mapleId;
	}
	
	/**
	 * 设置 代号
	 * 
	 * @param String mapleCode
	 */
	public void setMapleCode(String mapleCode) {
		this.mapleCode = MapleStringUtil.trim(mapleCode);
	}
	
	/**
	 * 获取 代号
	 * 
	 * @return String mapleCode
	 */
	public String getMapleCode() {
		return this.mapleCode;
	}
	
	/**
	 * 设置 名称
	 * 
	 * @param String mapleName
	 */
	public void setMapleName(String mapleName) {
		this.mapleName = MapleStringUtil.trim(mapleName);
	}
	
	/**
	 * 获取 名称
	 * 
	 * @return String mapleName
	 */
	public String getMapleName() {
		return this.mapleName;
	}
	
	/**
	 * 设置 类型
	 * 
	 * @param String mapleType
	 */
	public void setMapleType(String mapleType) {
		this.mapleType = MapleStringUtil.trim(mapleType);
	}
	
	/**
	 * 获取 类型
	 * 
	 * @return String mapleType
	 */
	public String getMapleType() {
		return this.mapleType;
	}
	
	/**
	 * 设置 控制器包代号
	 * 
	 * @param String controllerPackage
	 */
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = MapleStringUtil.trim(controllerPackage);
	}
	
	/**
	 * 获取 控制器包代号
	 * 
	 * @return String controllerPackage
	 */
	public String getControllerPackage() {
		return this.controllerPackage;
	}
	
	/**
	 * 设置 实体类包代号
	 * 
	 * @param String entityPackage
	 */
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = MapleStringUtil.trim(entityPackage);
	}
	
	/**
	 * 获取 实体类包代号
	 * 
	 * @return String entityPackage
	 */
	public String getEntityPackage() {
		return this.entityPackage;
	}
	
	/**
	 * 设置 控制器中的代号（大写）
	 * 
	 * @param String mapleControllerUpper
	 */
	public void setMapleControllerUpper(String mapleControllerUpper) {
		this.mapleControllerUpper = MapleStringUtil.trim(mapleControllerUpper);
	}
	
	/**
	 * 获取 控制器中的代号（大写）
	 * 
	 * @return String mapleControllerUpper
	 */
	public String getMapleControllerUpper() {
		return this.mapleControllerUpper;
	}
	
	/**
	 * 设置 控制器中的代号（小写）
	 * 
	 * @param String mapleControllerLower
	 */
	public void setMapleControllerLower(String mapleControllerLower) {
		this.mapleControllerLower = MapleStringUtil.trim(mapleControllerLower);
	}
	
	/**
	 * 获取 控制器中的代号（小写）
	 * 
	 * @return String mapleControllerLower
	 */
	public String getMapleControllerLower() {
		return this.mapleControllerLower;
	}
	
	/**
	 * 设置 实体类中的代号（大写）
	 * 
	 * @param String mapleEntityUpper
	 */
	public void setMapleEntityUpper(String mapleEntityUpper) {
		this.mapleEntityUpper = MapleStringUtil.trim(mapleEntityUpper);
	}
	
	/**
	 * 获取 实体类中的代号（大写）
	 * 
	 * @return String mapleEntityUpper
	 */
	public String getMapleEntityUpper() {
		return this.mapleEntityUpper;
	}
	
	/**
	 * 设置 实体类中的代号（小写）
	 * 
	 * @param String mapleEntityLower
	 */
	public void setMapleEntityLower(String mapleEntityLower) {
		this.mapleEntityLower = MapleStringUtil.trim(mapleEntityLower);
	}
	
	/**
	 * 获取 实体类中的代号（小写）
	 * 
	 * @return String mapleEntityLower
	 */
	public String getMapleEntityLower() {
		return this.mapleEntityLower;
	}
	
	/**
	 * 设置 数据表代号
	 * 
	 * @param String tableCode
	 */
	public void setTableCode(String tableCode) {
		this.tableCode = MapleStringUtil.trim(tableCode);
	}
	
	/**
	 * 获取 数据表代号
	 * 
	 * @return String tableCode
	 */
	public String getTableCode() {
		return this.tableCode;
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
	 * 设置 新增人员
	 * 
	 * @param String createUserId
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = MapleStringUtil.trim(createUserId);
	}
	
	/**
	 * 获取 新增人员
	 * 
	 * @return String createUserId
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	
	/**
	 * 设置 新增时间
	 * 
	 * @param Date createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 新增时间
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
	
	
	public BgMaple(){
		init();
	}
	
	public void init() {
		setMapleId(0);
	
		setMapleCode("");
		setMapleName("");
		setMapleType("");
		setControllerPackage("");
		setEntityPackage("");
		setMapleControllerUpper("");
		setMapleControllerLower("");
		setMapleEntityUpper("");
		setMapleEntityLower("");
		setTableCode("");
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