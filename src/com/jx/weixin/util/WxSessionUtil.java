package com.jx.weixin.util;

import com.jx.common.config.shiro.ShiroSessionUtil;

public class WxSessionUtil extends ShiroSessionUtil {
	
	/**
	 * 微信 个人openId
	 */
	public static final String SESSION_WX_MYOPENID_STR = "sessionWxMyOpenId";
	
	/**
	 * 获取 微信 个人openId
	 * @return
	 */
	public static String getMyOpenId(){
		return (String)getSession().getAttribute(SESSION_WX_MYOPENID_STR);
	}
	
	/**
	 * 设置 微信 个人openId
	 * @param myOpenId
	 */
	public static void setMyOpenId(String myOpenId){
		setSessionAttr(SESSION_WX_MYOPENID_STR, myOpenId);
	}
	
}
