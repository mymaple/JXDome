package com.jx.wechat.entity.messageReq;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: VoiceMessageReq </br>
 * 描述: 请求消息之语音消息 </br>
 */
public class VoiceMessageReq extends BaseMessageReq {

    /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据  */
    private String MediaId;
    
    /** 语音格式，如amr，speex等 */
    private String Format;
    
    
    /**
     * 获取	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
     * 
	 * @return String mediaId
	 */
	public String getMediaId() {
		return this.MediaId;
	}

	/**
	 * 设置 	图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 * 
	 * @param String mediaId
	 */
	public void setMediaId(String mediaId) {
		this.MediaId = MapleStringUtil.trim(mediaId);
	}

	/**
	 * 获取 	语音格式，如amr，speex等 
	 * 
	 * @return String format
	 */
	public String getFormat() {
		return this.Format;
	}

	/**
	 * 设置 	语音格式，如amr，speex等 
	 * 
	 * @param String format
	 */
	public void setFormat(String format) {
		this.Format = MapleStringUtil.trim(format);
	}

}

