package com.jx.common.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;

public class ComStyleCategory extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComStyleCategory> subComStyleCategoryList;
	
	/** 子列表 路径*/
	private String subComStyleCategoryPath;
	
	/** 是否有此规格分类 */
	private boolean hasStyleCategory;

	
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
		this.target = StringUtils.trim(target);
	}
	
	/**
	 * 获取 子列表
	 * 
	 * @return List<ComStyleCategory> subComStyleCategoryList
	 */
	public List<ComStyleCategory> getSubComStyleCategoryList() {
		return this.subComStyleCategoryList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComStyleCategory> subComStyleCategoryList
	 */
	public void setSubComStyleCategoryList(List<ComStyleCategory> subComStyleCategoryList) {
		this.subComStyleCategoryList = subComStyleCategoryList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComStyleCategoryPath
	 */
	public String getSubComStyleCategoryPath() {
		return this.subComStyleCategoryPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComStyleCategoryPath
	 */
	public void setSubComStyleCategoryPath(String subComStyleCategoryPath) {
		this.subComStyleCategoryPath = StringUtils.trim(subComStyleCategoryPath);
	}
	
	/**
	 * 获取 是否有此规格分类 
	 * 
	 * @return boolean hasStyleCategory
	 */
	public boolean isHasStyleCategory() {
		return this.hasStyleCategory;
	}
	
	/**
	 * 设置 是否有此规格分类
	 * 
	 * @param boolean hasStyleCategory
	 */
	public void setHasStyleCategory(boolean hasStyleCategory) {
		this.hasStyleCategory = hasStyleCategory;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 规格分类 主键id */
	@NotBlank(message="规格分类 主键id 不能为空", groups={ValidationEdit.class})
	private String styleCategoryId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 产品编号 */
	@NotBlank(message="产品编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String productId;
		
	/** 规格分类名称 */
	@NotBlank(message="规格分类名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String styleCategoryName;
		
	/** 是否最终分类 */
	@NotBlank(message="是否最终分类 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String isFinal;
		
	
	
	/**
	 * 设置规格分类 主键id
	 * 
	 * @param String styleCategoryId
	 */
	public void setStyleCategoryId(String styleCategoryId) {
		this.styleCategoryId = StringUtils.trim(styleCategoryId);
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
	 * 设置 上级id
	 * 
	 * @param String parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = StringUtils.trim(parentId);
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
	 * 设置 产品编号
	 * 
	 * @param String productId
	 */
	public void setProductId(String productId) {
		this.productId = StringUtils.trim(productId);
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
	 * 设置 规格分类名称
	 * 
	 * @param String styleCategoryName
	 */
	public void setStyleCategoryName(String styleCategoryName) {
		this.styleCategoryName = StringUtils.trim(styleCategoryName);
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
	 * 设置 是否最终分类
	 * 
	 * @param String isFinal
	 */
	public void setIsFinal(String isFinal) {
		this.isFinal = StringUtils.trim(isFinal);
	}
	
	/**
	 * 获取 是否最终分类
	 * 
	 * @return String isFinal
	 */
	public String getIsFinal() {
		return this.isFinal;
	}
	
	
	/**************************table prop  end  *********************************/
}