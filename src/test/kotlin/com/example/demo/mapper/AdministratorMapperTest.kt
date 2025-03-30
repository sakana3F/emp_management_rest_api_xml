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
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.MediaType
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@SpringBootTest
// h2を使用したDB処理(模擬)
// application-test.ymlで"test"を指定
@ActiveProfiles("test")
@Sql(
    scripts = ["/schema.sql", "/data.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class AdministratorMapperTest {

    @Autowired
    private lateinit var administratorMapper: AdministratorMapper

    /**
     * 管理者一覧の取得
     * @param result 管理者一覧
     */
    @Test
    fun testFindByAll() {
        val result = administratorMapper.findAll()
        assertThat(result).isNotEmpty()
    }

    /**
     * 管理者をid検索して情報取得
     * @param expect 期待する値
     * @param actual 実際に返される値
     */
    @Test
    fun testFindById() {
        val expect = Administrator(1, "管理者太郎", "admin@sample.com", "testtest1")
        val actual = administratorMapper.findById(1) // 実際に返されるid=1の取得した情報の確認
        assertEquals(expect, actual)
        // assertThat(actual?.name).isEqualTo("管理者太郎")
        // assertThat(actual?.mailAddress).isEqualTo("admin@sample.com")
        // assertThat(actual?.password).isEqualTo("testtest1")
    }

    /**
     * 管理者登録
     * @param newAdmin 登録したい情報
     * @param expected 更新情報をAdministratorに登録
     * @param actual   Administratorに登録されている最後の情報が登録した情報になっているかの確認
     */ 
    @Test
    fun testInsert() {
        val newAdmin = Administrator(name = "初心者管理者", mailAddress = "new_admin@example.com", password = "newadmin")
        val expected = administratorMapper.insert(newAdmin)
        assertEquals(1, expected)
        
        val actual = administratorMapper.findAll().lastOrNull() //.lastOrNull():リストの最後の要素を取り出す
        assertThat(actual).isNotNull()
        assertThat(actual?.name).isEqualTo("初心者管理者")
        assertThat(actual?.mailAddress).isEqualTo("new_admin@example.com")
    }

    /**
     * 管理者削除(id=2)
     * @param before   削除前の状態
     * @param effected 削除後の状態
     * @param after    削除されたことの確認(null)
     */ 
    @Test
    fun testDelete() {
        val before = administratorMapper.findById(2) // 削除前の状態を確認(NotNull=データがある)
        assertNotNull(before)

        val effected = administratorMapper.delete(2) // 削除後の状態を確認
        assertEquals(1, effected)                    // 削除した件数

        val after = administratorMapper.findById(2)
        assertNull(after) // nullになってることを確認
    }
    
    /**
     * 管理者情報更新(id=3)
     * @param administrator 更新内容
     * @param effected      更新後の管理者情報
     * @param expected      更新後の情報の確認
     */ 
    @Test
    fun testUpdate() {
        val administrator = Administrator(id = 3, name = "管理者さんしろう", mailAddress = "update_test@example.com", password = "testtest345") // 更新内容
        val effected: Int = administratorMapper.update(administrator) // 更新後の管理者情報
        assertEquals(1, effected) // 更新件数が1件であることの確認

        val expected = administratorMapper.findById(3) // id=3が更新後の情報になっているかの確認
        assertThat(expected?.name).isEqualTo("管理者さんしろう")
        assertThat(expected?.mailAddress).isEqualTo("update_test@example.com")
        assertThat(expected?.password).isEqualTo("testtest345")
    }
}
