package br.com.tiago.demo.controller

import br.com.tiago.demo.business.CreditCardBusiness
import br.com.tiago.demo.model.CreditCard
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CreditCardController {

    @Autowired
    private lateinit var creditCardBusiness: CreditCardBusiness

    @RequestMapping(value = "/card/{cardId}", method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getCard(@PathVariable(value = "cardId") cardId: Long): ResponseEntity<CreditCard> {
        val card = creditCardBusiness.getCard(cardId)
        return ResponseEntity(card, HttpStatus.OK)
    }

    @RequestMapping(value = "/card", method = [(RequestMethod.POST)], consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createCard(@RequestBody card: CreditCard): ResponseEntity<CreditCard> {
        val persistedCard = creditCardBusiness.createCard(card)
        return ResponseEntity(persistedCard, HttpStatus.OK)
    }

}
