package br.com.tiago.demo.entity

import br.com.tiago.demo.extension.encrypted
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import javax.persistence.*

@Entity
@Table(name = "transaction")
data class TransactionEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(name = "user_id")
        var userId: Long,
        @Column(name = "credit_card_number")
        var creditCardNumber: String,
        @Column(name = "credit_card_holder")
        var creditCardHolder: String,
        @Column(name = "product_id")
        var productId: Long,

        @Column
        var paid: Boolean

) {
    constructor() : this(null, 0, "0".encrypted(), "holder", 0, false)
    constructor(user: User, card: CreditCard, product: Product) : this(null, user.id!!, card.number.encrypted(), card.holder, product.id!!, false)
}