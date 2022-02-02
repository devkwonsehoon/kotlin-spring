package com.example.mvc.controller.put

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @PutMapping("/put-mapping/object")
    fun putObjectMapping(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {

        if (bindingResult.hasErrors()) {
            val msg = StringBuilder()

            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val message = it.defaultMessage

                msg.append(field.field + ":" + message + "\n")
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg.toString())
        }

        return ResponseEntity.ok("")
//        return UserResponse().apply {
//
//            this.result = Result().apply {
//                this.resultCode = "OK"
//                this.resultMessage = "success"
//            }
//
//            this.description = "testtest"
//
//            val users = mutableListOf<UserRequest>()
//
//            users.add(userRequest)
//
//            users.add(UserRequest().apply {
//                this.name = "test"
//                this.age = 24
//                this.email = "test@test.com"
//                this.address = "dawdawd"
//                this.phoneNumber = "0213129471294"
//            })
//
//            users.add(UserRequest().apply {
//                this.name = "test2"
//                this.age = 242
//                this.email = "test2@test.com"
//                this.address = "dawdawd2"
//                this.phoneNumber = "02131294712942"
//            })
//
//            this.userRequest = users
//
//        }


    }

}