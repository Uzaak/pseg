package br.com.tiago.demo.repository

import br.com.tiago.demo.entity.TransactionEntity
import org.springframework.data.repository.CrudRepository

interface TransactionRepository: CrudRepository<TransactionEntity, Long>