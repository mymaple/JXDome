package com.jx.wechat.entity.messageReq;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseMessageReq;

/**
 * 类名: LocationMessageReq </br>
 * 描述: 请求消息之地理位置消息</br>
 */
public class LocationMessageReq extends BaseMessageReq {

	/** 地理位置维度 */
    private String location_X;
    
    /** 地理位置经度 */
    private String location_Y;
    
    /** 地图缩放大小 */
    private String scale;
    
    /** 地理位置信息 */
    private String label;

	/**
	 * 获得	地理位置维度
	 *	
	 * @return String location_X
	 */
	public String getLocation_X() {
		return this.location_X;
	}

	/**
	 * 设置	地理位置维度
	 *
	 * @param String location_X
	 */
	public void setLocation_X(String location_X) {
		this.location_X = MapleStringUtil.trim(location_X);
	}

	/**
	 * 获得	地理位置经度
	 *	
	 * @return String location_Y
	 */
	public String getLocation_Y() {
		return this.location_Y;
	}

	/**
	 * 设置	地理位置经度
	 *
	 * @param String location_Y
	 */
	public void setLocation_Y(String location_Y) {
		this.location_Y = MapleStringUtil.trim(location_Y);
	}

	/**
	 * 获得	地图缩放大小
	 *	
	 * @return String scale
	 */
	public String getScale() {
		return this.scale;
	}

	/**
	 * 设置	地图缩放大小
	 *
	 * @param String scale
	 */
	public void setScale(String scale) {
		this.scale = MapleStringUtil.trim(scale);
	}

	/**
	 * 获得	地理位置信息
	 *	
	 * @return String label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * 设置	地理位置信息
	 *
	 * @param String label
	 */
	public void setLabel(String label) {
		this.label = MapleStringUtil.trim(label);
	}
    
    
}

