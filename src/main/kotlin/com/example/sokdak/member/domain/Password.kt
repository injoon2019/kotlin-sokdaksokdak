package com.example.sokdak.member.domain

import com.example.sokdak.member.exception.PasswordFormatException
import com.example.sokdak.member.exception.UsernameFormatException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Password(
    @Column(name = "password")
    var value: String
) {

    init {
        if (value.length < 1) {
            throw PasswordFormatException()
        }
    }

}
