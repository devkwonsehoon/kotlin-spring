package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. GET 4xx
    // GET localhost/api/response
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {

        return age?.let {
            // age < 20 -> 400 err
            if ( age < 20 ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail! age is lower than 20")
            ResponseEntity.status(HttpStatus.OK).body("OK")

        }?: kotlin.run {
            // age == null -> 400 err
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail! age is null")
        }
    }

    // 2. POST 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. PUT 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 기존 데이터가 없어서 새로 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. DELETE 5xx
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)

    }

}