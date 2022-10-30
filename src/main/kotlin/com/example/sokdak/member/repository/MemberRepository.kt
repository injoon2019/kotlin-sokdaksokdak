package com.example.sokdak.member.repository

import com.example.sokdak.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}
