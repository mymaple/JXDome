package com.jx.wechat.entity.messageReq;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: LinkMessageReq </br>
 * 描述: 请求消息之链接消息 </br>
 */
public class LinkMessageReq extends BaseMessageReq {

	/** 消息标题	*/
    private String title;
    /** 消息描述	*/
    private String description;
    /** 消息链接	*/
    private String url;
    
    
	/**
	 * 获得	消息标题
	 *	
	 * @return String title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * 设置	消息标题
	 *
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = MapleStringUtil.trim(title);
	}
	/**
	 * 获得	消息描述
	 *	
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * 设置	消息描述
	 *
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = MapleStringUtil.trim(description);
	}
	/**
	 * 获得	消息链接
	 *	
	 * @return String url
	 */
	public String getUrl() {
		return this.url;
	}
	/**
	 * 设置	消息链接
	 *
	 * @param String url
	 */
	public void setUrl(String url) {
		this.url = MapleStringUtil.trim(url);
	}
    
}

