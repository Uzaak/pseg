package br.com.tiago.demo.model

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.exception.decrypted

data class Transaction (

        var id: Long?,

        var user: User,
        var creditCardNumber: Long,
        var creditCardHolder: String,
        var product: Product,

        var paid: Boolean

) {
    constructor(entity: TransactionEntity) : this(entity.id, User.placeholder(), entity.creditCardNumber.decrypted().toLong(), entity.creditCardHolder, Product.placeholder(), entity.paid)
}