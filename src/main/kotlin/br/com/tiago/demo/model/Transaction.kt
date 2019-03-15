package br.com.tiago.demo.model

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.extension.decryptedLong
import br.com.tiago.demo.extension.encrypted

data class Transaction (

        var id: Long?,

        var user: User,
        var creditCardNumber: Long,
        var creditCardHolder: String,
        var product: Product,

        var paid: Boolean

) {
    companion object {
        fun fromEntity(entity: TransactionEntity): Transaction {
            return Transaction(entity.id, User.placeholder(), entity.creditCardNumber.decryptedLong(), entity.creditCardHolder, Product.placeholder(), entity.paid)
        }
    }

    fun toEntity(): TransactionEntity {
        return TransactionEntity(id, user.id!!, creditCardNumber.encrypted(), creditCardHolder, product.id!!, paid)
    }
}