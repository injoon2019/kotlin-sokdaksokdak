package com.example.sokdak.member.dto.response

import com.example.sokdak.member.domain.Member

class CreateMemberResponse(
    id: Long,
    memberName: String
) {

    companion object {
        fun of(member: Member): CreateMemberResponse {
            return CreateMemberResponse(
                id = member.memberId!!,
                memberName = member.memberName.toString()
            )
        }
    }
}
