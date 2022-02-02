package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse(
    var result: Result? = null,
    var description: String = "",

    @JsonProperty("user")
    var userRequest: List<UserRequest> = emptyList()
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
    var resultCode: String = "",
    var resultMessage: String = "",
)