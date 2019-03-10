package br.com.tiago.demo.business

import br.com.tiago.demo.exception.TransactionNotFoundException
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionBusiness {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    fun getTransaction(transactionId: Long): Transaction {
        val entity = transactionRepository.findById(transactionId).orElseThrow { TransactionNotFoundException() }
        return Transaction(entity)
    }

}