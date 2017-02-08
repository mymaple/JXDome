package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

public class News {
	
	/** 图文消息名称	*/
    private String title;
    
    /** 图文消息描述	*/
    private String description;
    
    /** 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80	*/
    private String picUrl;
    
 	/**	点击图文消息跳转链接	*/
    private String url;

	/**
	 * 获得	图文消息名称
	 *	
	 * @return String title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置	图文消息名称
	 *
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = MapleStringUtil.trim(title);
	}

	/**
	 * 获得	图文消息描述
	 *	
	 * @return String description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置	图文消息描述
	 *
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = MapleStringUtil.trim(description);
	}

	/**
	 * 获得	图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
	 *	
	 * @return String picUrl
	 */
	public String getPicUrl() {
		return this.picUrl;
	}

	/**
	 * 设置	图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
	 *
	 * @param String picUrl
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = MapleStringUtil.trim(picUrl);
	}

	/**
	 * 获得	点击图文消息跳转链接
	 *	
	 * @return String url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * 设置	点击图文消息跳转链接
	 *
	 * @param String url
	 */
	public void setUrl(String url) {
		this.url = MapleStringUtil.trim(url);
	}

    
}
