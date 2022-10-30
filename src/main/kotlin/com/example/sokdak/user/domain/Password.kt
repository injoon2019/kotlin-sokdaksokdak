package com.example.sokdak.user.domain

import com.example.sokdak.user.exception.PasswordFormatException
import com.example.sokdak.user.exception.UsernameFormatException
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
