package com.jx.common.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComIntegralCustomer extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComIntegralCustomer> subComIntegralCustomerList;
	
	/** 子列表 路径*/
	private String subComIntegralCustomerPath;
	
	/** 是否有此积分客户 */
	private boolean hasIntegralCustomer;

	
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
	 * @return List<ComIntegralCustomer> subComIntegralCustomerList
	 */
	public List<ComIntegralCustomer> getSubComIntegralCustomerList() {
		return this.subComIntegralCustomerList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComIntegralCustomer> subComIntegralCustomerList
	 */
	public void setSubComIntegralCustomerList(List<ComIntegralCustomer> subComIntegralCustomerList) {
		this.subComIntegralCustomerList = subComIntegralCustomerList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComIntegralCustomerPath
	 */
	public String getSubComIntegralCustomerPath() {
		return this.subComIntegralCustomerPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComIntegralCustomerPath
	 */
	public void setSubComIntegralCustomerPath(String subComIntegralCustomerPath) {
		this.subComIntegralCustomerPath = MapleStringUtil.trim(subComIntegralCustomerPath);
	}
	
	/**
	 * 获取 是否有此积分客户 
	 * 
	 * @return boolean hasIntegralCustomer
	 */
	public boolean isHasIntegralCustomer() {
		return this.hasIntegralCustomer;
	}
	
	/**
	 * 设置 是否有此积分客户
	 * 
	 * @param boolean hasIntegralCustomer
	 */
	public void setHasIntegralCustomer(boolean hasIntegralCustomer) {
		this.hasIntegralCustomer = hasIntegralCustomer;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 积分客户 主键id */
	@NotBlank(message="积分客户 主键id 不能为空", groups={ValidationEdit.class})
	private String integralCustomerId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 角色 */
	@NotBlank(message="角色 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String roleId;
		
	/** 积分客户代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="积分客户代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String integralCustomerCode;
		
	/** 积分客户名称 */
	@NotBlank(message="积分客户名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralCustomerName;
		
	/** 积分客户类型 */
	@NotBlank(message="积分客户类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String integralCustomerType;
		
	/** 积分客户状态 */
	private String integralCustomerStatus;
		
	/** 手机号码 */
	@NotBlank(message="手机号码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String phone;
		
	/** 密码 */
	@NotBlank(message="密码 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String password;
		
	/** 客户头像 */
	private String userIconSrc;
		
	/** 备注信息 */
	@NotBlank(message="备注信息 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String remarks;
		
	/** 级别 */
	private String level;
		
	
	
	/**
	 * 设置积分客户 主键id
	 * 
	 * @param String integralCustomerId
	 */
	public void setIntegralCustomerId(String integralCustomerId) {
		this.integralCustomerId = MapleStringUtil.trim(integralCustomerId);
	}
	
	/**
	 * 获取积分客户 主键id
	 * 
	 * @return String integralCustomerId
	 */
	public String getIntegralCustomerId() {
		return this.integralCustomerId;
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
	 * 设置 角色
	 * 
	 * @param String roleId
	 */
	public void setRoleId(String roleId) {
		this.roleId = MapleStringUtil.trim(roleId);
	}
	
	/**
	 * 获取 角色
	 * 
	 * @return String roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}
	
	/**
	 * 设置 积分客户代号
	 * 
	 * @param String integralCustomerCode
	 */
	public void setIntegralCustomerCode(String integralCustomerCode) {
		this.integralCustomerCode = MapleStringUtil.trim(integralCustomerCode);
	}
	
	/**
	 * 获取 积分客户代号
	 * 
	 * @return String integralCustomerCode
	 */
	public String getIntegralCustomerCode() {
		return this.integralCustomerCode;
	}
	
	/**
	 * 设置 积分客户名称
	 * 
	 * @param String integralCustomerName
	 */
	public void setIntegralCustomerName(String integralCustomerName) {
		this.integralCustomerName = MapleStringUtil.trim(integralCustomerName);
	}
	
	/**
	 * 获取 积分客户名称
	 * 
	 * @return String integralCustomerName
	 */
	public String getIntegralCustomerName() {
		return this.integralCustomerName;
	}
	
	/**
	 * 设置 积分客户类型
	 * 
	 * @param String integralCustomerType
	 */
	public void setIntegralCustomerType(String integralCustomerType) {
		this.integralCustomerType = MapleStringUtil.trim(integralCustomerType);
	}
	
	/**
	 * 获取 积分客户类型
	 * 
	 * @return String integralCustomerType
	 */
	public String getIntegralCustomerType() {
		return this.integralCustomerType;
	}
	
	/**
	 * 设置 积分客户状态
	 * 
	 * @param String integralCustomerStatus
	 */
	public void setIntegralCustomerStatus(String integralCustomerStatus) {
		this.integralCustomerStatus = MapleStringUtil.trim(integralCustomerStatus);
	}
	
	/**
	 * 获取 积分客户状态
	 * 
	 * @return String integralCustomerStatus
	 */
	public String getIntegralCustomerStatus() {
		return this.integralCustomerStatus;
	}
	
	/**
	 * 设置 手机号码
	 * 
	 * @param String phone
	 */
	public void setPhone(String phone) {
		this.phone = MapleStringUtil.trim(phone);
	}
	
	/**
	 * 获取 手机号码
	 * 
	 * @return String phone
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置 密码
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = MapleStringUtil.trim(password);
	}
	
	/**
	 * 获取 密码
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 设置 客户头像
	 * 
	 * @param String userIconSrc
	 */
	public void setUserIconSrc(String userIconSrc) {
		this.userIconSrc = MapleStringUtil.trim(userIconSrc);
	}
	
	/**
	 * 获取 客户头像
	 * 
	 * @return String userIconSrc
	 */
	public String getUserIconSrc() {
		return this.userIconSrc;
	}
	
	/**
	 * 设置 备注信息
	 * 
	 * @param String remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = MapleStringUtil.trim(remarks);
	}
	
	/**
	 * 获取 备注信息
	 * 
	 * @return String remarks
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	/**
	 * 设置 级别
	 * 
	 * @param String level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 获取 级别
	 * 
	 * @return String level
	 */
	public String getLevel() {
		return this.level;
	}
	
	
	/**************************table prop  end  *********************************/
}