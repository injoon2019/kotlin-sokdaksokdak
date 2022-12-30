package com.example.sokdak.member.domain

import org.hibernate.envers.Audited
import javax.persistence.*

@Audited
@Entity
class Member(
    @Embedded
    var memberName: Membername,

    @Embedded
    var password: Password? = Password("Abcd123!"),

    @Enumerated(EnumType.STRING)
    var role: Role = Role.USER,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var memberId: Long? = null
) {
    fun changeRole(role: String) {
        val targetRole = this.role.find(role)
        this.role = targetRole
    }
}
