package com.example.demo.controller

import com.example.demo.mapper.AdministratorMapper
import com.example.demo.entity.Administrator
import com.example.demo.service.AdministratorsService
import com.example.demo.controller.AdministratorsController
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get // Kotlin DSL
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.beans.factory.annotation.Autowired


@SpringBootTest
@AutoConfigureMockMvc
class AdministratorsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `GET administratorsId test - ok`() {
        val expectationsJson: String = """
            {    
                "id": 1,
                "name": "管理者太郎",
                "mailAddress": "admin@sample.com",
                "password": "testtest"
            }
            """

        mockMvc.get("/administrators")
            .andExpect {
                status { isOk() }
            }
            .andExpect { 
                content().json(expectationsJson)
            }
    }

    @Test
    fun `GET administratorsId test - error`() {
        val expectedError: String = """
            {
                "timestamp": "2025-03-21T09:18:22.130+00:00",
                "status": 404,
                "error": "Not Found",
                "path": "/errorResponse"
            }
            """

        mockMvc.get("/ErrorResponse")
            .andExpect {
                status { is4xxClientError() }
            }
            .andExpect{
                content().json(expectedError)
            }
    }

    // @Test

}
