package com.example.demo.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.dao.DuplicateKeyException


import com.example.demo.dto.ErrorResponseDTO


/**
 * コントローラーの例外ハンドリング
 */
@ControllerAdvice
class GlobalExceptionHandler { 

    /**
     * 400エラー
     */
    @ExceptionHandler(
        DuplicateKeyException::class // メールアドレスの重複確認
        // MethodArgumentNotValidException::class // バリデーションに引っかかったとき
    )
    fun handleBadRequest(): ResponseEntity<ErrorResponseDTO> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDTO(400, "Bad Request"))
    
    /**
     * 404エラー
     */
    @ExceptionHandler(
        NoSuchElementException::class // 指定IDの管理者が見つからない
    )
    fun handleNotFound(): ResponseEntity<ErrorResponseDTO> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDTO(404, "Not Found"))

    /**
     * 500エラー
     */
    @ExceptionHandler(Exception::class)
    fun handleOther(): ResponseEntity<ErrorResponseDTO> =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponseDTO(500, "Internal Server Error"))

}
