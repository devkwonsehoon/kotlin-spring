package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {

        val list = mutableListOf<String>()

        val temp = list[0]

        if (true) {
            throw RuntimeException("강제 Exception")
        }
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min= 2, max = 6)
        @RequestParam name: String,

        @Min(10)
        @RequestParam age: Int
    ): String {
        println(name)
        println(age)
        return name+" "+age
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e : ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        // 1. 에러분석
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach {


            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }

            errors.add(error)
        }

        // 2. make error response
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "fail"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생했습니다."
            this.path = request.requestURI.toString()
            this.timeStamp = LocalDateTime.now()
            this.errors = errors
        }
        // 3. return error response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }

    // 컨트롤러 어드바이스를 거치지 않고 클래스 내부 해당 컨트롤러에서 생기는 예외를 핸들링
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundExeption(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("index Error")
    }
}