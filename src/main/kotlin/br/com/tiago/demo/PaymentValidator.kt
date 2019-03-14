package br.com.tiago.demo

import br.com.tiago.demo.model.CreditCard
import org.springframework.stereotype.Component

@Component
class PaymentValidator {

    fun charge(creditCard: CreditCard): Boolean {

        val digits = creditCard.number.toString().toList().map { it.toInt() }

        if ( digits.sum() % 2 == 0 ) {
            // Charge card
            return true
        }

        // Card refused or something
        return false

    }

}