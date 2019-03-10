package br.com.tiago.demo.business

import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.exception.ProductNotFoundException
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductBusiness {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun getProduct(productId: Long): Product {
        val entity = productRepository.findById(productId).orElseThrow { ProductNotFoundException() }
        return Product(entity)
    }

    fun createProduct(product: Product): Product {
        val entity = ProductEntity(product)
        val persistedProduct = productRepository.save(entity)
        return Product(persistedProduct)
    }

}

