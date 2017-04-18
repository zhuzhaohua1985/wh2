/**
 * Copyright (R) 2017 isoftstone
 * @author: yjdai
 * @date: 2016年1月28日
 * @version: 1.0
 */
package com.iss.util;

import java.net.URL;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Ehcache缓存管理
 */
public class EhcacheUtil {
	private URL url;
	private CacheManager manager;
	private static EhcacheUtil ehCache;
	private static final String path = "/ehcache.xml";
	public static final String DATACACHE_NAME = "dataCache";

	private EhcacheUtil(String path) {
		url = getClass().getResource(path);
		manager = CacheManager.create(url);
	}

	public static EhcacheUtil getInstance() {
		if (ehCache == null) {
			ehCache = new EhcacheUtil(path);
		}
		return ehCache;
	}

	public void put(String cacheName, String key, Object value) {
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}
	
	public Object get(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public Cache getCache(String cacheName) {
		return manager.getCache(cacheName);
	}
	
	public void remove(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}
}
