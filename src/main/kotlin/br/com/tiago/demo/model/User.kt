package br.com.tiago.demo.model

import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.exception.InvalidUserException
import br.com.tiago.demo.repository.CreditCardRepository
import org.springframework.beans.factory.annotation.Autowired

data class User (

        var id: Long?,

        var name: String,
        var cpf: Long,
        var email: String,
        var password: String

) {
    companion object {
        @Autowired
        private lateinit var creditCardRepository: CreditCardRepository
    }

    constructor(entity: UserEntity) : this(entity.id, entity.name, entity.cpf, entity.email, entity.password)

    fun has(cardId: Long): Boolean {

        if ( id == null ) {
            throw InvalidUserException()
        }

        val cardEntity = creditCardRepository.findById(cardId).orElseThrow { CreditCardNotFoundException() }
        val card = CreditCard(cardEntity)
        return card.holderId == id
    }
}