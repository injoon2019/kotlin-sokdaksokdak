package com.example.sokdak.user.domain

import com.example.sokdak.user.exception.PasswordFormatException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasswordTest {

    @ParameterizedTest
    @ValueSource(strings = [""])
    fun `유저의 비밀번호는 1자 미만이면 예외 발생`(name: String) {
        assertThrows<PasswordFormatException> {
            Password(name)
        }
    }

    @Test
    fun `규칙을 만족하면 정상적으로 생성된다`() {
        Password("Abcd123!")
    }
}
