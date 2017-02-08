package com.jx.wechat.entity.messageReq;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: VoiceMessageReq </br>
 * 描述: 请求消息之语音消息 </br>
 */
public class VoiceMessageReq extends BaseMessageReq {

    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String mediaId;
    
    /** 语音格式，如amr，speex等 */
    private String format;
    
    
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
	 * 获取 	语音格式，如amr，speex等 
	 * 
	 * @return String format
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 * 设置 	语音格式，如amr，speex等 
	 * 
	 * @param String format
	 */
	public void setFormat(String format) {
		this.format = MapleStringUtil.trim(format);
	}

}

