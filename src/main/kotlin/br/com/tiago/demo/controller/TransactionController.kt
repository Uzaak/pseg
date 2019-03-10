package br.com.tiago.demo.controller

import br.com.tiago.demo.business.TransactionBusiness
import br.com.tiago.demo.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController {

    @Autowired
    private lateinit var transactionBusiness: TransactionBusiness

    @RequestMapping(value = "/transaction/{transactionId}", method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getTransaction(@PathVariable(value = "transactionId") transactionId: Long): ResponseEntity<Transaction> {
        val transaction = transactionBusiness.getTransaction(transactionId)
        return ResponseEntity(transaction, HttpStatus.OK)
    }

}