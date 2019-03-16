package br.com.tiago.demo.validator

import br.com.tiago.demo.evenCard
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.oddCard
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PaymentValidatorTests {

    private lateinit var paymentValidator: PaymentValidator

    @Before
    fun init() {
//        MockitoAnnotations.initMocks(this)
        paymentValidator = PaymentValidator()
    }

    @Test
    fun shouldValidateCardsWithEvenNumbers() {
        // GIVEN
        val card = CreditCard.evenCard()

        // WHEN
        val result = paymentValidator.charge(card)

        // THEN
        assertTrue(result)
    }

    @Test
    fun shouldNotValidateCardsWithOddNumbers() {
        // GIVEN
        val card = CreditCard.oddCard()

        // WHEN
        val result = paymentValidator.charge(card)

        // THEN
        assertFalse(result)
    }

}