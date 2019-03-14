package br.com.tiago.demo.business

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.exception.TransactionNotFoundException
import br.com.tiago.demo.exception.UserCardMismatchException
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import br.com.tiago.demo.validator.PaymentValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionBusiness {

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var creditCardRepository: CreditCardRepository

    @Autowired
    private lateinit var paymentValidator: PaymentValidator

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

        val cardEntity = creditCardRepository.findById(cardId).orElseThrow { CreditCardNotFoundException() }
        val card = CreditCard(cardEntity)

        val entity = TransactionEntity(null, userId, cardEntity.number, cardEntity.holder, productId, false)

        entity.paid = paymentValidator.charge(card)

        val persistedTransaction = transactionRepository.save(entity)
        return Transaction(persistedTransaction)

    }

    fun makeTransaction(userId: Long, creditCard: CreditCard, productId: Long): Transaction {

        val entity = TransactionEntity(null, userId, creditCard.number, creditCard.holder, productId, false)

        entity.paid = paymentValidator.charge(creditCard)

        val persistedTransaction = transactionRepository.save(entity)
        return Transaction(persistedTransaction)
    }

}