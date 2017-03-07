package com.jx.common.entity;

import com.jx.common.config.BaseExt;
import com.jx.common.util.MapleStringUtil;

public class ComAppUserExt extends BaseExt {
	
	/**************************custom prop satrt********************************/
	
	/**
	 * 积分数
	 */
	public static final String INTEGRALCOUNT = "integralCount";
	
	/**
	 * 微信二维码
	 */
	public static final String WXQRCODESRC = "wxQRcodeSrc";
	
	/**
	 * 微信二维码有效期
	 */
	public static final String WXQRCODEEXPIRY = "wxQRcodeExpiry";
	
	/**
	 * 媒体文件id
	 */
	public static final String MEDIAID = "mediaId";
	
	/**
	 * 媒体文件有效时间
	 */
	public static final String MEDIAEXPIRY = "mediaExpiry";
	
	
	/**************************custom prop end	********************************/
	
	/** 平台用户扩展 主键id */
	private String appUserExtId;
	
	/** 平台用户 主键id */
	private String appUserId;

	
	/**
	 * 获取 平台用户扩展 主键id
	 * 
	 * @return String appUserExtId
	 */
	public String getAppUserExtId() {
		return this.appUserExtId;
	}

	/**
	 * 设置 平台用户扩展 主键id
	 * 
	 * @param String appUserExtId
	 */
	public void setAppUserExtId(String appUserExtId) {
		this.appUserExtId = MapleStringUtil.trim(appUserExtId);
	}

	/**
	 * 获取 平台用户 主键id
	 * 
	 * @return String appUserId
	 */
	public String getAppUserId() {
		return this.appUserId;
	}

	/**
	 * 设置 平台用户 主键id
	 * 
	 * @param String appUserId
	 */
	public void setAppUserId(String appUserId) {
		this.appUserId = MapleStringUtil.trim(appUserId);
	}
	
}
