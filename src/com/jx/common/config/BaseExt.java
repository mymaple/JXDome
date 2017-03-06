package com.jx.common.config;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.jx.common.util.MapleStringUtil;

/**
 * 扩展属性基类
 */
public class BaseExt {
	
	public interface ValidationAdd {

	}
	
	public interface ValidationEdit {
		
	}
	
		
	/** 扩展属性名 */
	@NotBlank(message="扩展属性名 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String name;
		
	/** 扩展属性值 */
	@NotBlank(message="扩展属性值 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String value;
		
	/** 扩展属性代号 */
	@NotBlank(message="扩展属性代号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String code;
	
	/** 排序编号 */
	@NotEmpty(message="排序编号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
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
	
	/** 修改时间 */
	private Date lastModifyTime;
	
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
		this.effective = StringUtils.trim(effective);
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
		
	/**
	 * 设置 修改时间
	 * 
	 * @param Date lastModifyTime
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date lastModifyTime
	 */
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}	
}
