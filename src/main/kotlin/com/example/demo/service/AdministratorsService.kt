package com.example.demo.service

import org.springframework.stereotype.Service
import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import org.springframework.beans.factory.annotation.Autowired

@Service
class AdministratorsService @Autowired constructor(
    private val administratorMapper: AdministratorMapper
){

    // 管理者一覧を取得
    fun findAll(): List<Administrator> {
        val administrators: List<Administrator> = administratorMapper.findAll()
            ?: throw Exception("not found administrators list")
        return administrators
    }

    // ID検索から情報を取得
    // fun findById(id: Int): Administrator {
    //     val administrator: Administrator = administratorMapper.findById(id)
    //         ?: throw Exception("not found id")

    //     // if (administrator == null) {
    //     //     throw Exception
    //     // }
    //     // このif文は ?: throw Exception が代わりになる

    //     return administrator

    // }

}
