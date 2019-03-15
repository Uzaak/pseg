package br.com.tiago.demo.exception

import org.springframework.security.crypto.encrypt.Encryptors

class CreditCardNotFoundException: Exception()
class ProductNotFoundException: Exception()
class UserNotFoundException: Exception()
class TransactionNotFoundException: Exception()

class InvalidUserException: Exception()
class InvalidCreditCardException: Exception()
class InvalidProductException: Exception()

class UserCardMismatchException: Exception()

fun String.encrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    var crypt = encryptor.encrypt(this)
    return crypt
}

fun String.decrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    return encryptor.decrypt(this)
}

fun Long.encrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    var crypt = encryptor.encrypt(this.toString())
    return crypt
}