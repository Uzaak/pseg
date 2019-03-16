package br.com.tiago.demo.model

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.extension.decrypted
import br.com.tiago.demo.testEntity
import br.com.tiago.demo.testProduct
import br.com.tiago.demo.testTransaction
import br.com.tiago.demo.testUser
import org.hamcrest.Matchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TransactionTests {

    @Test
    fun shouldProperlyConvertToEntity() {
        // GIVEN
        val transaction = Transaction.testTransaction()
        val entity = TransactionEntity.testEntity()

        // WHEN
        val convertedEntity = transaction.toEntity()

        // THEN
        assertThat(convertedEntity.id, equalTo(entity.id))
        assertThat(convertedEntity.userId, equalTo(entity.userId))
        assertThat(convertedEntity.creditCardNumber.decrypted(), equalTo(entity.creditCardNumber.decrypted()))
        assertThat(convertedEntity.creditCardHolder, equalTo(entity.creditCardHolder))
        assertThat(convertedEntity.productId, equalTo(entity.productId))
        assertThat(convertedEntity.paid, equalTo(entity.paid))
    }

    @Test
    fun shouldProperlyConvertFromEntity() {
        // GIVEN
        val transaction = Transaction.testTransaction()
        val entity = TransactionEntity.testEntity()

        // WHEN
        val convertedModel = Transaction.fromEntity(entity)
        convertedModel.user = User.testUser()
        convertedModel.product = Product.testProduct()

        // THEN
        assertThat(convertedModel, equalTo(transaction))
    }

}