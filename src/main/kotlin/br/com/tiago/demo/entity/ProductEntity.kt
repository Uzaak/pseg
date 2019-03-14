package br.com.tiago.demo.entity

import br.com.tiago.demo.model.Product
import javax.persistence.*

@Entity
@Table(name = "product")
data class ProductEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column
        var description: String,
        @Column
        var price: Double

) {
    constructor() : this(null, "description", 0.0)
    constructor(product: Product) : this(product.id, product.description, product.price)
}

