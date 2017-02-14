package com.jx.wechat.entity.event;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseEvent;

/**
 * 类名: SubscribeEvent </br>
 * 描述: 关注/取消关注事件 </br>
 */
public class SubscribeEvent extends BaseEvent {
	
	/**	事件KEY值，qrscene_为前缀，后面为二维码的参数值	*/
	private String eventKey;
	
	/**	二维码的ticket，可用来换取二维码图片	*/
	private String ticket;

	/**
	 * 获得	事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 *	
	 * @return String eventKey
	 */
	public String getEventKey() {
		return this.eventKey;
	}

	/**
	 * 设置	事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 *
	 * @param String eventKey
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = MapleStringUtil.trim(eventKey);
	}

	/**
	 * 获得	二维码的ticket，可用来换取二维码图片
	 *	
	 * @return String ticket
	 */
	public String getTicket() {
		return this.ticket;
	}

	/**
	 * 设置	二维码的ticket，可用来换取二维码图片
	 *
	 * @param String ticket
	 */
	public void setTicket(String ticket) {
		this.ticket = MapleStringUtil.trim(ticket);
	}
	
}
