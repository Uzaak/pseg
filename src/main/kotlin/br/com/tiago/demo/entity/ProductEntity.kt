package br.com.tiago.demo.entity

import br.com.tiago.demo.model.Product

data class ProductEntity (

        var id: Long?,

        var description: String,
        var price: Double
) {
    constructor(product: Product) : this(product.id, product.description, product.price)
}

