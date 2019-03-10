package br.com.tiago.demo.model

import br.com.tiago.demo.entity.UserEntity

data class User (

        var id: Long?,

        var name: String,
        var cpf: Long,
        var email: String,
        var password: String

) {
    constructor(entity: UserEntity) : this(entity.id, entity.name, entity.cpf, entity.email, entity.password)
}