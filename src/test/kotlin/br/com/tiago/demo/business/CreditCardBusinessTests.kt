package br.com.tiago.demo.business

import br.com.tiago.demo.*
import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.repository.CreditCardRepository
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CreditCardBusinessTests {

    private lateinit var creditCardBusiness: CreditCardBusiness

    private var creditCardRepository: CreditCardRepository = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        creditCardBusiness = CreditCardBusiness(creditCardRepository)
    }

    @Test
    fun shouldCorrectlyReturnCard() {
        // GIVEN
        val card = CreditCard.testCard()
        whenever(creditCardRepository.findById(any())).thenReturn(Optional.of(CreditCardEntity.testEntity()))

        // WHEN
        val returnedCard = creditCardBusiness.getCard(1)

        // THEN
        assertThat(returnedCard, equalTo(card))
    }

    @Test(expected = CreditCardNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindCreditCard() {
        // GIVEN
        whenever(creditCardRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedCard = creditCardBusiness.getCard(1)

        // THEN
        assertNull(returnedCard)
        verify(creditCardRepository, times(1)).findById(1)
    }

    @Test
    fun shouldCreateCard() {
        // GIVEN
        val card = CreditCard.withNullId()
        val entity = card.toEntity()
        whenever(creditCardRepository.save<CreditCardEntity>(any())).thenReturn(entity)

        // WHEN
        val createdCard = creditCardBusiness.createCard(card)

        // THEN
        assertThat(createdCard, equalTo(card))
    }

    @Test
    fun shouldEditCard() {
        // GIVEN
        val card = CreditCard.testCard()
        val entity = card.toEntity()
        whenever(creditCardRepository.save<CreditCardEntity>(any())).thenReturn(entity)

        // WHEN
        val editedCard = creditCardBusiness.editCard(card)

        // THEN
        assertThat(editedCard, equalTo(card))
    }
}