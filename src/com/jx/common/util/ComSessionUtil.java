package com.jx.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class ComSessionUtil {
	
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
	 * 系统 登录验证码
	 */
	public static final String SESSION_COM_CAPTCHA_STR = "sessionComCaptcha";
	
	/**
	 * 系统 登录用户id
	 */
	public static final String SESSION_COM_USERID_STR = "sessionComUserId";
	

	/**
	 * 系统 登录验证码
	 * @return
	 */
	public static String getSessionWxCaptcha(){
		return (String)getSession().getAttribute(SESSION_COM_CAPTCHA_STR);
	}
	
	/**
	 * 系统 登录验证码
	 * @param captcha
	 */
	public static void setSessionWxCaptcha(String captcha){
		setSessionAttr(SESSION_COM_CAPTCHA_STR, captcha);
	}
	
	/**
	 * 系统 登录用户id
	 * @return
	 */
	public static String getSessionComUserId(){
		return (String)getSession().getAttribute(SESSION_COM_USERID_STR);
	}
	
	/**
	 * 系统 登录用户id
	 * @param captcha
	 */
	public static void setSessionComUserId(String userId){
		setSessionAttr(SESSION_COM_USERID_STR, userId);
	}
	
	
}
