package com.jx.wechat.entity.messageResp;

import com.jx.common.util.MapleStringUtil;

/**
 * 类名: Video </br>
 * 描述: 视频model </br>
 */
public class Video {

    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String mediaId;
    
    /** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据 */
    private String thumbMediaId;
    
    
    /**
     * 获取	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     * 
	 * @return String mediaId
	 */
	public String getMediaId() {
		return this.mediaId;
	}

	/**
	 * 设置 	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @param String mediaId
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = MapleStringUtil.trim(mediaId);
	}

	/**
	 * 获得	视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 *	
	 * @return String thumbMediaId
	 */
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	/**
	 * 设置	视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 *
	 * @param String thumbMediaId
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = MapleStringUtil.trim(thumbMediaId);
	}

}

