package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseEvent;
import com.jx.wechat.entity.BaseMessageReq;
import com.jx.wechat.entity.BaseMessageResp;
import com.jx.wechat.util.MessageUtil;

/**
 * 类名: VoiceMessageResp </br>
 * 描述: 回复消息之语音消息</br>
 */
public class VoiceMessageResp extends BaseMessageResp {
	
	public VoiceMessageResp(BaseMessageReq messageReq) {
		super(messageReq);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
	}
	
	public VoiceMessageResp(BaseEvent event) {
		super(event);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
	}
	
	public VoiceMessageResp() {
		super();
	}



	/** 语音 */
	private Voice Voice;

	/**
	 * 获得	语音
	 *	
	 * @return Voice voice
	 */
	public Voice getVoice() {
		return this.Voice;
	}

	/**
	 * 设置	语音
	 *
	 * @param Voice voice
	 */
	public void setVoice(Voice voice) {
		this.Voice = voice;
	}

}

