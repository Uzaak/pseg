package br.com.tiago.demo.entity

import br.com.tiago.demo.extension.encrypted
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
    companion object;
    constructor() : this(null, "0".encrypted(), null, "holder", null, null)
}