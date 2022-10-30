package com.example.sokdak.member.dto.response

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.MemberRole

class FindMemberResponse(
    val id: Long,
    val memberName: String,
    val role: MemberRole
) {

    companion object {
        fun of(member: Member): FindMemberResponse {
            return FindMemberResponse(
                id = member.memberId!!,
                memberName = member.memberName.toString(),
                role = member.memberRole,
            )
        }
    }
}
