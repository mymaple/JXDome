package com.jx.common.config.shiro.repository.impl;

import java.util.Set;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.jx.common.config.shiro.repository.CacheRepository;

@SuppressWarnings("unchecked")
public class LocalCacheOperations implements CacheRepository {
	
	private Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(10000000).build();

	@Override
	public <T> T get(String key) {
		
		return (T) cache.getIfPresent(key);
	}

	@Override
	public <T> void save(String key, T entity) {
		cache.put(key, entity);
	}

	@Override
	public void remove(String key) {
		cache.invalidate(key);
	}

	@Override
	public Set<String> keys() {
		return cache.asMap().keySet();
	}
}
