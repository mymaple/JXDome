package com.jx.wechat.entity.messageResp;

import com.jx.wechat.entity.BaseEvent;
import com.jx.wechat.entity.BaseMessageReq;
import com.jx.wechat.entity.BaseMessageResp;
import com.jx.wechat.util.MessageUtil;

/**
 * 类名: ImageMessageResp </br>
 * 描述: 回复消息之图片消息</br>
 */
public class ImageMessageResp extends BaseMessageResp {
	
	public ImageMessageResp(BaseMessageReq messageReq) {
		super(messageReq);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
	}
	
	public ImageMessageResp(BaseEvent event) {
		super(event);
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
	}
	
	public ImageMessageResp() {
		super();
	}

	/** 图片 */
	private Image Image;

	/**
	 * 获得	图片
	 *	
	 * @return Image image
	 */
	public Image getImage() {
		return this.Image;
	}

	/**
	 * 设置	图片
	 *
	 * @param Image image
	 */
	public void setImage(Image image) {
		this.Image = image;
	}
	
	
}

