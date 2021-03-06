package br.com.tiago.demo.entity

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
    companion object;
    constructor() : this(null, "description", 0.0)
}

