package com.example.sokdak.member.acceptance

import com.example.sokdak.member.dto.request.CreateMemberRequest
import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberAcceptanceTest(
    @LocalServerPort private val port: Int
) {

    @Test
    fun `회원가입을 할 수 있다`() {
        val createMemberRequest = CreateMemberRequest("thor", "Abcd123!")

        val response = RestAssured.given().port(port).log().all()
            .body(createMemberRequest)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .When().post("/members/signup")
            .then().log().all()
            .extract()

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}

fun RequestSpecification.When(): RequestSpecification {
    return this.`when`()
}
