package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseEvent;
import com.jx.wechat.entity.BaseMessageReq;
import com.jx.wechat.entity.BaseMessageResp;
import com.jx.wechat.util.MessageUtil;

/**
 * 类名: MusicMessageResp </br>
 * 描述: 回复消息之音乐消息</br>
 */
public class MusicMessageResp extends BaseMessageResp {
	
	public MusicMessageResp(BaseMessageReq messageReq) {
		super(messageReq);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
	}
	
	public MusicMessageResp(BaseEvent event) {
		super(event);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
	}

	public MusicMessageResp() {
		super();
	}



	/** 音乐 */
	private Music music;

	/**
	 * 获得	音乐
	 *	
	 * @return Music music
	 */
	public Music getMusic() {
		return this.music;
	}

	/**
	 * 设置	音乐
	 *
	 * @param Music music
	 */
	public void setMusic(Music music) {
		this.music = music;
	}

}

