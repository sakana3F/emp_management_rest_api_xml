/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database



/*
    Administratorsテーブルと対応するデータクラス
    ・エンティティ or DTO として機能
    ・AdministratorsMapper を介して取得・更新・削除の対象

    [関係性]
    １.AdministratorsMapperがデータベースから取得してAdministratorsRecord にマッピング
    ２.AdministratorServiceでこのクラスを使ってサービスロジックを実装
 */
data class AdministratorsRecord(
    var id: Int? = null,
    var name: String? = null,
    var mailAddress: String? = null,
    var password: String? = null
)
