package br.com.tiago.demo.model

import br.com.tiago.demo.entity.ProductEntity

data class Product (

        var id: Long?,

        var description: String,
        var price: Double

) {
    constructor(entity: ProductEntity) : this(entity.id, entity.description, entity.price)
}

