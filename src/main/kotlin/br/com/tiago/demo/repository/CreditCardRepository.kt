package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.CreditCardEntity
import org.springframework.data.repository.CrudRepository

interface CreditCardRepository: CrudRepository<CreditCardEntity,Long>

