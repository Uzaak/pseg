package br.com.tiago.demo.business

import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.exception.CardNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.repository.CreditCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreditCardBusiness {

    @Autowired
    private lateinit var creditCardRepository: CreditCardRepository

    fun getCard(cardId: Long): CreditCard {
        val entity = creditCardRepository.findById(cardId).orElseThrow { CardNotFoundException() }
        return CreditCard(entity)
    }

    fun createCard(card: CreditCard): CreditCard {
        val entity = CreditCardEntity(card)
        val persistedCard = creditCardRepository.save(entity)
        return CreditCard(persistedCard)
    }

}

