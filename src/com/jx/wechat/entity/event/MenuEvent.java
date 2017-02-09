package com.jx.wechat.entity.event;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseEvent;

/**
 * 类名: MenuEvent </br>
 * 描述: 自定义菜单事件 </br>
 */
public class MenuEvent extends BaseEvent {
	
	/** 事件KEY值，与自定义菜单接口中KEY值对应	*/
    private String EventKey;

	/**
	 * 获得	
	 *	
	 * @return String eventKey
	 */
	public String getEventKey() {
		return this.EventKey;
	}

	/**
	 * 设置	
	 *
	 * @param String eventKey
	 */
	public void setEventKey(String eventKey) {
		this.EventKey = MapleStringUtil.trim(eventKey);
	}
    
}
