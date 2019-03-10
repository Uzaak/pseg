package br.com.tiago.demo.controller

import br.com.tiago.demo.business.ProductBusiness
import br.com.tiago.demo.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductController {

    @Autowired
    private lateinit var productBusiness: ProductBusiness

    @RequestMapping(value = "/product/{productId}", method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getProduct(@PathVariable(value = "productId") productId: Long): ResponseEntity<Product> {
        val product = productBusiness.getProduct(productId)
        return ResponseEntity(product, HttpStatus.OK)
    }

    @RequestMapping(value = "/product", method = [(RequestMethod.POST)], consumes = [(MediaType.APPLICATION_JSON_VALUE)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createProduct(@RequestBody product: Product): ResponseEntity<Product> {
        val persistedProduct = productBusiness.createProduct(product)
        return ResponseEntity(persistedProduct, HttpStatus.OK)
    }

}
