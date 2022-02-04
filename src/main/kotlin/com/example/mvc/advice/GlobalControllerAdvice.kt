package com.example.mvc.advice

import com.example.mvc.controller.exception.ExceptionApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 아래와 같이 베이스 패키지 클래스를 지정하여 특정 클래스에서만 Exception을 처리할 수 도 있음
//@RestControllerAdvice(basePackageClasses = [ExceptionApiController::class])

//@RestControllerAdvice // RestController 에서 생기는 Exception들이 해당 클래스를 건너게 됨
class GlobalControllerAdvice {

    @ExceptionHandler(value = [RuntimeException::class]) // value에 어떠한 exception을 잡을 것인지 선언
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundExeption(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("index Error")
    }
}