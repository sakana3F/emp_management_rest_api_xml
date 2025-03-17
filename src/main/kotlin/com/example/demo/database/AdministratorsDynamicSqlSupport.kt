/*
 * Auto-generated file. Created by MyBatis Generator
 */
package database

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object AdministratorsDynamicSqlSupport {
    object Administrators : SqlTable("administrators") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val name = column<String>("name", JDBCType.VARCHAR)

        val mailAddress = column<String>("mail_address", JDBCType.VARCHAR)

        val password = column<String>("password", JDBCType.VARCHAR)
    }
}