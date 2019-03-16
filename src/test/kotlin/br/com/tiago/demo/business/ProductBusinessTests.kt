package br.com.tiago.demo.business

import br.com.tiago.demo.*
import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.exception.ProductNotFoundException
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.repository.ProductRepository
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
class ProductBusinessTests {

    private lateinit var productBusiness: ProductBusiness

    private var productRepository: ProductRepository = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        productBusiness = ProductBusiness(productRepository)
    }

    @Test
    fun shouldCorrectlyReturnProduct() {
        // GIVEN
        val product = Product.testProduct()
        whenever(productRepository.findById(any())).thenReturn(Optional.of(ProductEntity.testEntity()))

        // WHEN
        val returnedProduct = productBusiness.getProduct(1)

        // THEN
        assertThat(returnedProduct, equalTo(product))
    }

    @Test(expected = ProductNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindProduct() {
        // GIVEN
        whenever(productRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedProduct = productBusiness.getProduct(1)

        // THEN
        assertNull(returnedProduct)
        verify(productRepository, times(1)).findById(1)
    }

    @Test
    fun shouldCreateProduct() {
        // GIVEN
        val product = Product.withNullId()
        val entity = product.toEntity()
        whenever(productRepository.save<ProductEntity>(any())).thenReturn(entity)

        // WHEN
        val createdProduct = productBusiness.createProduct(product)

        // THEN
        assertThat(createdProduct, equalTo(product))
    }

    @Test
    fun shouldEditProduct() {
        // GIVEN
        val product = Product.testProduct()
        val entity = product.toEntity()
        whenever(productRepository.save<ProductEntity>(any())).thenReturn(entity)

        // WHEN
        val editedProduct = productBusiness.editProduct(product)

        // THEN
        assertThat(editedProduct, equalTo(product))
    }

}