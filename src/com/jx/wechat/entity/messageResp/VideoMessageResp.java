package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseEvent;
import com.jx.wechat.entity.BaseMessageReq;
import com.jx.wechat.entity.BaseMessageResp;
import com.jx.wechat.util.MessageUtil;

/**
 * 类名: VideoMessageResp </br>
 * 描述: 回复消息之视频消息</br>
 */
public class VideoMessageResp extends BaseMessageResp {
	
	public VideoMessageResp(BaseMessageReq messageReq) {
		super(messageReq);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
	}
	
	public VideoMessageResp(BaseEvent event) {
		super(event);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
	}

	public VideoMessageResp() {
		super();
	}



	/** 视频 */
	private Video video;

	/**
	 * 获得	视频
	 *	
	 * @return Video video
	 */
	public Video getVideo() {
		return this.video;
	}

	/**
	 * 设置	视频
	 *
	 * @param Video video
	 */
	public void setVideo(Video video) {
		this.video = video;
	}

}

