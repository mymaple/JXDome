package com.jx.common.config.shiro;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.jx.common.config.shiro.repository.UserSessionRelationCacheService;
import com.jx.common.config.shiro.repository.impl.UserSessionRelationLocalCacheServiceImpl;
import com.jx.common.util.SpringContextUtil;

public class ShiroSecurityHelper {
	
	private static ShiroCacheManager cacheManager;

	private static UserSessionRelationCacheService userSessionRelationCacheService;


	/**
	 * 从cache拿当前user
	 * @return
	 */
//	public static User getCurrentUser() {
//
//		Subject subject = getSubject();
//		PrincipalCollection collection = subject.getPrincipals();
//		if (null != collection && !collection.isEmpty()) {
//			return (User) collection.iterator().next();
//		}
//		return null;
//	}

	/**
	 * 获得当前用户名
	 * @return
	 */
	public static String getCurrentUserName() {
		return (String)getSubject().getPrincipal();
	}

	/**
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * @return
	 */
	public static String getSessionId() {
		Session session = getSession();
		if (null == session) {
			return null;
		}
		return getSession().getId().toString();
	}
	
	/**此方法会会遍历所有的session
	 * @param username
	 * @return
	 */
	public static Session getSessionByUsername(String username){
		Cache<Object, Object> sessionCache = getSessionCache();
		String sessionid = userSessionRelationCacheService.getSessionid(username);
		return (Session) sessionCache.get(sessionid);
	}
	
	/**踢除用户,此方法会会遍历所有的session
	 * @param username
	 */
	public static void kickOutUser(String username){
		Session session = getSessionByUsername(username);
		if(null != session && !StringUtils.equals(String.valueOf(session.getId()), ShiroSecurityHelper.getSessionId())){
			clearAuthenticationInfo(session.getId());
			clearAuthorizationInfo(username);
		}
	}

	/**将用户和session绑定
	 * @param username
	 */
	public static void bindUserAndSession(String username){
		bindUserAndSession(username,getSessionId());
	}

	/** 将用户和session绑定
	 * @param username
	 * @param sessionid
	 */
	public static void bindUserAndSession(String username,String sessionid){
		userSessionRelationCacheService.bind(username, sessionid);
	}

	/**获取session缓存
	 * @return
	 */
	public static Cache<Object, Object> getSessionCache(){
		//shiro-activeSessionCache 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
		//如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
		return cacheManager.getCache("shiro-activeSessionCache");
	}

	/**获取用户认证信息缓存
	 * @return
	 */
	public static Cache<Object, Object> getAuthorizationCache(){
		//ShiroCasRealm.authorizationCache 为shiro自义cache名(shiroCasRealm为我们定义的reaml类的类名)
		return cacheManager.getCache(ShiroRealm.REALM_NAME+".authorizationCache");
	}

	/**获取缓存管理
	 * @return
	 */
	public static ShiroCacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param _cacheManager
	 */
	public static void initStaticField(ShiroCacheManager _cacheManager){
		cacheManager = _cacheManager;
		userSessionRelationCacheService = SpringContextUtil.getBean(UserSessionRelationCacheService.class);
		if(null == userSessionRelationCacheService){
			userSessionRelationCacheService = new UserSessionRelationLocalCacheServiceImpl();
		}
	}

	/**
	 * 判断当前用户是否已通过认证
	 * @return
	 */
	public static boolean hasAuthenticated() {
		return getSubject().isAuthenticated();
	}

	private static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	
	
	/**
	 * 清除用户的授权信息
	 * @param username
	 */
	public static void clearAuthorizationInfo(String username){
		Cache<Object, Object> cache = ShiroSecurityHelper.getAuthorizationCache();
		cache.remove(username);
	}
	
	/**
	 * 清除当前用户的授权信息
	 */
	public static void clearAuthorizationInfo(){
		if(SecurityUtils.getSubject().isAuthenticated()){
			clearAuthorizationInfo(ShiroSecurityHelper.getCurrentUserName());
		}
	}
	
	/**清除session(认证信息)
	 * @param JSESSIONID
	 */
	public static void clearAuthenticationInfo(Serializable JSESSIONID){
		//shiro-activeSessionCache 为shiro自义cache名，该名在org.apache.shiro.session.mgt.eis.CachingSessionDAO抽象类中定义
		//如果要改变此名，可以将名称注入到sessionDao中，例如注入到CachingSessionDAO的子类EnterpriseCacheSessionDAO类中
		Cache<Object, Object> cache = ShiroSecurityHelper.getSessionCache();
		cache.remove(JSESSIONID);
	}
	
	
}
