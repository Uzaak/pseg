package br.com.tiago.demo.model

import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.exception.ProductNotFoundException
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.ProductRepository
import br.com.tiago.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired

data class Transaction (

        var id: Long?,

        var user: User,
        var creditCard: CreditCard,
        var product: Product

) {
    companion object {
        @Autowired
        private lateinit var userRepository: UserRepository

        @Autowired
        private lateinit var creditCardRepository: CreditCardRepository

        @Autowired
        private lateinit var productRepository: ProductRepository

        private fun findUserById(id: Long): UserEntity = userRepository.findById(id).orElseThrow { UserNotFoundException() }
        private fun findCreditCardById(id: Long): CreditCardEntity = creditCardRepository.findById(id).orElseThrow { CreditCardNotFoundException() }
        private fun findProductById(id: Long): ProductEntity = productRepository.findById(id).orElseThrow { ProductNotFoundException() }
    }

    constructor(entity: TransactionEntity) : this(entity.id, User(findUserById(entity.userId)), CreditCard(findCreditCardById(entity.creditCardId)), Product(findProductById(entity.productId)))
}