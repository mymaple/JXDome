package com.jx.wechat.entity.messageReq;

import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: TextMessageReq </br>
 * 描述: 请求消息之文本消息 </br>
 */
public class TextMessageReq extends BaseMessageReq {

    /** 消息内容 */
    private String content;
    
    /**
	 * 获取	消息内容
	 * 
	 * @return String content
	 */
    public String getContent() {
        return this.content;
    }

    /**
	 * 设置	消息内容
	 * 
	 * @param String content
	 */
    public void setContent(String content) {
    	this.content = content;
    }
    
}

