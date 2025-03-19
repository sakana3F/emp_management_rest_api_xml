package com.example.demo.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.select.render.*
import org.mybatis.dynamic.sql.render.RenderingStrategies

import com.example.demo.database.AdministratorsMapper
import com.example.demo.database.AdministratorsRecord
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.id
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.name
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.mailAddress
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.password


@Suppress("SpringJavaInjectionPointsAutowiringInspection") // Springの依存性注入の警告を抑制

@Service
class AdministratorsService @Autowired constructor(
    private val administratorsMapper: AdministratorsMapper
){

    // 管理者一覧を取得
    fun findAll(): List<AdministratorsRecord> {
        val selectStatement: SelectStatementProvider = select(id, name, mailAddress, password)
            .from(Administrators)
            .build()
            .render(RenderingStrategies.MYBATIS3)

        return administratorsMapper.selectMany(selectStatement)
    }

}
