package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseMessageResp;

/**
 * 类名: MusicMessageResp </br>
 * 描述: 回复消息之音乐消息</br>
 */
public class MusicMessageResp extends BaseMessageResp {
	
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

