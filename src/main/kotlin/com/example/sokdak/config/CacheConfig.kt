package com.example.sokdak.config

import com.example.sokdak.support.CacheType
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val caches: List<CaffeineCache> = Arrays.stream(CacheType.values())
            .map { cache ->
                CaffeineCache(
                    cache.cacheName, Caffeine.newBuilder()
                        .recordStats()
                        .expireAfterWrite(cache.expiredAfterWrite, TimeUnit.SECONDS)
                        .maximumSize(cache.maximumSize)
                        .build()
                )
            }
            .collect(Collectors.toList())

        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(caches)

        return cacheManager
    }
}
