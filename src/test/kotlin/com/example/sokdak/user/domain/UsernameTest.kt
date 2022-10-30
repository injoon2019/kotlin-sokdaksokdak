package com.example.sokdak.user.domain

import com.example.sokdak.user.exception.UsernameFormatException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UsernameTest {

    @ParameterizedTest
    @ValueSource(strings = ["sok", "sokdaksokdaksokkk", "가sokdak", "sokdak!", "", " "])
    fun `유저의 아이디는 숫자와 영문만을 포함한 4자 이상 16자가 아니면 예외가 발생한다`(name: String) {

        assertThrows<UsernameFormatException> {
            Username(name)
        }
    }
}
