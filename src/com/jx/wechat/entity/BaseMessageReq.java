package com.jx.wechat.entity;

import com.jx.common.util.MapleStringUtil;

public class BaseMessageReq {
	
	/** 开发者微信号 */
	private String toUserName;
		
	/** 发送方帐号（一个OpenID） */
	private String fromUserName;
		
	/** 消息创建时间 （整型） */
	private long createTime;
		
	/** 消息类型（text/image/location/link） */
	private String msgType;
		
	/** 消息id，64位整型 */
	private long msgId;
		
	
	/**
	 * 设置 开发者微信号
	 * 
	 * @param String toUserName
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = MapleStringUtil.trim(toUserName);
	}
	
	/**
	 * 获取 开发者微信号
	 * 
	 * @return String toUserName
	 */
	public String getToUserName() {
		return this.toUserName;
	}
	
	/**
	 * 设置 发送方帐号（一个OpenID）
	 * 
	 * @param String fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = MapleStringUtil.trim(fromUserName);
	}
	
	/**
	 * 获取 发送方帐号（一个OpenID）
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
	 * @param String msgType
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
	
	/**
	 * 设置 消息id，64位整型
	 * 
	 * @param long msgId
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	
	/**
	 * 获取 消息id，64位整型
	 * 
	 * @return long msgId
	 */
	public long getMsgId() {
		return this.msgId;
	}

}
