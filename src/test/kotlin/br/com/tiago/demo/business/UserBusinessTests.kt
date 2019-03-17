package br.com.tiago.demo.business

import br.com.tiago.demo.*
import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.CreditCardNotFoundException
import br.com.tiago.demo.exception.ProductNotFoundException
import br.com.tiago.demo.exception.TransactionNotFoundException
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.CreditCardRepository
import br.com.tiago.demo.repository.ProductRepository
import br.com.tiago.demo.repository.TransactionRepository
import br.com.tiago.demo.repository.UserRepository
import com.nhaarman.mockito_kotlin.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class UserBusinessTests {

    private lateinit var userBusiness: UserBusiness

    private var userRepository: UserRepository = mock()
    private var productRepository: ProductRepository = mock()
    private var creditCardRepository: CreditCardRepository = mock()
    private var transactionRepository: TransactionRepository = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        userBusiness = UserBusiness(userRepository, creditCardRepository, productRepository, transactionRepository)
    }

    @Test
    fun shouldCorrectlyReturnUser() {
        // GIVEN
        val user = User.testUser()
        whenever(userRepository.findById(any())).thenReturn(Optional.of(UserEntity.testEntity()))

        // WHEN
        val returnedUser = userBusiness.getUser(1)

        // THEN
        assertThat(returnedUser, equalTo(user))
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindUser() {
        // GIVEN
        whenever(userRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedUser = userBusiness.getUser(1)

        // THEN
        assertNull(returnedUser)
        verify(userRepository, times(1)).findById(1)
    }

    @Test
    fun shouldCreateUser() {
        // GIVEN
        val user = User.withNullId()
        val entity = user.toEntity()
        whenever(userRepository.save<UserEntity>(any())).thenReturn(entity)

        // WHEN
        val createdUser = userBusiness.createUser(user)

        // THEN
        assertThat(createdUser, equalTo(user))
    }

    @Test
    fun shouldCorrectlyReturnUserCreditCards() {
        // GIVEN
        val card = CreditCard.testCard()
        val entity = CreditCardEntity.testEntity()
        whenever(creditCardRepository.findAllByHolderId(any())).thenReturn(Optional.of(listOf(entity, entity, entity)))

        // WHEN
        val userCards = userBusiness.getUserCards(1)

        // THEN
        assertThat(userCards, equalTo(listOf(card, card, card)))
    }

    @Test(expected = CreditCardNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindUserCards() {
        // GIVEN
        whenever(creditCardRepository.findAllByHolderId(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedCards = userBusiness.getUserCards(1)

        // THEN
        assertNull(returnedCards)
        verify(creditCardRepository, times(1)).findAllByHolderId(1)
    }

    @Test
    fun shouldCorrectlyReturnAllUserTransactions() {
        // GIVEN
        val transaction = Transaction.testTransaction()
        val transactionEntity = TransactionEntity.testEntity()
        val userEntity = UserEntity.testEntity()
        val productEntity = ProductEntity.testEntity()
        whenever(transactionRepository.findAllByUserId(any())).thenReturn(Optional.of(listOf(transactionEntity, transactionEntity, transactionEntity)))
        whenever(userRepository.findById(any())).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(any())).thenReturn(Optional.of(productEntity))

        // WHEN
        val userTransactions = userBusiness.getUserTransactions(1)

        // THEN
        assertThat(userTransactions, equalTo(listOf(transaction, transaction, transaction)))
    }

    @Test(expected = TransactionNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindUserTransactions() {
        // GIVEN
        whenever(transactionRepository.findAllByUserId(any())).thenReturn(Optional.empty())

        // WHEN
        val returnedTransactions = userBusiness.getUserTransactions(1)

        // THEN
        assertNull(returnedTransactions)
        verify(transactionRepository, times(1)).findAllByUserId(1)
    }

    @Test(expected = UserNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindUserOfOneTransaction() {
        // GIVEN
        val transactionEntity = TransactionEntity.testEntity()
        val productEntity = ProductEntity.testEntity()
        whenever(transactionRepository.findAllByUserId(any())).thenReturn(Optional.of(listOf(transactionEntity, transactionEntity, transactionEntity)))
        whenever(userRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val userTransactions = userBusiness.getUserTransactions(1)

        // THEN
        assertNull(userTransactions)
    }

    @Test(expected = ProductNotFoundException::class)
    fun shouldThrowExceptionWhenCannotFindProductOfOneTransaction() {
        // GIVEN
        val transactionEntity = TransactionEntity.testEntity()
        val userEntity = UserEntity.testEntity()
        whenever(transactionRepository.findAllByUserId(any())).thenReturn(Optional.of(listOf(transactionEntity, transactionEntity, transactionEntity)))
        whenever(userRepository.findById(any())).thenReturn(Optional.of(userEntity))
        whenever(productRepository.findById(any())).thenReturn(Optional.empty())

        // WHEN
        val userTransactions = userBusiness.getUserTransactions(1)

        // THEN
        assertNull(userTransactions)
    }

}