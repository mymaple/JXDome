package com.jx.wechat.entity.messageReq;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: ImageMessageReq </br>
 * 描述: 请求消息之图片消息</br>
 */
public class ImageMessageReq extends BaseMessageReq {

    /** 图片链接 */
    private String picUrl;
    
    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String mediaId;
    
    
    
    /**
     * 获取	图片链接
     * 
	 * @return String picUrl
	 */
	public String getPicUrl() {
		return this.picUrl;
	}

	/**
	 * 设置	图片链接
	 * 
	 * @param String picUrl
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = MapleStringUtil.trim(picUrl);
	}

	/**
	 * 获取	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @return String mediaId
	 */
	public String getMediaId() {
		return this.mediaId;
	}

	/**
	 * 设置	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @param String mediaId
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = MapleStringUtil.trim(mediaId);
	}

}

