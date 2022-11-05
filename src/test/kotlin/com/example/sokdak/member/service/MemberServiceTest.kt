package com.example.sokdak.member.service

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.Membername
import com.example.sokdak.member.domain.Role
import com.example.sokdak.member.dto.request.ChangeRoleRequest
import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.FindMemberResponse
import com.example.sokdak.member.exception.MemberNotFoundException
import com.example.sokdak.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.CacheManager
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.TestConstructor
import java.lang.IllegalArgumentException

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class MemberServiceTest(
    val sut: MemberService,
    val memberRepository: MemberRepository,
    val cacheManager: CacheManager,
) {

    @BeforeEach
    fun setUp() {
        cacheManager.getCache("member")
            ?.clear()
    }

    @Test
    fun `회원을 승급할 수 있다`() {
        // given
        val savedUser = memberRepository.save(Member(Membername("thor")))
        val changeRoleRequest =
            ChangeRoleRequest(savedUser.memberId ?: throw IllegalStateException("회원 id가 존재하지 않습니다"), "ADMIN")

        // when
        sut.changeRole(changeRoleRequest)
        val changedUser =
            memberRepository.findByIdOrNull(savedUser.memberId) ?: throw IllegalStateException("회원이 존재하지 않습니다")

        // then
        assertThat(changedUser.role).isEqualTo(Role.ADMIN)
    }

    @Test
    fun `존재하지 않는 회원을 승급하면 예외가 발생한다`() {
        // given
        val changeRoleRequest = ChangeRoleRequest(9999L, "ADMIN")

        // when & then
        assertThrows<MemberNotFoundException> {
            sut.changeRole(changeRoleRequest)
        }
    }

    @Test
    fun `존재하지 않는 등급으로 승급하면 예외가 발생한다`() {
        // given
        val savedUser = memberRepository.save(Member(Membername("thor")))
        val changeRoleRequest =
            ChangeRoleRequest(savedUser.memberId ?: throw IllegalStateException("회원 id가 존재하지 않습니다"), "NONEXIST")

        // when & then
        assertThrows<IllegalArgumentException> {
            sut.changeRole(changeRoleRequest)
        }
    }

    @Test
    fun `로컬 캐시에 있으면 로컬 캐시에서 데이터를 조회한다`() {
        // given
        val createUserRequest = CreateMemberRequest("thor", Role.USER.name)
        val createdUser = sut.signUp(createUserRequest)

        // when
        val userFindResponse = sut.findMemberById(createdUser.id)

        // then
        assertThat(userFindResponse).isEqualTo(getCachedUser(createdUser.id))
    }

    @Test
    fun `로컬 캐시에 없으면 로컬 캐시에서 데이터를 조회하지 못한다`() {
        // given
        val createUserRequest = CreateMemberRequest("thor", Role.USER.name)
        val createdUser = sut.signUp(createUserRequest)

        // when & then
        assertThat(getCachedUser(createdUser.id)).isNull()
    }

    @Test
    fun `회원 정보가 변경되면 조회시 최신 변경 정보를 노출한다`() {
        // given
        val createUserRequest = CreateMemberRequest("thor", Role.USER.name)
        val createdUser = sut.signUp(createUserRequest)
        val cachedUser = sut.findMemberById(createdUser.id)

        // when
        sut.changeRole(ChangeRoleRequest(cachedUser.id, Role.ADMIN.name))
        val newFoundUser = sut.findMemberById(createdUser.id)

        // then
        assertThat(newFoundUser.role).isEqualTo(Role.ADMIN)
    }

    fun getCachedUser(id: Long): FindMemberResponse? {
        return cacheManager.getCache("member")
            ?.let { cache -> cache.get(id, FindMemberResponse::class.java) }
    }
}
