package br.com.tiago.demo.business

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.exception.*
import br.com.tiago.demo.extension.encrypted
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.ProductRepository
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import br.com.tiago.demo.validator.PaymentValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionBusiness (
    @Autowired private var transactionRepository: TransactionRepository,
    @Autowired private var userRepository: UserRepository,
    @Autowired private var creditCardRepository: CreditCardRepository,
    @Autowired private var productRepository: ProductRepository,
    @Autowired private var paymentValidator: PaymentValidator
) {

    fun getTransaction(transactionId: Long): Transaction {
        val entity = transactionRepository.findById(transactionId).orElseThrow { TransactionNotFoundException() }
        return transactionFrom(entity)
    }

    fun makeTransaction(userId: Long, cardId: Long, productId: Long): Transaction {

        val userEntity = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        val user = User.fromEntity(userEntity)

        val cardEntity = creditCardRepository.findById(cardId).orElseThrow { CreditCardNotFoundException() }
        val card = CreditCard.fromEntity(cardEntity)

        if ( ! productRepository.existsById(productId) ) {
            throw ProductNotFoundException()
        }

        if ( ! user.has(card) ) {
            throw UserCardMismatchException()
        }

        val entity = TransactionEntity(null, userId, cardEntity.number, cardEntity.holder, productId, false)

        entity.paid = paymentValidator.charge(card)

        val persistedTransaction = transactionRepository.save(entity)
        return transactionFrom(persistedTransaction)
    }

    fun makeTransaction(userId: Long, creditCard: CreditCard, productId: Long): Transaction {

        if ( ! productRepository.existsById(productId) ) {
            throw ProductNotFoundException()
        }

        val entity = TransactionEntity(null, userId, creditCard.number.encrypted(), creditCard.holder, productId, false)

        entity.paid = paymentValidator.charge(creditCard)
        if ( entity.paid ) {
            creditCard.holderId = userId
            val creditCardEntity = creditCard.toEntity()
            creditCardRepository.save(creditCardEntity)
        }

        val persistedTransaction = transactionRepository.save(entity)
        return transactionFrom(persistedTransaction)
    }

    private fun transactionFrom(entity: TransactionEntity): Transaction {
        var transaction = Transaction.fromEntity(entity)

        val userEntity = userRepository.findById(entity.userId).orElseThrow { UserNotFoundException() }
        transaction.user = User.fromEntity(userEntity)

        val productEntity = productRepository.findById(entity.productId).orElseThrow { ProductNotFoundException() }
        transaction.product = Product.fromEntity(productEntity)

        return transaction
    }

}