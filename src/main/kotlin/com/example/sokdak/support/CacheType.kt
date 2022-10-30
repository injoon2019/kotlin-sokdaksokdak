package com.example.sokdak.support

enum class CacheType(
    val cacheName: String,
    val expiredAfterWrite: Long,
    val maximumSize: Long,
) {
    USER_PROFILE("userProfile", 60, 1000),
    ;
}
