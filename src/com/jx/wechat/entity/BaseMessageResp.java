package com.jx.wechat.entity;

import com.jx.common.util.MapleStringUtil;

/**
* 类名: BaseMessageResp </br>
* 描述: 消息基类（公众帐号 -> 普通用户） </br>
 */
public class BaseMessageResp {
	
    /** 接收方帐号（收到的OpenID） */
	private String toUserName;
		
	/** 开发者微信号 */
	private String fromUserName;
		
	/** 消息创建时间 （整型） */
	private long createTime;
		
	/** 消息类型（text/image/location/link） */
	private String msgType;
		
	
	/**
	 * 设置 接收方帐号（收到的OpenID）
	 * 
	 * @param String toUserName
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = MapleStringUtil.trim(toUserName);
	}
	
	/**
	 * 获取 接收方帐号（收到的OpenID）
	 * 
	 * @return String toUserName
	 */
	public String getToUserName() {
		return this.toUserName;
	}
	
	/**
	 * 设置 开发者微信号
	 * 
	 * @param String fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = MapleStringUtil.trim(fromUserName);
	}
	
	/**
	 * 获取 开发者微信号
	 * 
	 * @return String fromUserName
	 */
	public String getFromUserName() {
		return this.fromUserName;
	}
	
	/**
	 * 设置 消息创建时间 （整型）
	 * 
	 * @param long createTime
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 消息创建时间 （整型）
	 * 
	 * @return long createTime
	 */
	public long getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * 设置 消息类型（text/image/location/link）
	 * 
	 * @param long msgType
	 */
	public void setMsgType(String msgType) {
		this.msgType = MapleStringUtil.trim(msgType);
	}
	
	/**
	 * 获取 消息类型（text/image/location/link）
	 * 
	 * @return String msgType
	 */
	public String getMsgType() {
		return this.msgType;
	}
	
}
