package com.example.mvc.controller.execption

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest // Web MVC 관련
@AutoConfigureMockMvc // 자동으로 MVC 관련 설정
class ExceptionApiControllerTest {

    // 가상의 요청을 만들어야 한다. -> mockMVC 필요
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `hello 테스트`() {
        mockMvc.perform(get("/api/exception/hello"))
            .andExpect (status().isOk)
            .andExpect (content().string("Hello"))
            .andDo (print())
    }

    @Test
    fun `get 테스트`() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sehoon")
        queryParams.add("age", "20")

        mockMvc.perform(get("/api/exception").queryParams(queryParams))
            .andExpect(status().isOk)
            .andExpect(content().string("sehoon 20"))
            .andDo(print())
    }

    @Test
    fun `get fail 테스트`() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sehoon")
        queryParams.add("age", "9")

        mockMvc.perform(get("/api/exception").queryParams(queryParams))
            .andExpect(status().isBadRequest)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("\$.result_code").value("fail"))
            .andExpect(jsonPath("\$.errors[0].field").value("age"))
            .andDo(print())
    }

    @Test
    fun `post 테스트`() {

        val userRequest = UserRequest().apply {
             this.name = "sehoon"
             this.age = 20
            this.phoneNumber = "010-1111-1111"
            this.address = "대한민국 어쩌구 저쩌구"
            this.email = "devkwonsehoon@naver.com"
            this.createdAt = "2020-02-20 20:20:20"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$.name").value("sehoon"))
            .andExpect(jsonPath("\$.email").value("devkwonsehoon@naver.com"))
            .andDo(print())

    }

    @Test
    fun `post fail 테스트`() {

        val userRequest = UserRequest().apply {
            this.name = "sehoon"
            this.age = -2
            this.phoneNumber = "010-1111-1111"
            this.address = "대한민국 어쩌구 저쩌구"
            this.email = "devkwonsehoon@naver.com"
            this.createdAt = "2020-02-20 20:20:20"
        }

        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType("application/json")
                .accept("application/json")
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

    }
}