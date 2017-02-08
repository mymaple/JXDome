package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseMessageResp;

/**
 * 类名: TextMessageResp </br>
 * 描述: 回复消息之文本消息 </br>
 */
public class TextMessageResp extends BaseMessageResp {

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

