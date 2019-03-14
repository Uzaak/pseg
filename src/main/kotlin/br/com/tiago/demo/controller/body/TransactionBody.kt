package br.com.tiago.demo.controller.body

import java.util.*

data class TransactionBody (

        val userId: Long?,

        val creditCardId: Long?,

        var creditCardNumber: Long?,
        var creditCardHolder: String?,
        var creditCardCvv: Int?,
        var creditCardExpiration: Date?,

        val productId: Long?

)