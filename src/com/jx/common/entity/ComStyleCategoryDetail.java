package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComStyleCategoryDetail extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 规格分类详情 主键id */
	@NotBlank(message="规格分类详情 主键id 不能为空", groups={ValidationEdit.class})
	private String styleCategoryDetailId;
	
	/** 规格分类 id */
	@NotBlank(message="规格分类id 不能为空", groups={ValidationAdd.class})
	private String styleCategoryId;
	
	/** 规格分类详情代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="规格分类详情代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String styleCategoryDetailCode;
		
	/** 规格分类详情名称 */
	@NotBlank(message="规格分类详情名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String styleCategoryDetailName;
		
	/** 规格分类详情状态 */
	private String styleCategoryDetailStatus;
		
	
	
	/**
	 * 设置规格分类详情 主键id
	 * 
	 * @param String styleCategoryDetailId
	 */
	public void setStyleCategoryDetailId(String styleCategoryDetailId) {
		this.styleCategoryDetailId = MapleStringUtil.trim(styleCategoryDetailId);
	}
	
	/**
	 * 获取规格分类详情 主键id
	 * 
	 * @return String styleCategoryDetailId
	 */
	public String getStyleCategoryDetailId() {
		return this.styleCategoryDetailId;
	}
	
	/**
	 * 设置 规格分类 id 
	 * 
	 * @param String styleCategoryId
	 */
	public void setStyleCategoryId(String styleCategoryId) {
		this.styleCategoryId = MapleStringUtil.trim(styleCategoryId);
	}
	
	/**
	 * 获取 规格分类 id 
	 * 
	 * @return String styleCategoryId
	 */
	public String getStyleCategoryId() {
		return this.styleCategoryId;
	}
	
	/**
	 * 设置 规格分类详情代号
	 * 
	 * @param String styleCategoryDetailCode
	 */
	public void setStyleCategoryDetailCode(String styleCategoryDetailCode) {
		this.styleCategoryDetailCode = MapleStringUtil.trim(styleCategoryDetailCode);
	}
	
	/**
	 * 获取 规格分类详情代号
	 * 
	 * @return String styleCategoryDetailCode
	 */
	public String getStyleCategoryDetailCode() {
		return this.styleCategoryDetailCode;
	}
	
	/**
	 * 设置 规格分类详情名称
	 * 
	 * @param String styleCategoryDetailName
	 */
	public void setStyleCategoryDetailName(String styleCategoryDetailName) {
		this.styleCategoryDetailName = MapleStringUtil.trim(styleCategoryDetailName);
	}
	
	/**
	 * 获取 规格分类详情名称
	 * 
	 * @return String styleCategoryDetailName
	 */
	public String getStyleCategoryDetailName() {
		return this.styleCategoryDetailName;
	}
	
	/**
	 * 设置 规格分类详情状态
	 * 
	 * @param String styleCategoryDetailStatus
	 */
	public void setStyleCategoryDetailStatus(String styleCategoryDetailStatus) {
		this.styleCategoryDetailStatus = MapleStringUtil.trim(styleCategoryDetailStatus);
	}
	
	/**
	 * 获取 规格分类详情状态
	 * 
	 * @return String styleCategoryDetailStatus
	 */
	public String getStyleCategoryDetailStatus() {
		return this.styleCategoryDetailStatus;
	}
	
	
	/**************************table prop  end  *********************************/
}