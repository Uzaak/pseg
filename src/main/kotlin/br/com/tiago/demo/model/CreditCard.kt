package br.com.tiago.demo.model

import br.com.tiago.demo.entity.CreditCardEntity
import java.util.Date

data class CreditCard (

        var id: Long?,

        var number: Long,
        var holderId: Long?,
        var holder: String,
        var cvv: Int?,
        var expiration: Date?

) {
    constructor(entity: CreditCardEntity) : this(entity.id, entity.number, entity.holderId, entity.holder, entity.cvv, entity.expiration)
}

