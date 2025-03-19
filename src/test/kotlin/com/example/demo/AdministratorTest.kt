package com.example.demo

import com.example.demo.database.AdministratorsMapper
import com.example.demo.database.AdministratorsRecord
import com.example.demo.service.AdministratorsService
import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired


@SpringBootTest
class AdministratorsServiceTest {

    // MockKでモック作成
    private lateinit var administratorsMapper: AdministratorsMapper
    private lateinit var administratorsService: AdministratorsService

    @BeforeEach
    fun setup() {
        administratorsMapper = mockk()
        administratorsService = AdministratorsService(administratorsMapper)
    }

    @Test
    // `バッククオート`を使用することで文字列を関数名として使用可能
    fun `test findAll from administrators`() {
        // Mockのデータ準備
        val sample = listOf(
            AdministratorsRecord(1, "test1", "test1@example.com", "password1"),
            AdministratorsRecord(2, "test2", "test2@example.com", "password2")
        )

        // Mockを返すように設定
        every { administratorsMapper.selectMany(any()) } returns sample

        val result = administratorsService.findAll()

        assertEquals(2, result.size)
        assertEquals("test1", result[0].name)
        assertEquals("test2@example.com", result[1].mailAddress)

        verify(exactly = 1) { administratorsMapper.selectMany(any()) }


    }

}
