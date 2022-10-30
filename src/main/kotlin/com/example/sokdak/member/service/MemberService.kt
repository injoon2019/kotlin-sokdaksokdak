package com.example.sokdak.member.service

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.MemberRole
import com.example.sokdak.member.domain.Membername
import com.example.sokdak.member.domain.Password
import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.CreateMemberResponse
import com.example.sokdak.member.dto.response.FindMemberResponse
import com.example.sokdak.member.repository.MemberRepository
import com.example.sokdak.support.CacheType
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.lang.RuntimeException

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

    @Cacheable(cacheNames = ["userProfile"])
    fun findMemberById(id: Long): FindMemberResponse {
        val foundMember = memberRepository.findById(id).get()
        return FindMemberResponse.of(foundMember)
    }
}
