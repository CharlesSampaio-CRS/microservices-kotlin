package com.kt.microservices.exception.handler

import com.kt.microservices.exception.ExceptionResponse
import com.kt.microservices.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomizaResponseHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(java.lang.Exception::class)
    fun handleAllException(ex: java.lang.Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(Date(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(ex: java.lang.Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(Date(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND)
    }

}