package br.com.tiago.demo.controller

import br.com.tiago.demo.business.UserBusiness
import br.com.tiago.demo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    private lateinit var userBusiness: UserBusiness

    @RequestMapping(value = "/user/{userId}", method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getUser(@PathVariable(value = "userId") userId: Long): ResponseEntity<User> {
        val user = userBusiness.getUser(userId)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @RequestMapping(value = "/user", method = [(RequestMethod.POST)], consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val persistedUser = userBusiness.createUser(user)
        return ResponseEntity(persistedUser, HttpStatus.OK)
    }

}