package br.com.tiago.demo.entity

import br.com.tiago.demo.exception.encrypted
import br.com.tiago.demo.model.CreditCard
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "credit_card")
data class CreditCardEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column(name = "card_number")
        var number: String,
        @Column(name = "holder_id")
        var holderId: Long?,
        @Column
        var holder: String,
        @Column
        var cvv: Int?,
        @Column
        var expiration: Date?

) {
    constructor() : this(null, "0".encrypted(), null, "holder", null, null)
    constructor(card: CreditCard) : this(card.id, card.number.encrypted(), card.holderId, card.holder, card.cvv, card.expiration)
}