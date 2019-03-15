package br.com.tiago.demo.model

import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.InvalidCreditCardException
import br.com.tiago.demo.exception.InvalidUserException
import br.com.tiago.demo.extension.decrypted
import br.com.tiago.demo.extension.encrypted

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

        fun fromEntity(entity: UserEntity): User {
            return User(entity.id, entity.name, entity.cpf, entity.email, entity.password.decrypted())
        }
    }

    fun toEntity(): UserEntity {
        return UserEntity(id, name, cpf, email, password.encrypted())
    }

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