package com.jx.background.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class BgMaple extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 代号（大写） */
	private String mapleCodeUpper;
	
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
		
	
	/**
	 * 设置 代号（大写）
	 * 
	 * @param String mapleCodeUpper
	 */
	public void setMapleCodeUpper(String mapleCodeUpper) {
		this.mapleCodeUpper = MapleStringUtil.trim(mapleCodeUpper);
	}
	
	/**
	 * 获取 代号（大写）
	 * 
	 * @return String mapleCodeUpper
	 */
	public String getMapleCodeUpper() {
		return this.mapleCodeUpper;
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
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 代码生成 主键id */
	@NotBlank(message="代码生成 主键id 不能为空", groups={ValidationEdit.class})
	private String mapleId;
	
	/** 代码生成代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="代码生成代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String mapleCode;
		
	/** 代码生成名称 */
	@NotBlank(message="代码生成名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mapleName;
		
	/** 代码生成类型 */
	@NotBlank(message="代码生成类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mapleType;
		
	/** 代码生成状态 */
	private String mapleStatus;
		
	/** 控制器包代号 */
	@NotBlank(message="控制器包代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String controllerPackage;
		
	/** 实体类包代号 */
	@NotBlank(message="实体类包代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String entityPackage;
		
	
	
	/**
	 * 设置代码生成 主键id
	 * 
	 * @param String mapleId
	 */
	public void setMapleId(String mapleId) {
		this.mapleId = MapleStringUtil.trim(mapleId);
	}
	
	/**
	 * 获取代码生成 主键id
	 * 
	 * @return String mapleId
	 */
	public String getMapleId() {
		return this.mapleId;
	}
	
	/**
	 * 设置 代码生成代号
	 * 
	 * @param String mapleCode
	 */
	public void setMapleCode(String mapleCode) {
		this.mapleCode = MapleStringUtil.trim(mapleCode);
	}
	
	/**
	 * 获取 代码生成代号
	 * 
	 * @return String mapleCode
	 */
	public String getMapleCode() {
		return this.mapleCode;
	}
	
	/**
	 * 设置 代码生成名称
	 * 
	 * @param String mapleName
	 */
	public void setMapleName(String mapleName) {
		this.mapleName = MapleStringUtil.trim(mapleName);
	}
	
	/**
	 * 获取 代码生成名称
	 * 
	 * @return String mapleName
	 */
	public String getMapleName() {
		return this.mapleName;
	}
	
	/**
	 * 设置 代码生成类型
	 * 
	 * @param String mapleType
	 */
	public void setMapleType(String mapleType) {
		this.mapleType = MapleStringUtil.trim(mapleType);
	}
	
	/**
	 * 获取 代码生成类型
	 * 
	 * @return String mapleType
	 */
	public String getMapleType() {
		return this.mapleType;
	}
	
	/**
	 * 设置 代码生成状态
	 * 
	 * @param String mapleStatus
	 */
	public void setMapleStatus(String mapleStatus) {
		this.mapleStatus = MapleStringUtil.trim(mapleStatus);
	}
	
	/**
	 * 获取 代码生成状态
	 * 
	 * @return String mapleStatus
	 */
	public String getMapleStatus() {
		return this.mapleStatus;
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
	
	/**************************table prop  end  *********************************/
}