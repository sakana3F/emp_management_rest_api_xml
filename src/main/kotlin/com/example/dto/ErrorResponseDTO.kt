package com.example.demo.dto

/**
 * ステータスコードとメッセージの指定
 */
data class ErrorResponseDTO(
    val status: Int,
    val message: String
)
