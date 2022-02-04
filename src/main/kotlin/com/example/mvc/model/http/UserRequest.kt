package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.format.annotation.DateTimeFormat
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(
    @field:NotEmpty
    @field:Size(min = 2, max = 8) // data class validation 시에는 filed를 붙여준다.
    var name: String = "",

    @field:PositiveOrZero // 0과 같거나 큰 숫자
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String = "",

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
    var phoneNumber: String = "",

    @field:StringFormatDateTime
    var createdAt: String ?= null // yyyy-MM-dd hh:mm:ss
){
}
