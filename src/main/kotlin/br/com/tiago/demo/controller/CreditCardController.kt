package br.com.tiago.demo.controller

import br.com.tiago.demo.business.CreditCardBusiness
import br.com.tiago.demo.exception.InvalidCreditCardException
import br.com.tiago.demo.exception.InvalidProductException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class CreditCardController (
    @Autowired private var creditCardBusiness: CreditCardBusiness
) {

    @RequestMapping(value = ["/cards/{cardId}"], method = [(RequestMethod.GET)])
    fun getCard(@PathVariable(value = "cardId") cardId: Long): ResponseEntity<CreditCard> {
        val card = creditCardBusiness.getCard(cardId)
        return ResponseEntity(card, HttpStatus.OK)
    }

    @RequestMapping(value = ["/cards"], method = [(RequestMethod.POST)])
    fun createCard(@RequestBody card: CreditCard): ResponseEntity<CreditCard> {
        val persistedCard = creditCardBusiness.createCard(card)
        return ResponseEntity(persistedCard, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/cards"], method = [(RequestMethod.PUT)])
    fun editCard(@RequestBody card: CreditCard): ResponseEntity<CreditCard> {
        if ( card.id == null ) {
            throw InvalidCreditCardException()
        }
        val persistedCard = creditCardBusiness.editCard(card)
        return ResponseEntity(persistedCard, HttpStatus.OK)
    }

}

