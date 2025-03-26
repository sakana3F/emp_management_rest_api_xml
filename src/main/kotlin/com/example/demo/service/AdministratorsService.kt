package com.example.demo.service

import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.dto.AdministratorCreateRequest
import com.example.demo.exception.GlobalExceptionHandler
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException

@Service
class AdministratorsService @Autowired constructor(
    private val administratorMapper: AdministratorMapper
){

    // 管理者一覧を取得
    fun findAll(): List<Administrator> {
        val administrators: List<Administrator> = administratorMapper.findAll()

        return administrators
    }

    // ID検索から情報を取得
    fun findById(id: Int): Administrator {
        val administrator: Administrator
        
        try{
            administrator = administratorMapper.findById(id)
        } catch(e: NoSuchElementException) {
            throw NoSuchElementException("指定IDの管理者が見つからない")
        }
        return administrator
        
        // if (administrator == null) {
        //     throw Exception
        // }
        // このif文は ?: throw Exception が代わりになる
    }


    /**
     * 管理者機能の登録
     * 
     * @param request idのない登録したい管理者丈夫尾
     * @param adminstrator 登録したい管理者情報
     * @return 自動採番のidが入ったAdminstrater
     */
    fun insert(request: AdministratorCreateRequest): Administrator {
        val administrator: Administrator = Administrator()

        administrator.name = request.name
        administrator.mailAddress = request.mailAddress
        administrator.password = request.password

        try {
            administratorMapper.insert(administrator)
        } catch (e: DuplicateKeyException) {
            throw DuplicateKeyException("メールアドレスの重複を検知")
        }
        return administrator
    }


    // 管理者削除
    fun delete(id: Int): Int {
        try{
            administratorMapper.delete(id)
        } catch(e: Exception) {
            throw Exception("サーバー内部エラー(削除対象がいない)")
        }     
        return id
    }

}
