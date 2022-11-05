package com.example.sokdak.member.repository

import com.example.sokdak.member.domain.Password
import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.Role
import com.example.sokdak.member.domain.Membername
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun `유저를 저장한다`() {
        var member = Member(Membername("thor"), Password("Abcd123!"), Role.USER)

        memberRepository.save(member)
        val foundUser = memberRepository.findById(1L).get()

        foundUser.password!!.value shouldBe "Abcd123!"
        foundUser.role shouldBe Role.USER
        foundUser.memberId shouldBe 1L
    }
}
