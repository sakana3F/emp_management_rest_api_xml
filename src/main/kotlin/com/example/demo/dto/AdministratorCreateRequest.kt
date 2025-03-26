package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*


data class AdministratorCreateRequest(
    @field:NotBlank(message = "名前は必須")
    @field:JsonProperty("name", required = true)
    val name: String,
    
    @field:NotBlank(message = "メールアドレスは必須です")
    @field:Email(message = "メールアドレスの形式が不正です")
    @field:JsonProperty("mailAddress", required = true)
    val mailAddress: String,

    @field:NotBlank(message = "パスワードは必須です")
    @field:Size(min = 8, message = "パスワードは6文字以上で入力")
    // @field:Pattern(
    //     regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@._-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}$",
    //     message = "メールアドレスは英字と数字を含める必要があります"
    //     )
    @field:JsonProperty("password", required = true)
    val password: String
)
