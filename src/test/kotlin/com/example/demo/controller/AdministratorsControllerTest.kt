package com.example.demo.controller

import com.example.demo.mapper.AdministratorMapper
import com.example.demo.entity.Administrator
import com.example.demo.service.AdministratorsService
import com.example.demo.controller.AdministratorsController
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get // Kotlin DSL
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity



@SpringBootTest
@AutoConfigureMockMvc
class AdministratorsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val administratorMapper: AdministratorMapper = mockk()

    @Autowired
    private lateinit var administratorsService: AdministratorsService

    private lateinit var administratorsController: AdministratorsController

    @BeforeEach
    fun setup() {
        administratorsService = AdministratorsService(administratorMapper) // ✅ モックを注入
        administratorsController = AdministratorsController(administratorsService) // ✅ サービスを注入
    }

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

    @Test
    fun `GET administratorsService calltest - ok`() {
        val testData = listOf(
            Administrator(1, "管理者太郎", "admin@sample.com", "testtest"),
            Administrator(2, "管理者次郎", "admin2@sample.com", "testtesttest")
        )
        every { administratorMapper.findAll() } returns testData

        val response: ResponseEntity<List<Administrator>> = administratorsController.getAlladministrators()

        assertThat(response.statusCode.value()).isEqualTo(200)
        assertThat(response.body).isEqualTo(testData)
    }

    // @Test
    // fun `GET administratorsService calltest - error`() {
    //     val testData = listOf(
    //         Administrator(1, "管理者太郎", "admin@sample.com", "testtest"),
    //         Administrator(2, "管理者次郎", "admin2@sample.com", "testtesttest")
    //     )
    //     every { administratorMapper.findAll() } returns testData

    //     val response: ResponseEntity<List<Administrator>> = administratorsController.getAlladministrators()

    //     assertThat(response.statusCode.value()).isEqualTo(500)
    //     assertThat(response.body).isEqualTo(testData)
    // }


}
