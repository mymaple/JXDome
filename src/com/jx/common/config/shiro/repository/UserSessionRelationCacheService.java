package com.jx.common.config.shiro.repository;

public interface UserSessionRelationCacheService {
	
	/**
     * @param username
     * @param sessionid
     */
    void  bind(String username,String sessionid);

    /**
     * @param username
     * @return
     */
    String  getSessionid(String username);

    /**
     * @param username
     * @return
     */
    String getAndRemoveSessionid(String username);
}
