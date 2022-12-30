package com.example.sokdak.member.controller

import com.example.sokdak.member.dto.request.ChangeRoleRequest
import com.example.sokdak.member.dto.request.CreateMemberRequest
import com.example.sokdak.member.dto.response.ChangeRoleResponse
import com.example.sokdak.member.dto.response.CreateMemberResponse
import com.example.sokdak.member.dto.response.FindMemberResponse
import com.example.sokdak.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/{id}")
    fun findMemberById(@PathVariable id: Long): ResponseEntity<FindMemberResponse> {
        return ResponseEntity.ok(memberService.findMemberById(id))
    }

    @PatchMapping("/role")
    fun changeRole(@RequestBody request: ChangeRoleRequest): ResponseEntity<ChangeRoleResponse> {
        return ResponseEntity.ok(memberService.changeRole(request))
    }
}
