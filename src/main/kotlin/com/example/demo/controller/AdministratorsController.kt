package com.example.demo.controller

import com.example.demo.service.AdministratorsService
import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.dto.ErrorResponseDTO
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.dao.DuplicateKeyException



@RestController  // SpringBootでREST APIを実装するためのアノテーション
@RequestMapping("/administrators") // エンドポイント
class AdministratorsController(
    @Autowired
    val administratorsService: AdministratorsService
    // @Autowired
    // val errorResponseDTO: ErrorResponseDTO = new Ad

) {
    @GetMapping("") // 一覧表示
    fun getAlladministrators(): ResponseEntity<List<Administrator>> {
        val administrators = administratorsService.findAll()
        return if(administrators.isNotEmpty()) {
            ResponseEntity.ok(administrators)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    /**
     * 管理者情報をidで取得
     * @param id 取得したい管理者のid
     * @return Administrator 管理者情報
     */

    @GetMapping("/{id}") 
    fun getAdministratorById(@PathVariable id: Int): ResponseEntity<Administrator> {
        try{
            val administrator = administratorsService.findById(id)
            return ResponseEntity.ok(administrator)
        } catch(e: Exception) {
            println(e.message)
            return ResponseEntity.notFound().build()
        }
    }

    /**
     * 管理者情報登録
     * @param admin 新規登録する管理者情報
     * @return 登録した管理者情報
     */
    @PostMapping // 新規登録
    fun createAdministrator(@RequestBody admin: Administrator): ResponseEntity<Administrator> {
        val admin = administratorsService.insert(admin)
        return ResponseEntity.ok(admin)
    }

    @DeleteMapping("/{id}") // 削除
    fun deleteAdministrator(@PathVariable id: Int) {
        administratorsService.deleteAdministratorById(id)
    }

    }
