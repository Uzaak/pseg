package br.com.tiago.demo.controller

import br.com.tiago.demo.business.TransactionBusiness
import br.com.tiago.demo.controller.body.TransactionBody
import br.com.tiago.demo.exception.InvalidProductException
import br.com.tiago.demo.exception.InvalidUserException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class TransactionController {

    @Autowired
    private lateinit var transactionBusiness: TransactionBusiness

    @RequestMapping(value = "/transaction/{transactionId}", method = [(RequestMethod.GET)])
    fun getTransaction(@PathVariable(value = "transactionId") transactionId: Long): ResponseEntity<Transaction> {
        val transaction = transactionBusiness.getTransaction(transactionId)
        return ResponseEntity(transaction, HttpStatus.OK)
    }

    @RequestMapping(value = "/transaction", method = [(RequestMethod.POST)])
    fun makeTransaction(@RequestBody data: TransactionBody): ResponseEntity<Transaction?> {

        var transaction: Transaction

        if ( data.userId == null ) {
            throw InvalidUserException()
        } else if ( data.productId == null ) {
            throw InvalidProductException()
        }

        if ( data.creditCardId != null ) {
            transaction = transactionBusiness.makeTransaction(data.userId, data.creditCardId, data.productId)
        } else if ( data.creditCardNumber!= null && data.creditCardHolder != null && data.creditCardCvv != null && data.creditCardExpiration != null ) {
            val card = CreditCard(null, data.creditCardNumber, null, data.creditCardHolder, data.creditCardCvv, data.creditCardExpiration)
            transaction = transactionBusiness.makeTransaction(data.userId, card, data.productId)
        } else {
            return ResponseEntity<Transaction?>(null, HttpStatus.BAD_REQUEST)
        }

        return ResponseEntity(transaction, HttpStatus.OK)
    }

}