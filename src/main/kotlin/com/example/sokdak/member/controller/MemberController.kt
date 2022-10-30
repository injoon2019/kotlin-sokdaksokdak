package com.example.sokdak.member.controller

import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.CreateMemberResponse
import com.example.sokdak.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun singUp(@RequestBody createMemberRequest: CreateMemberRequest): ResponseEntity<Unit> {
        val createdMemberResponse = memberService.signUp(createMemberRequest)
        return ResponseEntity.created(URI.create("/members/" + createdMemberResponse.id)).build();
    }
}
