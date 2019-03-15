package br.com.tiago.demo.business

import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.*
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.ProductRepository
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserBusiness (
    @Autowired private var userRepository: UserRepository,
    @Autowired private var creditCardRepository: CreditCardRepository,
    @Autowired private var productRepository: ProductRepository,
    @Autowired private var transactionRepository: TransactionRepository
) {

    fun getUser(userId: Long): User {
        val entity = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        return User.fromEntity(entity)
    }

    fun createUser(user: User): User {
        val entity = user.toEntity()
        val persistedUser = userRepository.save(entity)
        return User.fromEntity(persistedUser)
    }

    fun getUserCards(userId: Long): List<CreditCard> {
        val entityList = creditCardRepository.findAllByHolderId(userId).orElseThrow { CreditCardNotFoundException() }
        return entityList.map { CreditCard.fromEntity(it) }
    }

    fun getUserTransactions(userId: Long): List<Transaction> {
        val entityList = transactionRepository.findAllByUserId(userId).orElseThrow { TransactionNotFoundException() }
        return entityList.map { transactionFrom(it) }
    }

    private fun transactionFrom(entity: TransactionEntity): Transaction {
        var transaction = Transaction.fromEntity(entity)

        val userEntity = userRepository.findById(entity.userId).orElseThrow { UserNotFoundException() }
        transaction.user = User.fromEntity(userEntity)

        val productEntity = productRepository.findById(entity.productId).orElseThrow { ProductNotFoundException() }
        transaction.product = Product.fromEntity(productEntity)

        return transaction
    }

    fun getUserTransactions(userId: Long, paid: Boolean): List<Transaction> {
        val entityList = transactionRepository.findAllByUserIdAndPaid(userId, paid).orElseThrow { TransactionNotFoundException() }
        return entityList.map { transactionFrom(it) }
    }

}