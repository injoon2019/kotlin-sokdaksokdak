package com.example.sokdak.user.domain

import com.example.sokdak.user.exception.PasswordFormatException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PasswordTest {

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "abcdAbcd", "abCd1234", "12341234", "abcd12!", "abcd1234^", "123456789012345678901"])
    fun `유저의 비밀번호는 영어 대소문자, 숫자, 특수문자 세 카테고리를 모두 포함하는 8자이상 20자 이하의 문자열이 아니면 예외가 발생한다`(name: String) {
        assertThrows<PasswordFormatException> {
            Password(name)
        }
    }
}
