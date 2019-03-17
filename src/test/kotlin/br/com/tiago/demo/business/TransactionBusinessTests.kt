package br.com.tiago.demo.business

import br.com.tiago.demo.*
import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.ProductNotFoundException
import br.com.tiago.demo.exception.TransactionNotFoundException
import br.com.tiago.demo.exception.UserCardMismatchException
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.ProductRepository
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import br.com.tiago.demo.validator.PaymentValidator
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.*

class TransactionBusinessTests {

    private lateinit var transactionBusiness: TransactionBusiness

    private var transactionRepository: TransactionRepository = mock()
    private var userRepository: UserRepository = mock()
    private var creditCardRepository: CreditCardRepository = mock()
    private var productRepository: ProductRepository = mock()
    private var paymentValidator: PaymentValidator = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        transactionBusiness = TransactionBusiness(transactionRepository, userRepository, creditCardRepository, productRepository, paymentValidator)
    }

    @Test
    fun shouldCorrectlyReturnTransaction() {
        // GIVEN
        val transaction = Transaction.testTransaction()
        val userEntity = UserEntity.testEntity()
        val productEntity = ProductEntity.testEntity()
        whenever(transactionRepository.findById(any())).thenReturn(Optional.of(TransactionEntity.testEntity()))
        whenever(userRepository.findById(any())).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(any())).thenReturn(Optional.of(productEntity))

        // WHEN
        val returnedTransaction = transactionBusiness.getTransaction(1)

        // THEN
        assertThat(returnedTransaction, equalTo(transaction))
    }

    @Test(expected = TransactionNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindTransaction() {
        // GIVEN
        whenever(transactionRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedTransactions = transactionBusiness.getTransaction(1)

        // THEN
        assertNull(returnedTransactions)
        verify(transactionRepository, times(1)).findById(1)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindUserOfTransaction() {
        // GIVEN
        whenever(transactionRepository.findById(any())).thenReturn(Optional.of(TransactionEntity.testEntity()))
        whenever(userRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedTransaction = transactionBusiness.getTransaction(1)

        // THEN
        assertNull(returnedTransaction)
    }

    @Test(expected = ProductNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindProductOfTransaction() {
        // GIVEN
        val userEntity = UserEntity.testEntity()
        whenever(transactionRepository.findById(any())).thenReturn(Optional.of(TransactionEntity.testEntity()))
        whenever(userRepository.findById(any())).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedTransaction = transactionBusiness.getTransaction(1)

        // THEN
        assertNull(returnedTransaction)
    }

    @Test
    fun shouldMakeTransactionFromIds() {
        // GIVEN
        val user = User.testUser()
        val userEntity = UserEntity.testEntity()
        val card = CreditCard.testCard()
        val cardEntity = CreditCardEntity.testEntity()
        val product = Product.testProduct()
        val productEntity = ProductEntity.testEntity()
        val transaction = Transaction.testTransaction()
        val transactionEntity = TransactionEntity.testEntity()
        whenever(productRepository.existsById(any())).thenReturn(true)
        whenever(userRepository.findById(user.id!!)).thenReturn(Optional.of(userEntity))
        whenever(creditCardRepository.findById(card.id!!)).thenReturn(Optional.of(cardEntity))
        whenever(paymentValidator.charge(any())).thenReturn(true)
        whenever(transactionRepository.save<TransactionEntity>(any())).thenReturn(transactionEntity)
        whenever(productRepository.findById(product.id!!)).thenReturn(Optional.of(productEntity))

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(user.id!!, card.id!!, product.id!!)

        // THEN
        assertThat(createdTransaction, equalTo(transaction))
    }

    @Test(expected = ProductNotFoundException::class)
    fun shouldThrowExceptionWhenTryingToBuyNonExistentProductById() {
        // GIVEN
        val user = User.testUser()
        val userEntity = UserEntity.testEntity()
        val card = CreditCard.testCard()
        val cardEntity = CreditCardEntity.testEntity()
        whenever(userRepository.findById(user.id!!)).thenReturn(Optional.of(userEntity))
        whenever(creditCardRepository.findById(card.id!!)).thenReturn(Optional.of(cardEntity))
        whenever(productRepository.existsById(any())).thenReturn(false)

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(1, 1, 1)

        // THEN
        assertNull(createdTransaction)
    }

    @Test(expected = UserCardMismatchException::class)
    fun shouldThrowExceptionWhenUserDoesNotOwnCard() {
        // GIVEN
        val userEntity = UserEntity.testEntityWithNoCardAtAll()
        val cardEntity = CreditCardEntity.testEntity()
        whenever(productRepository.existsById(any())).thenReturn(true)
        whenever(userRepository.findById(any())).thenReturn(Optional.of(userEntity))
        whenever(creditCardRepository.findById(any())).thenReturn(Optional.of(cardEntity))

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(1, 1, 1)

        // THEN
        assertNull(createdTransaction)
    }

    @Test
    fun shouldMakeTransactionWithUnsavedCardAndSavePaidCard() {
        // GIVEN
        val user = User.testUser()
        val userEntity = UserEntity.testEntity()
        val card = CreditCard.testCard()
        val product = Product.testProduct()
        val productEntity = ProductEntity.testEntity()
        val transaction = Transaction.testTransaction()
        val transactionEntity = TransactionEntity.testEntity()
        whenever(productRepository.existsById(any())).thenReturn(true)
        whenever(paymentValidator.charge(any())).thenReturn(true)
        whenever(transactionRepository.save<TransactionEntity>(any())).thenReturn(transactionEntity)
        whenever(userRepository.findById(user.id!!)).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(product.id!!)).thenReturn(Optional.of(productEntity))

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(user.id!!, card, product.id!!)

        // THEN
        assertThat(createdTransaction, equalTo(transaction))
        verify(creditCardRepository, times(1)).save<CreditCardEntity>(any())
    }

    @Test
    fun shouldMakeTransactionWithUnsavedCardAndNotSaveUnpaidCard() {
        // GIVEN
        val user = User.testUser()
        val userEntity = UserEntity.testEntity()
        val card = CreditCard.testCard()
        val product = Product.testProduct()
        val productEntity = ProductEntity.testEntity()
        val transaction = Transaction.testTransaction()
        val transactionEntity = TransactionEntity.testEntity()
        whenever(productRepository.existsById(any())).thenReturn(true)
        whenever(paymentValidator.charge(any())).thenReturn(false)
        whenever(transactionRepository.save<TransactionEntity>(any())).thenReturn(transactionEntity)
        whenever(userRepository.findById(user.id!!)).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(product.id!!)).thenReturn(Optional.of(productEntity))

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(user.id!!, card, product.id!!)

        // THEN
        assertThat(createdTransaction, equalTo(transaction))
        verify(creditCardRepository, times(0)).save<CreditCardEntity>(any())
    }

    @Test(expected = ProductNotFoundException::class)
    fun shouldThrowExceptionWhenTryingToBuyNonExistentProductWithUnsavedCard() {
        // GIVEN
        val card = CreditCard.testCard()
        whenever(productRepository.existsById(any())).thenReturn(false)

        // WHEN
        val createdTransaction = transactionBusiness.makeTransaction(1, card, 1)

        // THEN
        assertNull(createdTransaction)
    }
}