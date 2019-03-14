package br.com.tiago.demo.entity

import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User

data class TransactionEntity (

        var id: Long?,

        var userId: Long,
        var creditCardNumber: Long,
        var creditCardHolder: String,
        var productId: Long,

        var paid: Boolean
) {
    constructor(user: User, card: CreditCard, product: Product) : this(null, user.id!!, card.number, card.holder, product.id!!, false)
    constructor(transaction: Transaction) : this(transaction.id, transaction.user.id!!, transaction.creditCardNumber, transaction.creditCardHolder, transaction.product.id!!, transaction.paid)
}