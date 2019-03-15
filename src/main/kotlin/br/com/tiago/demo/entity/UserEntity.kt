package br.com.tiago.demo.entity

import br.com.tiago.demo.exception.encrypted
import br.com.tiago.demo.model.User
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
    constructor() : this(null, "name", 0, "email", "password".encrypted())
    constructor(user: User) : this(user.id, user.name, user.cpf, user.email, user.password.encrypted())
}