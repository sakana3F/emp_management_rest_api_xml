package com.example.demo.controller

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
import com.ninjasquad.springmockk.SpykBean
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity



@SpringBootTest
@AutoConfigureMockMvc
class AdministratorsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpykBean
    private lateinit var administratorsService: AdministratorsService

    val expectationsListJson: String = """
        [    
            {
                "id": 1,
                "name": "管理者太郎",
                "mailAddress": "admin@sample.com",
                "password": "testtest"
            },
            {
                "id": 2,
                "name": "管理者次郎",
                "mailAddress": "adomin2@sample.com",
                "password": "testtesttest"
            }
        ]
    """

    val expectationsJson: String = """
            {    
                "id": 1,
                "name": "管理者太郎",
                "mailAddress": "admin@sample.com",
                "password": "testtest"
            }
        """

    // 400_Bad Request
    val expectedRequestjson: String = """
            {
                "status": 400,
                "error": "Bad Request",
                "path": "/ErrorResponse"
            }
        """

    // 404_Not Found
    val expectedFoundjson: String = """
            {
                "status": 404,
                "error": "Not Found",
                "path": "/ErrorResponse"
            }
        """

    // 500_Internal_Serve_Error
    val serverError: String = """
            {
               "status": 500,
               "error": "Internal Server Error",
               "path": "/ErrorResponse"
           }
        """

    // 管理者一覧取得 - 管理者一覧の取得に成功(200)
    @Test
    fun `GET administratorsList test - success`() {

        val mockData = listOf(
            Administrator(1, "管理者太郎", "admin@sample.com", "testtest"),
            Administrator(2, "管理者次郎", "adomin2@sample.com", "testtesttest")
        )
        every { administratorsService.findAll() } returns mockData

        mockMvc.get("/administrators")
            .andExpect {
                status { isOk() }
                content { json(expectationsListJson) }
            }
    }

    // 管理者一覧取得 - リクエスト不正(404)
    // → 不要
    // @Test
    // fun `GET administratorsList test - error`() {

    //     mockMvc.get("/ErrorResponse")
    //         .andExpect {
    //             status { is4xxClientError() }
    //             content{ json(expectedFoundjson) }
    //         }
    // }

    // 管理者一覧取得 - サーバ内部エラー(500)
    // @Test
    // fun `Server internal error`() {
    //     
    //     mockMvc.get("/ErrorResponse")
    //         .andExpect {
    //             status { is5xxServerError() }
    //         }
    //         .andExpect{
    //             content().json(serverError)
    //         }    
    // }

    // 管理者一覧取得 - 管理者情報取得に成功(200)
    @Test
    fun `administratorsFindById test - success`() {
        val mockData = listOf(
            Administrator(1, "管理者太郎", "admin@sample.com", "testtest")
        )
        every { administratorsService.findById(1) } returns mockData[0]
    
        mockMvc.get("/administrators/1")
            .andExpect {
                status { isOk() }
                content { json(expectationsJson) }
            }

    }

    // // 管理者個別取得 - IDの形式不正など(400)
    // @Test
    // fun `administratorsFindById test - requestError`() {

    //     mockMvc.get("/administrators/a1")
    //         .andExpect {
    //             status { is4xxClientError() }
    //             content{ json(expectedRequestjson) }
    //         }
    // }

    // 管理者個別取得 - 指定のIDの管理者が見つからない(404)
    // @Test
    // fun `administratorsFindById test - foundError`() {

    //     mockMvc.get("/administrators/22")
    //         .andExpect {
    //             status { is4xxClientError() }
    //         }
    // }

    // 管理者個別取得 - サーバー内部エラー(500)
    // @Test
    // fun `administratorsFindById test - serverError`() {

    //     mockMvc.get("/{id}")
    //         .andExpect {
    //             status { is5xxServerError() }
    //             content{ json(serverError) }
    //         }
    // }

    // 管理者登録 - 新規管理者の登録成功(200)
    // @Test
    // fun `administratorCreate - success`() {
    //     val mockData = listOf(
    //         Administrator(3, "New Manager" , "new.manager@example.com", "pass123")
    //     )
    //     every { administrator.insert("New Manager" , "new.manager@example.com", "pass123") } returns mockData

    //     val expectationsJson: String = """
    //         {    
    //             "id": 3,
    //             "name": "New Manager",
    //             "mailAddress": "new.manager@example.com",
    //             "password": "pass12"
    //         }
    //     """

    //     mockMvc.get("/AdministratorCreateRequest")
    //         .andExpect {
    //             status { isOk() }
    //             content{ json(expectationsJson) }
    //         }    
    // }

    // 管理者登録 - サーバ内部エラー(500)
    // @Test
    // fun `administratorCreate - error`() {
    //     val serverError: String = """
    //         {
    //            "status": 500,
    //            "error": "Internal Server Error",
    //            "path": "/ErrorResponse"
    //        }
    //    """

    //     mockMvc.get("/ErrorResponse")
    //         .andExpect {
    //             status { is5xxServerError() }
    //             content{ json(serverError) }
    //         }    
    // }

    // 管理者更新 - 成功(200)
    // @Test
    // fun`administratorsUpdate - success`() {
    //     val mockData = listOf(
    //         Administrator(3, "Updated Manager" , "updated.manager@example.com", "pass456")
    //     )
    //     every{ administratorsService.update(3, "Updated Manager" , "updated.manager@example.com", "pass456") } returns mockData

    //     val expectationsJson: String = """
    //         {    
    //             "id": 3,
    //             "name": "Updated Manager",
    //             "mailAddress": "updated.manager@example.com",
    //             "password": "pass456"
    //         }
    //     """

    //     mockMvc.get("/ErrorResponse")
    //         .andExpect {
    //             status { isOk() }
    //             content{ json(expectationsJson) }
    //         } 
    // }



    // 管理者更新 - リクエスト不正(400)


    // 管理者更新 - サーバ内部エラー(500)


    // 管理者削除 - 削除成功 (レスポンスボディなし)
    // @Test
    // fun`administratorsUpdate - success`() {
    //     val mockData = listOf(
    //         Administrator()
    //     )
    //     every{ administratorsService.update() } returns mockData

    //     mockMvc.get("/ErrorResponse")
    //         .andExpect {
    //             status { isOk() }
    //         } 
    // }


    // 管理者削除 - サーバ内部エラー(500)


        

}
