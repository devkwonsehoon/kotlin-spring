package com.example.mvc.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller // 특정한 HTML를 내릴 때 사용하는 어노테이션
class PageController {

    @GetMapping("/main")
    fun main(): String {
        println("main init")

        /**
         * @Controller 어노테이션이 붙으면서 text/plain이 아닌 static main.html 이 return 된다.
         * @RestController 어노테이션이 붙게 되면 우리가 생각했던 것 처럼 text/plain의 main.html 이 return되게 된다.
         */
        return "main.html"

    }

    @ResponseBody
    @GetMapping("/test")
    fun response(): String {
        return "main.html"
    }
}