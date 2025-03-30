package com.example.demo.service

import com.example.demo.entity.Administrator
import com.example.demo.mapper.AdministratorMapper
import com.example.demo.dto.AdministratorCreateRequest
import com.example.demo.dto.AdministratorUpdateRequest
import com.example.demo.exception.GlobalExceptionHandler
import com.example.demo.exception.BadRequestExeption
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException

@Service
class AdministratorsService @Autowired constructor(
    private val administratorMapper: AdministratorMapper
){

    /**
     * 管理者一覧を取得
     * @param administrators 登録済みの管理者情報取得
     * @administrators       管理者情報一覧
     * 
     *  */ 
    fun findAll(): List<Administrator> {
        val administrators: List<Administrator> = administratorMapper.findAll()

        return administrators
    }

    /**
     * ID検索から情報を取得
     * @param  id 管理者id
     * @return    idが紐づいた管理者情報
     * 
     *  */ 
    fun findById(id: Int): Administrator {
        return administratorMapper.findById(id)        
            ?: throw NoSuchElementException("指定IDの管理者が見つからない(id=$id)")

        // try{
        //     administrator = administratorMapper.findById(id)
        // } catch(e: NoSuchElementException) {
        //     throw NoSuchElementException("指定IDの管理者が見つからない")
        // }
        // return 
    }


    /**
     * 管理者機能の登録
     * @param request      idをまだ持ってない登録したい管理者情報
     * @param adminstrator id自動採番後のidを持った登録したい管理者情報
     * @return             自動採番のidが入ったAdminstrater
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

    /**
     * 管理者削除
     * @param id 削除したい管理者のid
     * @return   削除件数
     *  */ 
    fun delete(id: Int): Int {
        try{
            administratorMapper.delete(id)
        } catch(e: NoSuchElementException) {
            throw NoSuchElementException("サーバー内部エラー(削除対象がいない)")
        }     
        return id
    }

    /**
     * 管理者更新
     * @param request 更新したい管理者情報
     * @return 更新した管理者情報
     *  */ 
    fun update(id: Int, request: AdministratorUpdateRequest): Administrator {
        val administrator: Administrator = Administrator()

        // 更新情報を登録
        administrator.id = id
        administrator.name = request.name
        administrator.mailAddress = request.mailAddress
        administrator.password = request.password

        val effected: Int = administratorMapper.update(administrator)

        if(effected == 1) {
            val administrator: Administrator = administratorMapper.findById(id)
                ?: throw NoSuchElementException("指定IDの管理者が見つからない(id=$id)") //nullの場合NoSuchElementExceptionのエラーを出す
        } else {
            throw BadRequestExeption("指定IDの管理者が見つからない")  // idリクエストが間違ってるとき
        }
        return administrator
    }

}
