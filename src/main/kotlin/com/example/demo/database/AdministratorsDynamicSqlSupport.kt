/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

/*
    MyBatis Dynamic SQLを使う際に必要なadministratorsテーブルの情報を保持
    ・administrators テーブルのカラム情報を定義
    ・MyBatis Dynamic SQL を使う際に必要
    ・Administrators.id などの形でカラムを参照できる

    [関係性]
    １.AdministratorsMapperのselect()やupdate()で利用される
    ２.AdministratorsMapperExtensionsでこのクラスのカラム情報を利用

 */
object AdministratorsDynamicSqlSupport {
    object Administrators : SqlTable("administrators") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val name = column<String>("name", JDBCType.VARCHAR)

        val mailAddress = column<String>("mail_address", JDBCType.VARCHAR)

        val password = column<String>("password", JDBCType.VARCHAR)
    }
}
