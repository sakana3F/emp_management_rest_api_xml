// package com.example.demo

// import org.junit.jupiter.api.Test
// import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.boot.test.context.SpringBootTest
// import org.springframework.jdbc.core.jdbcTemplate
// import org.junit.jupiter.api.Assertions.assertTrue

// @SpringBootTest
// class AdministratorTest {

//     @Autowired
//     lateinit var jdbcTemplate: jdbcTemplate

//     @Test
//     fun testConnection() {
//         var result = jdbcTemplate.queryForobject("SELECT 1 FROM administrators", Int::class.java)

//         assertTrue(result == 1, "Database connection test failed!")

        

//     }

// }
