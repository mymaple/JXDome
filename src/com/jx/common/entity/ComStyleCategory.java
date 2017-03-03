package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComStyleCategory extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 规格分类 主键id */
	@NotBlank(message="规格分类 主键id 不能为空", groups={ValidationEdit.class})
	private String styleCategoryId;
	
	/** 产品编号 */
	@NotBlank(message="产品编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productId;
		
	/** 规格分类代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="规格分类代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String styleCategoryCode;
		
	/** 规格分类名称 */
	@NotBlank(message="规格分类名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String styleCategoryName;
		
	/** 规格分类状态 */
	private String styleCategoryStatus;
		
	
	
	/**
	 * 设置规格分类 主键id
	 * 
	 * @param String styleCategoryId
	 */
	public void setStyleCategoryId(String styleCategoryId) {
		this.styleCategoryId = MapleStringUtil.trim(styleCategoryId);
	}
	
	/**
	 * 获取规格分类 主键id
	 * 
	 * @return String styleCategoryId
	 */
	public String getStyleCategoryId() {
		return this.styleCategoryId;
	}
	
	/**
	 * 设置 产品编号
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = MapleStringUtil.trim(productId);
	}
	
	/**
	 * 获取 产品编号
	 * 
	 * @return String productId
	 */
	public String getProductId() {
		return this.productId;
	}
	
	/**
	 * 设置 规格分类代号
	 * 
	 * @param String styleCategoryCode
	 */
	public void setStyleCategoryCode(String styleCategoryCode) {
		this.styleCategoryCode = MapleStringUtil.trim(styleCategoryCode);
	}
	
	/**
	 * 获取 规格分类代号
	 * 
	 * @return String styleCategoryCode
	 */
	public String getStyleCategoryCode() {
		return this.styleCategoryCode;
	}
	
	/**
	 * 设置 规格分类名称
	 * 
	 * @param String styleCategoryName
	 */
	public void setStyleCategoryName(String styleCategoryName) {
		this.styleCategoryName = MapleStringUtil.trim(styleCategoryName);
	}
	
	/**
	 * 获取 规格分类名称
	 * 
	 * @return String styleCategoryName
	 */
	public String getStyleCategoryName() {
		return this.styleCategoryName;
	}
	
	/**
	 * 设置 规格分类状态
	 * 
	 * @param String styleCategoryStatus
	 */
	public void setStyleCategoryStatus(String styleCategoryStatus) {
		this.styleCategoryStatus = MapleStringUtil.trim(styleCategoryStatus);
	}
	
	/**
	 * 获取 规格分类状态
	 * 
	 * @return String styleCategoryStatus
	 */
	public String getStyleCategoryStatus() {
		return this.styleCategoryStatus;
	}
	
	
	/**************************table prop  end  *********************************/
}