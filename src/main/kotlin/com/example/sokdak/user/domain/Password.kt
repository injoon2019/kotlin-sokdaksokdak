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
        if (!PATTERN.matcher(value).matches()) {
            throw PasswordFormatException()
        }
    }

    companion object {
        val PATTERN = Pattern.compile("Pattern.compile(\"^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@\$!%*#?&])[A-Za-z\\\\d@\$!%*#?&]{8,20}\$\")")
    }
}
