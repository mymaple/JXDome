package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComInvite extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 邀请 主键id */
	@NotBlank(message="邀请 主键id 不能为空", groups={ValidationEdit.class})
	private String inviteId;
	
	/** 邀请代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="邀请代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String inviteCode;
		
	/** 邀请名称 */
	@NotBlank(message="邀请名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String inviteName;
		
	/** 邀请类型 */
	@NotBlank(message="邀请类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String inviteType;
		
	/** 邀请状态  state 00wx扫描 01完成注册绑定 02未绑定失效*/
	private String inviteStatus;
		
	/** 邀请人id */
	@NotBlank(message="邀请人id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String inviteUserId;
		
	/** 被邀请人id */
	@NotBlank(message="被邀请人id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String invitedUserId;
		
	
	
	/**
	 * 设置邀请 主键id
	 * 
	 * @param String inviteId
	 */
	public void setInviteId(String inviteId) {
		this.inviteId = MapleStringUtil.trim(inviteId);
	}
	
	/**
	 * 获取邀请 主键id
	 * 
	 * @return String inviteId
	 */
	public String getInviteId() {
		return this.inviteId;
	}
	
	/**
	 * 设置 邀请代号
	 * 
	 * @param String inviteCode
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = MapleStringUtil.trim(inviteCode);
	}
	
	/**
	 * 获取 邀请代号
	 * 
	 * @return String inviteCode
	 */
	public String getInviteCode() {
		return this.inviteCode;
	}
	
	/**
	 * 设置 邀请名称
	 * 
	 * @param String inviteName
	 */
	public void setInviteName(String inviteName) {
		this.inviteName = MapleStringUtil.trim(inviteName);
	}
	
	/**
	 * 获取 邀请名称
	 * 
	 * @return String inviteName
	 */
	public String getInviteName() {
		return this.inviteName;
	}
	
	/**
	 * 设置 邀请类型
	 * 
	 * @param String inviteType
	 */
	public void setInviteType(String inviteType) {
		this.inviteType = MapleStringUtil.trim(inviteType);
	}
	
	/**
	 * 获取 邀请类型
	 * 
	 * @return String inviteType
	 */
	public String getInviteType() {
		return this.inviteType;
	}
	
	/**
	 * 设置 邀请状态
	 * state 00wx扫描 01完成注册绑定 02未绑定失效
	 * @param String inviteStatus
	 */
	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = MapleStringUtil.trim(inviteStatus);
	}
	
	/**
	 * 获取 邀请状态
	 * state 00wx扫描 01完成注册绑定 02未绑定失效
	 * @return String inviteStatus
	 */
	public String getInviteStatus() {
		return this.inviteStatus;
	}
	
	/**
	 * 设置 邀请人id
	 * 
	 * @param String inviteUserId
	 */
	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = MapleStringUtil.trim(inviteUserId);
	}
	
	/**
	 * 获取 邀请人id
	 * 
	 * @return String inviteUserId
	 */
	public String getInviteUserId() {
		return this.inviteUserId;
	}
	
	/**
	 * 设置 被邀请人id
	 * 
	 * @param String invitedUserId
	 */
	public void setInvitedUserId(String invitedUserId) {
		this.invitedUserId = MapleStringUtil.trim(invitedUserId);
	}
	
	/**
	 * 获取 被邀请人id
	 * 
	 * @return String invitedUserId
	 */
	public String getInvitedUserId() {
		return this.invitedUserId;
	}
	
	
	/**************************table prop  end  *********************************/
}