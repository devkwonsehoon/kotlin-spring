package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse (
    @field:JsonProperty("result_code")
    var resultCode: String = "",

    @field:JsonProperty("http_status")
    var httpStatus: String = "",

    @field:JsonProperty("http_method")
    var httpMethod: String = "",

    var message: String = "",
    var path: String = "",
    var timeStamp: LocalDateTime? = null,
    var errors: MutableList<Error> = mutableListOf()
)

data class Error (
    var field: String = "",
    var message: String = "",
    var value: Any =""
)