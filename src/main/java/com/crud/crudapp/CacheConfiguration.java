package com.crud.crudapp;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

	private final static int ONE_MINUTE = 60 * 1000;

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		cacheManager.setAllowNullValues(false);
		cacheManager.setCacheNames(Arrays.asList("productCache", "customerCache"));
		System.out.println("Cache Names : " + cacheManager.getCacheNames());
		return cacheManager;
	}

	@CacheEvict(value = "productCache", allEntries = true)
	@Scheduled(fixedDelay = ONE_MINUTE, initialDelay = 0)
	public void evictProductCache() {
		System.out.println("evictProductCache");
	}
}
