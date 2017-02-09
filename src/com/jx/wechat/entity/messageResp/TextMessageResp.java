package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseEvent;
import com.jx.wechat.entity.BaseMessageReq;
import com.jx.wechat.entity.BaseMessageResp;
import com.jx.wechat.util.MessageUtil;

/**
 * 类名: TextMessageResp </br>
 * 描述: 回复消息之文本消息 </br>
 */
public class TextMessageResp extends BaseMessageResp {

    public TextMessageResp(BaseMessageReq messageReq) {
		super(messageReq);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	}
    
    public TextMessageResp(BaseEvent event) {
    	super(event);
    	setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
    }
    
	public TextMessageResp() {
		super();
	}



	/** 消息内容 */
    private String Content;
    
    /**
	 * 获取	消息内容
	 * 
	 * @return String content
	 */
    public String getContent() {
        return this.Content;
    }

    /**
	 * 设置	消息内容
	 * 
	 * @param String content
	 */
    public void setContent(String content) {
    	this.Content = content;
    }
    
}

