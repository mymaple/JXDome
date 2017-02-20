package com.jx.common.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;


@SuppressWarnings("rawtypes")
@Component
public class ShiroCacheManager extends AbstractCacheManager {

	@Override
	protected Cache createCache(String cacheName) throws CacheException {
		return new ShiroCache<Object, Object>(cacheName);
	}

}
