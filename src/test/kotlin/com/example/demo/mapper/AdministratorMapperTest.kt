package com.example.demo.mapper

import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.service.AdministratorsService
import com.example.demo.controller.AdministratorsController
import com.example.demo.dto.AdministratorCreateRequest

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get // Kotlin DSL
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.MediaType
import kotlin.test.assertEquals

@SpringBootTest
class AdministratorMapperTest {

    @Autowired
    private lateinit var administratorMapper: AdministratorMapper
    
    @Test
    fun testUpdate() {
        val administrator = Administrator(2, "管理者三四郎", "testadmin@sample.com", "testtest123")
        val effected: Int = administratorMapper.update(administrator)
        assertEquals(1, effected)
    }

}
