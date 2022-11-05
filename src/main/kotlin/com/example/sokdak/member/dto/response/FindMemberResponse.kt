package com.example.sokdak.member.dto.response

import com.example.sokdak.member.domain.Member
import com.example.sokdak.member.domain.Role

class FindMemberResponse(
    val id: Long,
    val memberName: String,
    val role: Role
) {

    companion object {
        fun of(member: Member): FindMemberResponse {
            return FindMemberResponse(
                id = member.memberId!!,
                memberName = member.memberName.toString(),
                role = member.role,
            )
        }
    }
}
