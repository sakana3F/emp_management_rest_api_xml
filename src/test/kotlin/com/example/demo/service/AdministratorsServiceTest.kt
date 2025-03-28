package com.example.demo.service

import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.service.AdministratorsService
import com.example.demo.dto.AdministratorUpdateRequest
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


@ActiveProfiles("test")
@Sql(
    scripts = ["/schema.sql", "/data.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@SpringBootTest
class AdministratorsServicetTest {

    @MockkBean
    private lateinit var administratorMapper: AdministratorMapper

    @Autowired
    private lateinit var administratorsService: AdministratorsService

    /**
     * 管理者情報の更新
     * @param effect   更新する管理者情報
     * @param effected 更新後の管理者情報
     * @return         更新件数
     */
    @Test
    fun `administrator Update Test - success`() {

        // 更新したい(後)管理者情報
        val request = AdministratorUpdateRequest(
            name = "管理者さんしろう",
            mailAddress = "update_test@sample.com",
            password = "testtest345"
        )

        // 管理者情報の更新作業
        val effect = Administrator(3, request.name, request.mailAddress, request.password)

        // update件数が1件かどうか（updateは行われた件数が返される。
        // 今回は、1ならOK。0とか2ならNG
        every { administratorMapper.update(effect) } returns 1
        every { administratorMapper.findById(3) } returns effect

        // id=3が更新した内容(request)になっているかの確認
        val effected = administratorsService.update(3, request)

        // requestで指定した管理者情報と更新したい内容が同じになっているかの確認(今回はnameだけあってればあってると判断)
        assertThat(effected.name).isEqualTo("管理者さんしろう")

        // MapperのupdateとfindByIdが指定通りに呼び出されているかの確認
        verify() { administratorMapper.update(effect) }
        verify() { administratorMapper.findById(3) }
    }
    
}
