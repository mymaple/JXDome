package com.jx.wechat.entity;

import com.jx.common.util.MapleStringUtil;

public class BaseMessageReq {
	
	/** 开发者微信号 */
	private String ToUserName;
		
	/** 发送方帐号（一个OpenID） */
	private String FromUserName;
		
	/** 消息创建时间 （整型） */
	private String CreateTime;
		
	/** 消息类型（text/image/location/link） */
	private String MsgType;
		
	/** 消息id，64位整型 */
	private String MsgId;
		
	
	/**
	 * 设置 开发者微信号
	 * 
	 * @param String toUserName
	 */
	public void setToUserName(String toUserName) {
		this.ToUserName = MapleStringUtil.trim(toUserName);
	}
	
	/**
	 * 获取 开发者微信号
	 * 
	 * @return String toUserName
	 */
	public String getToUserName() {
		return this.ToUserName;
	}
	
	/**
	 * 设置 发送方帐号（一个OpenID）
	 * 
	 * @param String fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.FromUserName = MapleStringUtil.trim(fromUserName);
	}
	
	/**
	 * 获取 发送方帐号（一个OpenID）
	 * 
	 * @return String fromUserName
	 */
	public String getFromUserName() {
		return this.FromUserName;
	}
	
	/**
	 * 设置 消息创建时间 （整型）
	 * 
	 * @param String createTime
	 */
	public void setCreateTime(String createTime) {
		this.CreateTime = createTime;
	}
	
	/**
	 * 获取 消息创建时间 （整型）
	 * 
	 * @return String createTime
	 */
	public String getCreateTime() {
		return this.CreateTime;
	}
	
	/**
	 * 设置 消息类型（text/image/location/link）
	 * 
	 * @param String msgType
	 */
	public void setMsgType(String msgType) {
		this.MsgType = MapleStringUtil.trim(msgType);
	}
	
	/**
	 * 获取 消息类型（text/image/location/link）
	 * 
	 * @return String msgType
	 */
	public String getMsgType() {
		return this.MsgType;
	}
	
	/**
	 * 设置 消息id，64位整型
	 * 
	 * @param String msgId
	 */
	public void setMsgId(String msgId) {
		this.MsgId = msgId;
	}
	
	/**
	 * 获取 消息id，64位整型
	 * 
	 * @return String msgId
	 */
	public String getMsgId() {
		return this.MsgId;
	}

}
