package com.jx.common.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class ShiroSessionUtil {
	
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
	 * 验证码
	 */
	public static final String SESSION_CAPTCHA_STR = "sessionCaptcha";
	
	/**
	 * 用户id
	 */
	public static final String SESSION_USERID_STR = "sessionUserId";
	
	/**
	 * 登录用户信息
	 */
	public static final String SESSION_USER_OBJ = "sessionUser";
	
	/**
	 * 选择收货地址时存储的订单Id
	 */
	public static final String SESSION_ORDERID_CRA_STR = "sessionOrderIdcra";
	
	
	

	
	/**
	 * 获取 验证码
	 * @return
	 */
	public static String getCaptcha(){
		return (String)getSession().getAttribute(SESSION_CAPTCHA_STR);
	}
	
	/**
	 * 设置 验证码
	 * @param captcha
	 */
	public static void setCaptcha(String captcha){
		setSessionAttr(SESSION_CAPTCHA_STR, captcha);
	}
	
	/**
	 * 删除 验证码
	 * @param captcha
	 */
	public static void removeCaptcha(){
		getSession().removeAttribute(SESSION_CAPTCHA_STR);
	}
	
	/**
	 * 获取 用户id
	 * @return
	 */
	public static String getUserId(){
		return (String)getSession().getAttribute(SESSION_USERID_STR);
	}
	
	/**
	 * 设置 用户id
	 * @param userId
	 */
	public static void setUserId(String userId){
		setSessionAttr(SESSION_USERID_STR, userId);
	}
	
	/**
	 * 获取 登录用户信息
	 * @return
	 */
	public static Object getUser(){
		return getSession().getAttribute(SESSION_USER_OBJ);
	}
	
	/**
	 * 设置 登录用户信息
	 * @param myOpenId
	 */
	public static void setUser(Object user){
		setSessionAttr(SESSION_USER_OBJ, user);
	}
	
	/**
	 * 获取 选择收货地址时存储的订单Id
	 * @return
	 */
	public static String getOrderIdcra(){
		return (String)getSession().getAttribute(SESSION_ORDERID_CRA_STR);
	}
	
	/**
	 * 设置 选择收货地址时存储的订单Id
	 * @param orderIdcra
	 */
	public static void setOrderIdcra(String orderIdcra){
		setSessionAttr(SESSION_ORDERID_CRA_STR, orderIdcra);
	}
	
	
}
