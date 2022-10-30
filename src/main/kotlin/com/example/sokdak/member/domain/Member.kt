package com.example.sokdak.member.domain

import javax.persistence.*

@Entity
class Member(
    @Embedded
    var memberName: Membername,

    @Embedded
    var password: Password,

    @Enumerated(EnumType.STRING)
    var memberRole: MemberRole = MemberRole.USER,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var memberId: Long? = null
) {
}
