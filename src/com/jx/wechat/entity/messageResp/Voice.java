package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

/**
 * 类名: Voice </br>
 * 描述: 语音model</br>
 */
public class Voice {

    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String mediaId;
    
    
    
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

