// package com.example.demo

// import com.example.demo.mapper.AdministratorMapper
// import com.example.demo.entity.Administrator
// import com.example.demo.service.AdministratorsService
// import com.example.demo.dto.AdministratorCreateRequest
// import io.mockk.*
// import org.junit.jupiter.api.Test
// import org.junit.jupiter.api.BeforeEach
// import kotlin.test.assertEquals
// import org.assertj.core.api.Assertions.assertThat
// import org.springframework.boot.test.context.SpringBootTest
// import org.springframework.beans.factory.annotation.Autowired


// @SpringBootTest
// class OldAdministratorsServiceTest {

//     // MockKでモック作成
//     // private lateinit var administratorsMapper: AdministratorsMapper

    
//     // @BeforeEach
//     // fun setup() {
//         //     administratorsMapper = mockk()
//         //     administratorsService = AdministratorsService(administratorsMapper)
//         // }
        
//         // @Test
//         // // `バッククオート`を使用することで文字列を関数名として使用可能
//         // fun `test findAll from administrators`() {
//             //     // MockKのデータ準備
//             //     val sample = listOf(
//                 //         AdministratorsRecord(1, "test1", "test1@example.com", "password1"),
//                 //         AdministratorsRecord(2, "test2", "test2@example.com", "password2")
//                 //     )
                
//                 //     // Mockを返すように設定
//                 //     every { administratorsMapper.selectMany(any()) } returns sample
                
//                 //     val result = administratorsService.findAll()
                
//                 //     assertEquals(2, result.size)
//                 //     assertEquals("test1", result[0].name)
//                 //     assertEquals("test2@example.com", result[1].mailAddress)
                
//                 //     verify(exactly = 1) { administratorsMapper.selectMany(any()) }
                
//                 // }
                
//     @Autowired
//     private lateinit var administratorsService: AdministratorsService
//     @Autowired
//     private lateinit var administratorCreateRequest: AdministratorCreateRequest
                
//     // pgAdminに接続してテスト
//     @Test
//     fun `administrators findAll test`() {
//         val result: List<Administrator> = administratorsService.findAll() 
//         // 空でないことの確認
//         assertThat(result).isNotEmpty()
        
//         // データ内容を取得
//         assertThat(result[0].id).isEqualTo(1)
//         assertThat(result[0].name).isEqualTo("管理者太郎")
//         assertThat(result[0].mailAddress).isEqualTo("admin@sample.com")
//     assertThat(result[0].password).isEqualTo("testtest")
//     }

//     @Test
//     fun `administrators findById test`() {
//         val result: Administrator = administratorsService.findById(1)

//         println(result)

//         assertThat(result.id).isEqualTo(1)
//         assertThat(result.name).isEqualTo("管理者太郎")
//         assertThat(result.mailAddress).isEqualTo("admin@sample.com")
//         assertThat(result.password).isEqualTo("testtest")
//     }

//     @Test
//     fun `administrators insert test`() {
//         val testAdministrator = Administrator(3, "Test", "test3@example.com", "testpassword123")
//         val result: Administrator = administratorsService.insert(administratorCreateRequest)

//         println(result)
//     }

//     @Test
//     fun `administrators delete test`() {
//         val delId: Int = 3
//         val result: Int = administratorsService.delete(delId)
//     }

// }
