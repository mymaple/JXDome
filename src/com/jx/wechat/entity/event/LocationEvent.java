package com.jx.wechat.entity.event;

import com.jx.common.util.MapleStringUtil;
import com.jx.wechat.entity.BaseEvent;

/**
 * 类名: LocationEvent </br>
 * 描述: 上报地理位置事件 </br>
 */
public class LocationEvent extends BaseEvent {
	
	/** 地理位置纬度	*/
    private String latitude;
    
    /** 地理位置经度	*/
    private String longitude;
    
    /** 地理位置精度	*/
    private String precision;
    
    
	/**
	 * 获得	地理位置纬度
	 *	
	 * @return String latitude
	 */
	public String getLatitude() {
		return this.latitude;
	}
	/**
	 * 设置	地理位置纬度
	 *
	 * @param String latitude
	 */
	public void setLatitude(String latitude) {
		latitude = MapleStringUtil.trim(latitude);
	}
	/**
	 * 获得	地理位置经度
	 *	
	 * @return String longitude
	 */
	public String getLongitude() {
		return this.longitude;
	}
	/**
	 * 设置	地理位置经度
	 *
	 * @param String longitude
	 */
	public void setLongitude(String longitude) {
		longitude = MapleStringUtil.trim(longitude);
	}
	/**
	 * 获得	地理位置精度
	 *	
	 * @return String precision
	 */
	public String getPrecision() {
		return this.precision;
	}
	/**
	 * 设置	地理位置精度
	 *
	 * @param String precision
	 */
	public void setPrecision(String precision) {
		precision = MapleStringUtil.trim(precision);
	}
    
}
