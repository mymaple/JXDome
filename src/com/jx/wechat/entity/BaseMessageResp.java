package com.jx.wechat.entity;

import java.util.Date;

import com.jx.common.util.MapleStringUtil;

/**
* 类名: BaseMessageResp </br>
* 描述: 消息基类（公众帐号 -> 普通用户） </br>
 */
public class BaseMessageResp {
	
	public BaseMessageResp(BaseMessageReq messageReq) {
		super();
		this.ToUserName = messageReq.getFromUserName();
		this.FromUserName = messageReq.getToUserName();
		this.CreateTime = String.valueOf(new Date().getTime());
		this.FuncFlag = "0";
	}
	
	public BaseMessageResp(BaseEvent event) {
		super();
		this.ToUserName = event.getFromUserName();
		this.FromUserName = event.getToUserName();
		this.CreateTime = String.valueOf(new Date().getTime());
		this.FuncFlag = "0";
	}
	
    public BaseMessageResp() {
		super();
	}

	/** 接收方帐号（收到的OpenID） */
	private String ToUserName;
		
	/** 开发者微信号 */
	private String FromUserName;
		
	/** 消息创建时间 （整型） */
	private String CreateTime;
		
	/** 消息类型（text/image/location/link） */
	private String MsgType;
		
	/** 位0x0001被标志时，星标刚收到的消息	*/
    private String FuncFlag;
    
    
	/**
	 * 设置 接收方帐号（收到的OpenID）
	 * 
	 * @param String toUserName
	 */
	public void setToUserName(String toUserName) {
		this.ToUserName = MapleStringUtil.trim(toUserName);
	}
	
	/**
	 * 获取 接收方帐号（收到的OpenID）
	 * 
	 * @return String toUserName
	 */
	public String getToUserName() {
		return this.ToUserName;
	}
	
	/**
	 * 设置 开发者微信号
	 * 
	 * @param String fromUserName
	 */
	public void setFromUserName(String fromUserName) {
		this.FromUserName = MapleStringUtil.trim(fromUserName);
	}
	
	/**
	 * 获取 开发者微信号
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
	 * 获得	位0x0001被标志时，星标刚收到的消息
	 *	
	 * @return String funcFlag
	 */
	public String getFuncFlag() {
		return this.FuncFlag;
	}

	/**
	 * 设置	位0x0001被标志时，星标刚收到的消息
	 *
	 * @param String funcFlag
	 */
	public void setFuncFlag(String funcFlag) {
		this.FuncFlag = funcFlag;
	}
	
}
