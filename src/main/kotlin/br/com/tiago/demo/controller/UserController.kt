package br.com.tiago.demo.controller

import br.com.tiago.demo.business.UserBusiness
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class UserController {

    @Autowired
    private lateinit var userBusiness: UserBusiness

    @RequestMapping(value = "/users/{userId}", method = [(RequestMethod.GET)])
    fun getUser(@PathVariable(value = "userId") userId: Long): ResponseEntity<User> {
        val user = userBusiness.getUser(userId)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @RequestMapping(value = "/users", method = [(RequestMethod.POST)])
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val persistedUser = userBusiness.createUser(user)
        return ResponseEntity(persistedUser, HttpStatus.OK)
    }

    @RequestMapping(value = "/users/{userId}/cards", method = [(RequestMethod.GET)])
    fun getUserCards(@PathVariable(value = "userId") userId: Long): ResponseEntity<List<CreditCard>> {
        val cards = userBusiness.getUserCards(userId)
        return ResponseEntity(cards, HttpStatus.OK)
    }

    @RequestMapping(value = "/users/{userId}/transactions", method = [(RequestMethod.GET)])
    fun getUserTransactions(@PathVariable(value = "userId") userId: Long): ResponseEntity<List<Transaction>> {
        val products = userBusiness.getUserTransactions(userId)
        return ResponseEntity(products, HttpStatus.OK)
    }

}