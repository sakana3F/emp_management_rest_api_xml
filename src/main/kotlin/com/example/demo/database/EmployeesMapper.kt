/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database

import com.example.demo.database.EmployeesRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface EmployeesMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<EmployeesRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<EmployeesRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("EmployeesRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): EmployeesRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="EmployeesRecordResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="image", property="image", jdbcType=JdbcType.VARCHAR),
        Result(column="gender", property="gender", jdbcType=JdbcType.VARCHAR),
        Result(column="hire_date", property="hireDate", jdbcType=JdbcType.TIMESTAMP),
        Result(column="mail_address", property="mailAddress", jdbcType=JdbcType.VARCHAR),
        Result(column="zip_code", property="zipCode", jdbcType=JdbcType.VARCHAR),
        Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        Result(column="telephone", property="telephone", jdbcType=JdbcType.VARCHAR),
        Result(column="salary", property="salary", jdbcType=JdbcType.INTEGER),
        Result(column="characteristics", property="characteristics", jdbcType=JdbcType.VARCHAR),
        Result(column="dependents_count", property="dependentsCount", jdbcType=JdbcType.INTEGER)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<EmployeesRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}