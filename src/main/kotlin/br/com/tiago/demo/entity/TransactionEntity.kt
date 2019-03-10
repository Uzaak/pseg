package br.com.tiago.demo.entity

import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.User

data class TransactionEntity (

        var id: Long?,

        var userId: Long,
        var creditCardId: Long,
        var productId: Long
) {
    constructor(user: User, card: CreditCard, product: Product) : this(null, user.id!!, card.id!!, product.id!!)
}