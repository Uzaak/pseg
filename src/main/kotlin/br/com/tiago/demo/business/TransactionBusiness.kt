package br.com.tiago.demo.business

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.exception.TransactionNotFoundException
import br.com.tiago.demo.exception.UserCardMismatchException
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionBusiness {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    fun getTransaction(transactionId: Long): Transaction {
        val entity = transactionRepository.findById(transactionId).orElseThrow { TransactionNotFoundException() }
        return Transaction(entity)
    }

    //TODO: Documentação
    fun makeTransaction(userId: Long, cardId: Long, productId: Long): Transaction {

        val userEntity = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val user = User(userEntity)

        if ( ! user.has(cardId) ) {
            throw UserCardMismatchException()
        }

        val entity = TransactionEntity(null, userId, cardId, productId)
        val persistedTransaction = transactionRepository.save(entity)
        return Transaction(persistedTransaction)

    }

}