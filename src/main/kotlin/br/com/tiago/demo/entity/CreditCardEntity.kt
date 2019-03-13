package br.com.tiago.demo.entity

import br.com.tiago.demo.model.CreditCard
import java.util.*

data class CreditCardEntity (

        var id: Long?,

        var number: String,
        var holderId: Long,
        var cvv: Int,
        var expiration: Date

) {
    constructor(card: CreditCard) : this(card.id, card.number, card.holderId, card.cvv, card.expiration)
}