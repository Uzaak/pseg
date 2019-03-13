package br.com.tiago.demo.model

import br.com.tiago.demo.entity.CreditCardEntity
import java.util.Date

data class CreditCard (

        var id: Long?,

        var number: String,
        var holderId: Long,
        var cvv: Int,
        var expiration: Date

) {
    constructor(entity: CreditCardEntity) : this(entity.id, entity.number, entity.holderId, entity.cvv, entity.expiration)
}

