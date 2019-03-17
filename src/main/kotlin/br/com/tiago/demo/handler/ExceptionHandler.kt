package br.com.tiago.demo.handler

import br.com.tiago.demo.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.xml.ws.Response

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [
        InvalidUserException::class,
        InvalidCreditCardException::class,
        InvalidProductException::class,
        UserCardMismatchException::class
    ])
    fun handleBadRequestException() = ResponseEntity.status(HttpStatus.BAD_REQUEST).build<Any>()

    @ExceptionHandler(value = [
        CreditCardNotFoundException::class,
        ProductNotFoundException::class,
        UserNotFoundException::class,
        TransactionNotFoundException::class
    ])
    fun handleNotFoundException() = ResponseEntity.status(HttpStatus.NOT_FOUND).build<Any>()

}