package br.com.tiago.demo.model

import br.com.tiago.demo.entity.ProductEntity

data class Product (

        var id: Long?,

        var description: String,
        var price: Double

) {
    companion object {
        fun placeholder(): Product {
            return Product(null, "description", 0.0)
        }
    }

    constructor(entity: ProductEntity) : this(entity.id, entity.description, entity.price)
}

