/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database

import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.id
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.mailAddress
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.name
import com.example.demo.database.AdministratorsDynamicSqlSupport.Administrators.password
import com.example.demo.database.AdministratorsRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun AdministratorsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Administrators, completer)

fun AdministratorsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Administrators, completer)

fun AdministratorsMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun AdministratorsMapper.insert(record: AdministratorsRecord) =
    insert(this::insert, record, Administrators) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(mailAddress).toProperty("mailAddress")
        map(password).toProperty("password")
    }

fun AdministratorsMapper.insertMultiple(records: Collection<AdministratorsRecord>) =
    insertMultiple(this::insertMultiple, records, Administrators) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(mailAddress).toProperty("mailAddress")
        map(password).toProperty("password")
    }

fun AdministratorsMapper.insertMultiple(vararg records: AdministratorsRecord) =
    insertMultiple(records.toList())

fun AdministratorsMapper.insertSelective(record: AdministratorsRecord) =
    insert(this::insert, record, Administrators) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(name).toPropertyWhenPresent("name", record::name)
        map(mailAddress).toPropertyWhenPresent("mailAddress", record::mailAddress)
        map(password).toPropertyWhenPresent("password", record::password)
    }

private val columnList = listOf(id, name, mailAddress, password)

fun AdministratorsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Administrators, completer)

fun AdministratorsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Administrators, completer)

fun AdministratorsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Administrators, completer)

fun AdministratorsMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun AdministratorsMapper.update(completer: UpdateCompleter) =
    update(this::update, Administrators, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: AdministratorsRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
        set(mailAddress).equalTo(record::mailAddress)
        set(password).equalTo(record::password)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: AdministratorsRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
        set(mailAddress).equalToWhenPresent(record::mailAddress)
        set(password).equalToWhenPresent(record::password)
    }

fun AdministratorsMapper.updateByPrimaryKey(record: AdministratorsRecord) =
    update {
        set(name).equalTo(record::name)
        set(mailAddress).equalTo(record::mailAddress)
        set(password).equalTo(record::password)
        where(id, isEqualTo(record::id))
    }

fun AdministratorsMapper.updateByPrimaryKeySelective(record: AdministratorsRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        set(mailAddress).equalToWhenPresent(record::mailAddress)
        set(password).equalToWhenPresent(record::password)
        where(id, isEqualTo(record::id))
    }