package com.example.sokdak.member.service

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.Role
import com.example.sokdak.member.domain.Membername
import com.example.sokdak.member.domain.Password
import com.example.sokdak.member.dto.request.ChangeRoleRequest
import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.ChangeRoleResponse
import com.example.sokdak.member.dto.response.CreateMemberResponse
import com.example.sokdak.member.dto.response.FindMemberResponse
import com.example.sokdak.member.exception.MemberNotFoundException
import com.example.sokdak.member.repository.MemberRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun signUp(createMemberRequest: CreateMemberRequest): CreateMemberResponse {
        val createdMember = memberRepository.save(
            Member(
                memberName = Membername(createMemberRequest.username),
                password = Password(createMemberRequest.password),
                role = Role.USER
            )
        )
        return CreateMemberResponse.of(createdMember)
    }

    @Cacheable(cacheNames = ["member"])
    fun findMemberById(id: Long): FindMemberResponse {
        val foundMember = memberRepository.findById(id).get()
        return FindMemberResponse.of(foundMember)
    }

    @Transactional
    @CacheEvict(value = ["member"], key = "#changeRoleRequest.id")
    fun changeRole(changeRoleRequest: ChangeRoleRequest): ChangeRoleResponse {
        val foundMember = memberRepository.findByIdOrNull(changeRoleRequest.id)
            ?: throw MemberNotFoundException()
        foundMember.changeRole(changeRoleRequest.role)
        return ChangeRoleResponse(foundMember.role)
    }
}
