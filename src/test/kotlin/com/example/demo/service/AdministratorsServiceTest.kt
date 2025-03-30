package com.example.demo.service

import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.service.AdministratorsService
import com.example.demo.dto.AdministratorUpdateRequest
import com.example.demo.dto.AdministratorCreateRequest
import com.example.demo.exception.BadRequestExeption
import io.mockk.*
import org.springframework.boot.test.context.SpringBootTest
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.dao.DuplicateKeyException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.assertEquals


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
     * 管理者情報一覧 - 成功
     * @param expectationsList 期待される管理者一覧
     * @param resultList       返される管理者一覧
     */
    @Test
    fun `administrators findAll Test - success`() {
        val expectationsList = listOf(
            Administrator(1, "管理者太郎", "admin@sample.com", "testtest"),
            Administrator(2, "管理者次郎", "jiro-admin@sample.com", "jiro-test")
        )
        every { administratorMapper.findAll() } returns expectationsList

        val resultList = administratorsService.findAll()

        assertThat(resultList).hasSize(2)
        // assertThat(resultList).isEqualTo(expectationsList) // List全体をまとめて確認

        // id=1
        assertThat(resultList[0].name).isEqualTo("管理者太郎")
        assertThat(resultList[0].mailAddress).isEqualTo("admin@sample.com")
        assertThat(resultList[0].password).isEqualTo("testtest")
        // id=2
        assertThat(resultList[1].name).isEqualTo("管理者次郎")
        assertThat(resultList[1].mailAddress).isEqualTo("jiro-admin@sample.com")
        assertThat(resultList[1].password).isEqualTo("jiro-test")
    }

    /**
     * 管理者のid検索 - 成功
     * @param expect   id=1で期待する管理者情報
     * @param expected id=1で期待される管理者情報
     */
    fun `administrator findById Test - success`() {
        val expect = Administrator(1, "管理者太郎", "admin@sample.com", "testtest")
        every { administratorMapper.findById(1) } returns expect

        val expected = administratorsService.findById(1)
        assertThat(expected).isEqualTo(expect)
    }

    /**
     * id検索で管理者情報取得 - idがないとき
     * @param exception idなし(null)
     */
    @Test
    fun `administrators findAll Test - exception_not found`() {
        val id = 99
        every { administratorMapper.findById(99) } returns null
        val exception = assertThrows<NoSuchElementException> {
            administratorsService.findById(99)
        }
        assertEquals("指定IDの管理者が見つからない(id=$id)", exception.message)
    }

    /**
     * 管理者情報の登録 - 成功
     * @param request  新規登録情報
     * @param expected 新規登録する情報をAdministratorに格納
     * @param result   新規登録された情報
    */
    fun `administrator insert Test - success`() {
        val request = AdministratorCreateRequest("初心者管理者", "new_admin@example.com", "newadmin")

        // eq(...) を使って引数を一致させる方法
        val expected = Administrator(
            name = request.name,
            mailAddress = request.mailAddress,
            password = request.password
        )

        every { administratorMapper.insert(any()) } returns 1

        val result = administratorsService.insert(request)

        verify { administratorMapper.insert(eq(expected)) }

        assertEquals("初心者管理者", result.name) 

        /**
         * slot<Administrator>()
         * io.mockkパッケージから提供されているSlot<T>型のインスタンスを作る関数
         * MockKのユーティリティ,引数をキャプチャするための変数を作る
         */
        // val slot = slot<Administrator>() 
        //                                    
        // every { administratorMapper.insert(capture(slot)) } returns 1

        // administratorsService.insert(request)

        // verify { administratorMapper.insert(slot.captured) }

        // assertEquals("初心者管理者", slot.captured.name)
        // assertEquals("new_admin@example.com", slot.captured.mailAddress)
        // assertEquals("newadmin", slot.captured.password)
    }

    /**
     * 管理者所法の登録 - 失敗
     * @param request     mailAddressの重複した管理者
     * @param duplication mailAddressの重複でエラーが投げられることを期待
     */
    @Test
    fun `administrators duplicationInsert Test - exception_Bad Request`() {
        val request = AdministratorCreateRequest("管理者太郎", "admin@sample.com", "testtest")
        
        val duplication = Administrator(
            name = request.name,
            mailAddress = request.mailAddress,
            password = request.password
        )

        every { administratorMapper.insert(duplication) } throws DuplicateKeyException("メールアドレスの重複を検知")

        // 例外が投げられることを検証
        val exception = assertThrows<DuplicateKeyException> {
            administratorsService.insert(request)
        }

        // エラーメッセージの確認
        assertEquals("メールアドレスの重複を検知", exception.message)

        verify(exactly = 1) { administratorMapper.insert(duplication) }
    }


    /**
     * 管理者削除 - 成功
     * @param expected id=2の管理者削除
     */
    @Test
    fun `administrator delete Test - success`() {
        every { administratorMapper.delete(2) } returns 1

        val expected = administratorsService.delete(2)

        assertEquals(2, expected)
    }

    /**
     * 管理者削除 - 失敗
     * @param exception 削除対象がいないときにNoSuchElementExceptionのエラーを投げる
     */
    @Test
    fun `administrator delete Test - exception_not found`() {
        every { administratorMapper.delete(99) } throws NoSuchElementException()

        val exception = assertThrows<NoSuchElementException> {
            administratorsService.delete(99)
        }

        assertEquals("サーバー内部エラー(削除対象がいない)", exception.message)
    }

    /**
     * 管理者情報の更新 - 成功
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
