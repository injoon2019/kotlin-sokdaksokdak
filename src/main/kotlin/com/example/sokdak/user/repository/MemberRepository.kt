package com.example.sokdak.user.repository

import com.example.sokdak.user.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}
