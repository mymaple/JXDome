package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseMessageResp;

/**
 * 类名: VoiceMessageResp </br>
 * 描述: 回复消息之语音消息</br>
 */
public class VoiceMessageResp extends BaseMessageResp {
	
	/** 语音 */
	private Voice voice;

	/**
	 * 获得	语音
	 *	
	 * @return Voice voice
	 */
	public Voice getVoice() {
		return this.voice;
	}

	/**
	 * 设置	语音
	 *
	 * @param Voice voice
	 */
	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}

