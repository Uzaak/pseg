package br.com.tiago.demo.entity

import br.com.tiago.demo.model.User

data class UserEntity (

        var id: Long?,

        var name: String,
        var cpf: Long,
        var email: String,
        var password: String
) {
    constructor(user: User) : this(user.id, user.name, user.cpf, user.email, user.password)
}