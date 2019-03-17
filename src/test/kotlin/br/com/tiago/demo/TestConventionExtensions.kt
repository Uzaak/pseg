package br.com.tiago.demo

import br.com.tiago.demo.entity.CreditCardEntity
import br.com.tiago.demo.entity.ProductEntity
import br.com.tiago.demo.entity.TransactionEntity
import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.extension.encrypted
import br.com.tiago.demo.model.CreditCard
import br.com.tiago.demo.model.Product
import br.com.tiago.demo.model.Transaction
import br.com.tiago.demo.model.User
import java.util.*

/*
 * This class contains convenient extensions
 * FOR TESTING PURPOSES ONLY
 * and it should NOT be carried over to the main code
*/

// CREDIT CARD
fun CreditCard.Companion.testCard() = CreditCard(1, 1234567890123456, 1, "John Appleseed", 123, Date(0))
fun CreditCard.Companion.withNullId() = CreditCard(null, 1234, 1, "John Appleseed", 123, Date(0))
fun CreditCard.Companion.evenCard() = CreditCard(2, 24680, 1, "John Appleseed", 456, Date())
fun CreditCard.Companion.oddCard() = CreditCard(3, 13579, 1, "John Appleseed", 789, Date())

fun CreditCardEntity.Companion.testEntity() = CreditCardEntity(1, "231d1c3cde54bcc53c7a2bbcfdb2e8217d81a560342f52bd221d8dcd409b44e48247517284f5026e94cf7d7988c8b33a", 1, "John Appleseed", 123, Date(0))

// PRODUCT
fun Product.Companion.testProduct() = Product(1, "Test Product Description Yada Yada", 1234567.89)
fun Product.Companion.withNullId() = Product(null, "Test Product With Null Id", 9876543.21)

fun ProductEntity.Companion.testEntity() = ProductEntity(1, "Test Product Description Yada Yada", 1234567.89)

// USER
fun User.Companion.testUser() = User(1, "John Appleseed", 12345678900, "some@e.mail", "superstrong_P@ssW0rD")
fun User.Companion.withNullId() = User(null, "John Appleseed", 123, "some@e.mail", "superstrong_P@ssW0rD")

fun UserEntity.Companion.testEntity() = UserEntity(1, "John Appleseed", 12345678900, "some@e.mail", "25670ebd6f7bb0129af0c1df65fa3ab9ef46b015c63eb4fd31264a7808c483f406ff292e569f32d1bb2919f50422e934")

// TRANSACTION
fun Transaction.Companion.testTransaction() = Transaction(1, User.testUser(), 1234567890123456, "John Appleseed", Product.testProduct(), true)

fun TransactionEntity.Companion.testEntity() = TransactionEntity(1, 1, "231d1c3cde54bcc53c7a2bbcfdb2e8217d81a560342f52bd221d8dcd409b44e48247517284f5026e94cf7d7988c8b33a", "John Appleseed", 1, true)