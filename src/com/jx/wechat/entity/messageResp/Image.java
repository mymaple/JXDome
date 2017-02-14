package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

/**
 * 类名: Image </br>
 * 描述: 图片</br>
 */
public class Image {

	
    public Image(String mediaId) {
		super();
		MediaId = mediaId;
	}
    
	public Image() {
		super();
	}



	/** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String MediaId;
    
    
    
	/**
	 * 获取	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @return String mediaId
	 */
	public String getMediaId() {
		return this.MediaId;
	}

	/**
	 * 设置	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @param String mediaId
	 */
	public void setMediaId(String mediaId) {
		this.MediaId = MapleStringUtil.trim(mediaId);
	}

}

