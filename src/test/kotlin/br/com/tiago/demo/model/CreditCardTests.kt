package br.com.tiago.demo.model

import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.extension.decrypted
import br.com.tiago.demo.testCard
import br.com.tiago.demo.testEntity
import org.hamcrest.Matchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CreditCardTests {

    @Test
    fun shouldProperlyConvertToCreditCardEntity() {
        // GIVEN
        val card = CreditCard.testCard()
        val entity = CreditCardEntity.testEntity()

        // WHEN
        val convertedEntity = card.toEntity()

        // THEN
        assertThat(convertedEntity.id, equalTo(entity.id))
        assertThat(convertedEntity.holder, equalTo(entity.holder))
        assertThat(convertedEntity.number.decrypted(), equalTo(entity.number.decrypted()))
        assertThat(convertedEntity.cvv, equalTo(entity.cvv))
        assertThat(convertedEntity.expiration, equalTo(entity.expiration))
        assertThat(convertedEntity.holderId, equalTo(entity.holderId))
    }

    @Test
    fun shouldProperlyConvertFromCreditCardEntity() {
        // GIVEN
        val card = CreditCard.testCard()
        val entity = CreditCardEntity.testEntity()

        // WHEN
        val convertedModel = CreditCard.fromEntity(entity)

        // THEN
        assertThat(convertedModel, equalTo(card))
    }
}