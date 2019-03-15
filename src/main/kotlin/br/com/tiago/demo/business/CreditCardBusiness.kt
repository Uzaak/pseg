package br.com.tiago.demo.business

import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.repository.CreditCardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreditCardBusiness(
    @Autowired private var creditCardRepository: CreditCardRepository
) {
    fun getCard(cardId: Long): CreditCard {
        val entity = creditCardRepository.findById(cardId).orElseThrow { CreditCardNotFoundException() }
        return CreditCard.fromEntity(entity)
    }

    fun createCard(card: CreditCard): CreditCard {
        //card.isValid()
        val entity = card.toEntity()
        val persistedCard = creditCardRepository.save(entity)
        return CreditCard.fromEntity(persistedCard)
    }

}

