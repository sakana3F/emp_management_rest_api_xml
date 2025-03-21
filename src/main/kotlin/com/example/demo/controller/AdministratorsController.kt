package com.example.demo.controller

import com.example.demo.service.AdministratorsService
import com.example.demo.entity.Administrator
// import com.example.demo.mapper.AdministratorMapper
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController  // SpringBootでREST APIを実装するためのアノテーション
@RequestMapping("/administrators") // エンドポイント
class AdministratorsController(
    @Autowired
    val administratorsService: AdministratorsService
) {
    @GetMapping("")
    fun getAlladministrators(): ResponseEntity<List<Administrator>> {
        val administrators = administratorsService.findAll()
        return if(administrators.isNotEmpty()) {
            ResponseEntity.ok(administrators)
        } else {
            ResponseEntity.noContent().build()
        }
    }

    // @GetMapping("/{id}")
    // fun getAdministratorById(@PathVariable id: Int): ResponseEntity<AdministratorsRecord> {
        
    //     try{
    //         val administrator = administratorsService.findById(id)
    //         return ResponseEntity.ok(administrator)
    //     } catch(e: Exception) {
    //         return ResponseEntity.notFound().build()
    //     }
    // }

    }
