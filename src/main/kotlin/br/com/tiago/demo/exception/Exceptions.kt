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
    System.out.println("This encrypted is: " + crypt)
    var decrypt = encryptor.decrypt(crypt)
    System.out.println("That decrypted is: " + decrypt)
    var decrypt2 = encryptor.decrypt(crypt)
    System.out.println("That decrypted again is: " + decrypt2)
    var crypt2 = encryptor.encrypt(this)
    System.out.println("This encrypted again is: " + crypt2)
    var isit = crypt == crypt2
    var isit2 = decrypt == decrypt2
    System.out.println("Crypts equal: " + isit)
    System.out.println("Decrypts equal: " + isit2)
    var omg = encryptor.decrypt("3c6feec35406cca24b02aef277ee1cf057f924b80f3cce4d7bf12b90d4301a55")
    var omg2 = encryptor.decrypt("a41b5ae0fd51e6a33d4d190b205ff9fc93a1846892d2d6d9cd1965ffa07ea06e")
    System.out.println("Old decrypt: " + omg)
    System.out.println("Old decrypt2: " + omg2)

    return crypt
}

fun String.decrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    return encryptor.decrypt(this)
}

fun Long.encrypted(): String {
    val encryptor = Encryptors.text("password", "123bad")
    var crypt = encryptor.encrypt(this.toString())
    System.out.println("This encrypted is: " + crypt)
    var decrypt = encryptor.decrypt(crypt).toLong()
    System.out.println("That decrypted is: " + decrypt)
    var decrypt2 = encryptor.decrypt(crypt).toLong()
    System.out.println("That decrypted again is: " + decrypt2)
    var crypt2 = encryptor.encrypt(this.toString())
    System.out.println("This encrypted again is: " + crypt2)
    var isit = crypt == crypt2
    var isit2 = decrypt == decrypt2
    System.out.println("Crypts equal: " + isit)
    System.out.println("Decrypts equal: " + isit2)
    var omg = encryptor.decrypt("3c6feec35406cca24b02aef277ee1cf057f924b80f3cce4d7bf12b90d4301a55")
    var omg2 = encryptor.decrypt("a41b5ae0fd51e6a33d4d190b205ff9fc93a1846892d2d6d9cd1965ffa07ea06e")
    System.out.println("Old decrypt: " + omg)
    System.out.println("Old decrypt2: " + omg2)

    return crypt
}