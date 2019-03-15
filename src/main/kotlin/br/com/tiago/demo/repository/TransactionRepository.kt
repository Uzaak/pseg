package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.TransactionEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TransactionRepository: CrudRepository<TransactionEntity, Long> {
    fun findAllByUserId(userId: Long): Optional<List<TransactionEntity>>
    fun findAllByUserIdAndPaid(userId: Long, paid: Boolean): Optional<List<TransactionEntity>>
}