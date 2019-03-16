package br.com.tiago.demo.model

import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.testEntity
import br.com.tiago.demo.testProduct
import org.hamcrest.Matchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ProductTests {

    @Test
    fun shouldProperlyConvertToProductEntity() {
        // GIVEN
        val product = Product.testProduct()
        val entity = ProductEntity.testEntity()

        // WHEN
        val convertedEntity = product.toEntity()

        // THEN
        assertThat(convertedEntity, equalTo(entity))
    }

    @Test
    fun shouldProperlyConvertFromProductEntity() {
        // GIVEN
        val product = Product.testProduct()
        val entity = ProductEntity.testEntity()

        // WHEN
        val convertedModel = Product.fromEntity(entity)

        // THEN
        assertThat(convertedModel, equalTo(product))
    }

}