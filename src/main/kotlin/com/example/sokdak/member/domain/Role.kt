package com.example.sokdak.member.domain

import java.lang.IllegalArgumentException

enum class Role {
    USER,
    ADMIN,
    ;

    fun find(role: String): Role {
        return Role.values()
            .find { it.name == role}
            ?: throw IllegalArgumentException("존재하지 않는 역할입니다")
    }
}
