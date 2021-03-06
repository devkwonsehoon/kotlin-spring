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
    fun hello(): String {
//        val list = mutableListOf<String>()
//        val temp = list[0]
        return "Hello"
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
        // 1. μλ¬λΆμ
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
            this.message = "μμ²­μ μλ¬κ° λ°μνμ΅λλ€."
            this.path = request.requestURI.toString()
            this.timeStamp = LocalDateTime.now()
            this.errors = errors
        }
        // 3. return error response
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)

    }

//    // μ»¨νΈλ‘€λ¬ μ΄λλ°μ΄μ€λ₯Ό κ±°μΉμ§ μκ³  ν΄λμ€ λ΄λΆ ν΄λΉ μ»¨νΈλ‘€λ¬μμ μκΈ°λ μμΈλ₯Ό νΈλ€λ§
    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundExeption(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("index Error")
    }
}