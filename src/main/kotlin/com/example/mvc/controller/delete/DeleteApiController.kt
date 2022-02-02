package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam(value = "name")
        @NotNull(message = "name 값은 필수값입니다.")
        @Size(min = 2, max = 10)
        _name: String,

        @NotNull(message = "age 값은 필수값입니다.")
        @Min(value = 20, message = "20보다 커야합니다.")
        @RequestParam(value = "age")
        _age: Int
    ): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }

    @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
    fun deleteMappingPath(
        @PathVariable(value = "name")
        @NotNull(message = "name 값은 필수값입니다.")
        @Size(min = 2, max = 10)
        _name: String,

        @NotNull(message = "age 값은 필수값입니다.")
        @Min(value = 20, message = "20보다 커야합니다.")
        @PathVariable(value = "age")
        _age: Int
    ): String {
        println(_name)
        println(_age)
        return _name + " " + _age
    }

}