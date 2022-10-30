package com.example.sokdak.user.domain

import com.example.sokdak.user.exception.UsernameFormatException
import java.util.regex.Pattern
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Membername(
    @Column(name = "name")
    var value: String
) {
    init {
        if (value.length < MIN_LENGTH || value.length > MAX_LENGTH || !PATTERN.matcher(value).matches()) {
            throw UsernameFormatException()
        }
    }

    companion object {
        val PATTERN = Pattern.compile("^[0-9a-zA-Z]{4,16}$")
        const val MAX_LENGTH = 10
        const val MIN_LENGTH = 1
    }
}
