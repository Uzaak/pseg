package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.CreditCardEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CreditCardRepository: CrudRepository<CreditCardEntity,Long> {
    fun findAllByHolderId(holderId: Long): Optional<List<CreditCardEntity>>
}

