package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseMessageResp;

/**
 * 类名: ImageMessageResp </br>
 * 描述: 回复消息之图片消息</br>
 */
public class ImageMessageResp extends BaseMessageResp {
	
	/** 图片 */
	private Image image;

	/**
	 * 获得	图片
	 *	
	 * @return Image image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * 设置	图片
	 *
	 * @param Image image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}

