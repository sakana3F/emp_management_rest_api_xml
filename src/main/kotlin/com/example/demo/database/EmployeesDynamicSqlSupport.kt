/*
 * Auto-generated file. Created by MyBatis Generator
 */
package database

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.SqlTable

object EmployeesDynamicSqlSupport {
    object Employees : SqlTable("employees") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val name = column<String>("name", JDBCType.VARCHAR)

        val image = column<String>("image", JDBCType.VARCHAR)

        val gender = column<String>("gender", JDBCType.VARCHAR)

        val hireDate = column<Date>("hire_date", JDBCType.TIMESTAMP)

        val mailAddress = column<String>("mail_address", JDBCType.VARCHAR)

        val zipCode = column<String>("zip_code", JDBCType.VARCHAR)

        val address = column<String>("address", JDBCType.VARCHAR)

        val telephone = column<String>("telephone", JDBCType.VARCHAR)

        val salary = column<Int>("salary", JDBCType.INTEGER)

        val characteristics = column<String>("characteristics", JDBCType.VARCHAR)

        val dependentsCount = column<Int>("dependents_count", JDBCType.INTEGER)
    }
}