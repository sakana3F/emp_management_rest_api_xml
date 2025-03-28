package com.example.demo.controller

import com.example.demo.service.AdministratorsService
import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.dto.ErrorResponseDTO
import com.example.demo.dto.AdministratorCreateRequest
import com.example.demo.dto.AdministratorUpdateRequest
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.*
import org.springframework.dao.DuplicateKeyException
import org.springframework.validation.annotation.Validated
import jakarta.validation.Valid


@RestController  // SpringBootでREST APIを実装するためのアノテーション
// @Validated
@RequestMapping("/administrators") // エンドポイント
class AdministratorsController(
    @Autowired
    val administratorsService: AdministratorsService

) {
    @GetMapping("") // 一覧表示
    fun getAlladministrators(): ResponseEntity<List<Administrator>> {
        val administrators = administratorsService.findAll()
        return ResponseEntity.ok(administrators)  // 空の一覧も返す（エラーなし）
        
    }

    /**
     * 管理者情報をidで取得
     * @param id 取得したい管理者のid
     * @return Administrator 管理者情報
     */
    @GetMapping("/{id}") 
    fun getAdministratorById(@PathVariable id: Int): ResponseEntity<Administrator> {
            val administrator = administratorsService.findById(id)
            return ResponseEntity.ok(administrator)
    }

    /**
     * 管理者情報登録
     * @param admin 新規登録する管理者情報
     * @return 登録した管理者情報
     */
    @PostMapping("") // 新規登録
    fun signUpAdministrator(@RequestBody @Valid request: AdministratorCreateRequest): ResponseEntity<Administrator> {
        val admin = administratorsService.insert(request)
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(admin)
    }

    /**
     * 管理者削除
     * @param deleted 削除対象者のid
     */
    @DeleteMapping("/{id}") // 削除
    fun deleteAdministrator(@PathVariable id: Int): ResponseEntity<Void> {
        val effected = administratorsService.delete(id)
        return ResponseEntity.noContent().build() // 削除成功(204)
    }

    /**
     * 管理者情報の更新
     * @param  update 更新する管理者情報
     * @return        更新後の管理者情報
     */
    @PutMapping("/{id}") // 更新
    fun updateAdministrator(@PathVariable id: Int, @RequestBody @Valid request: AdministratorUpdateRequest): ResponseEntity<Administrator> {
        val admin = administratorsService.update(id, request)
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(admin)
    }
     

}
