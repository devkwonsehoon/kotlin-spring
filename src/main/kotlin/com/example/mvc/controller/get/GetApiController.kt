package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController // 해당 컨트롤러는 REST API임
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    // 아래와 같이 배열로 path를 선언할 수 있으며, 두 URI 모두 접근 가능하다.
    @GetMapping(path = ["/hello", "/abcd"]) // GET http://localhost:8080/api/hello, GET http://localhost:8080/api/abcd
    fun hello(): String {
        return "Hello Kotlin"
    }

    // @RequestMapping 을 이용해 method, path 각각 설정하여 라우터를 만들어 낼 수 있다.
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // param은 @PathVarialble로 접근할 수 있다.
    @GetMapping("/get-mapping/path-variable/{name}/{age}") //GET http://localhost:8080/api/get-mapping/path-variable/{name}/{age}
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return name + " " + age
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") //GET http://localhost:8080/api/get-mapping/path-variable/{name}/{age}
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
        val name = "kotlin"

        println("${_name}, ${age}")
        return _name + " " + age
    }

    // Query Parameter : https://localhost:8080/api/page?key=value&key=value&key=value
    // Query는 @PathVarialble로 접근할 수 있다.
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam name: String,
        @RequestParam(value = "age") age: Int
    ): String {
        println("${name}, ${age}")
        return name + " " + age
    }

    // 여러가지 값을 받아야 할 때는 객체 매핑을 이용할 수 있다.
    // name, age, address, email
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    // Map을 이용해 받을 수도 있다.
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        println(phoneNumber)
        return map
    }

}