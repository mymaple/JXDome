package com.jx.common.config.shiro.repository.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jx.common.config.shiro.repository.UserSessionRelationCacheService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**

 * Created by Dylan on 2016/1/21.

 */

@Service("userSessionRelationCacheService")
public class UserSessionRelationLocalCacheServiceImpl implements UserSessionRelationCacheService {

    private final static Logger log = LoggerFactory.getLogger(UserSessionRelationLocalCacheServiceImpl.class);

    private Cache<String, String> cache = CacheBuilder.newBuilder().build();


    @Override
    public void bind(String username, String sessionid) {
        if(log.isDebugEnabled()){
            log.debug("将用户{}与session{}绑定",username,sessionid);
        }
        cache.put(username,sessionid);
    }

    @Override
    public String getSessionid(String username) {

        return cache.getIfPresent(username);
    }

    @Override
    public String getAndRemoveSessionid(String username) {
        String sessionid = getSessionid(username);
        if(StringUtils.isNotBlank(sessionid)){
            cache.invalidate(username);
        }
        return sessionid;
    }
}
