package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;

public class ComLbt extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 路径 滚播图
	 */
	public static final String PATH_IMG_LBT_LBTIMG = "uploadFiles/image/lbt/lbtImg";
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 轮播图 主键id */
	@NotBlank(message="轮播图 主键id 不能为空", groups={ValidationEdit.class})
	private String lbtId;
	
	/** 轮播图代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="轮播图代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String lbtCode;
		
	/** 轮播图类型 */
	@NotBlank(message="轮播图类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String lbtType;
		
	/** 轮播图状态 */
	private String lbtStatus;
		
	/** 轮播图图片 */
	@NotBlank(message="轮播图图片 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String lbtImgSrc;
		
	/** 描述 */
	@NotBlank(message="描述 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String description;
		
	
	
	/**
	 * 设置轮播图 主键id
	 * 
	 * @param String lbtId
	 */
	public void setLbtId(String lbtId) {
		this.lbtId = StringUtils.trim(lbtId);
	}
	
	/**
	 * 获取轮播图 主键id
	 * 
	 * @return String lbtId
	 */
	public String getLbtId() {
		return this.lbtId;
	}
	
	/**
	 * 设置 轮播图代号
	 * 
	 * @param String lbtCode
	 */
	public void setLbtCode(String lbtCode) {
		this.lbtCode = StringUtils.trim(lbtCode);
	}
	
	/**
	 * 获取 轮播图代号
	 * 
	 * @return String lbtCode
	 */
	public String getLbtCode() {
		return this.lbtCode;
	}
	
	/**
	 * 设置 轮播图类型
	 * 
	 * @param String lbtType
	 */
	public void setLbtType(String lbtType) {
		this.lbtType = StringUtils.trim(lbtType);
	}
	
	/**
	 * 获取 轮播图类型
	 * 
	 * @return String lbtType
	 */
	public String getLbtType() {
		return this.lbtType;
	}
	
	/**
	 * 设置 轮播图状态
	 * 
	 * @param String lbtStatus
	 */
	public void setLbtStatus(String lbtStatus) {
		this.lbtStatus = StringUtils.trim(lbtStatus);
	}
	
	/**
	 * 获取 轮播图状态
	 * 
	 * @return String lbtStatus
	 */
	public String getLbtStatus() {
		return this.lbtStatus;
	}
	
	/**
	 * 设置 轮播图图片
	 * 
	 * @param String lbtImgSrc
	 */
	public void setLbtImgSrc(String lbtImgSrc) {
		this.lbtImgSrc = StringUtils.trim(lbtImgSrc);
	}
	
	/**
	 * 获取 轮播图图片
	 * 
	 * @return String lbtImgSrc
	 */
	public String getLbtImgSrc() {
		return this.lbtImgSrc;
	}
	
	/**
	 * 设置 描述
	 * 
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = StringUtils.trim(description);
	}
	
	/**
	 * 获取 描述
	 * 
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}
	
	
	/**************************table prop  end  *********************************/
}