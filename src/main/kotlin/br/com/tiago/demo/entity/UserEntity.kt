package br.com.tiago.demo.entity

import br.com.tiago.demo.extension.encrypted
import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @Column
        var name: String,
        @Column
        var cpf: Long,
        @Column
        var email: String,
        @Column
        var password: String

) {
    companion object;
    constructor() : this(null, "name", 0, "email", "password".encrypted())
}