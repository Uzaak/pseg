package br.com.tiago.demo.model

import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.extension.decryptedLong
import br.com.tiago.demo.extension.encrypted
import java.util.Date

data class CreditCard (

        var id: Long?,

        var number: Long,
        var holderId: Long?,
        var holder: String,
        var cvv: Int?,
        var expiration: Date?

) {
    companion object {
        fun fromEntity(entity: CreditCardEntity): CreditCard {
            return CreditCard(entity.id, entity.number.decryptedLong(), entity.holderId, entity.holder, entity.cvv, entity.expiration)
        }
    }

    fun toEntity(): CreditCardEntity {
        return CreditCardEntity(id, number.encrypted(), holderId, holder, cvv, expiration)
    }
}

