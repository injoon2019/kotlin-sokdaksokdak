package com.example.sokdak.member.service

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.MemberRole
import com.example.sokdak.member.domain.Membername
import com.example.sokdak.member.domain.Password
import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.CreateMemberResponse
import com.example.sokdak.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun signUp(createMemberRequest: CreateMemberRequest): CreateMemberResponse {
        val createdMember = memberRepository.save(
            Member(
                memberName = Membername(createMemberRequest.username),
                password = Password(createMemberRequest.password),
                memberRole = MemberRole.USER
            )
        )
        return CreateMemberResponse.of(createdMember)
    }
}
