package br.com.tiago.demo.model

import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.exception.InvalidCreditCardException
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
        fun placeholder(): User {
            return User(null, "name", 0, "email", "password")
        }
    }

    constructor(entity: UserEntity) : this(entity.id, entity.name, entity.cpf, entity.email, entity.password)

    fun has(card: CreditCard): Boolean {

        if ( id == null ) {
            throw InvalidUserException()
        }

        if ( card.id == null ) {
            throw InvalidCreditCardException()
        }

        return card.holderId == id
    }
}