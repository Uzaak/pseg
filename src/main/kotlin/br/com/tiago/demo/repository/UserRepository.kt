package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, Long>