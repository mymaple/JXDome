package com.jx.weixin.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.mgt.CookieRememberMeManager;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.background.entity.BgUser;
import com.jx.common.entity.ComAppUser;

public class WxSessionUtil {
	
	/**shiro管理的session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	/**获得  Session key对应的Obj
	 * @return
	 */
	public static Object getSessionAttr(String key){
		return getSession().getAttribute(key);
	}
	
	
	/**设置  Session key对应的Obj
	 * @return
	 */
	public static void setSessionAttr(String key, Object obj){
		Session session = getSession();
		if(session.getAttribute(key) != null){
			session.removeAttribute(key);
		}
		session.setAttribute(key, obj);
	}
	
	/**
	 * 微信 登录验证码
	 */
	public static final String SESSION_WX_CAPTCHA_STR = "sessionWxCaptcha";
	
	/**
	 * 微信 个人openId
	 */
	public static final String SESSION_WX_MYOPENID_STR = "sessionWxMyOpenId";
	
	/**
	 * 微信 平台用户信息
	 */
	public static final String SESSION_WX_COMAPPUSER_OBJ = "sessionWxComAppUser";
	
	
	
	
	/**
	 * 获取 微信 登录验证码
	 * @return
	 */
	public static String getSessionWxCaptcha(){
		return (String)getSession().getAttribute(SESSION_WX_CAPTCHA_STR);
	}
	
	/**
	 * 设置 微信 登录验证码
	 * @param captcha
	 */
	public static void setSessionWxCaptcha(String captcha){
		setSessionAttr(SESSION_WX_CAPTCHA_STR, captcha);
	}
	
	/**
	 * 删除 微信 登录验证码
	 * @param captcha
	 */
	public static void removeSessionWxCaptcha(){
		getSession().removeAttribute(SESSION_WX_CAPTCHA_STR);
	}
	
	/**
	 * 获取 微信 个人openId
	 * @return
	 */
	public static String getSessionWxMyOpenId(){
		return (String)getSession().getAttribute(SESSION_WX_MYOPENID_STR);
	}
	
	/**
	 * 设置 微信 个人openId
	 * @param myOpenId
	 */
	public static void setSessionWxMyOpenId(String myOpenId){
		setSessionAttr(SESSION_WX_MYOPENID_STR, myOpenId);
	}
	
	/**
	 * 获取 微信 平台用户信息
	 * @return
	 */
	public static ComAppUser getSessionWxComAppUser(){
		return (ComAppUser)getSession().getAttribute(SESSION_WX_COMAPPUSER_OBJ);
	}
	
	/**
	 * 设置 微信 平台用户信息
	 * @param myOpenId
	 */
	public static void setSessionWxComAppUser(ComAppUser comAppUser){
		setSessionAttr(SESSION_WX_COMAPPUSER_OBJ, comAppUser);
	}
}
