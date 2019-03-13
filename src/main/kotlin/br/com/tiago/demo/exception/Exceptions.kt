package br.com.tiago.demo.exception

class CreditCardNotFoundException: Exception()
class ProductNotFoundException: Exception()
class UserNotFoundException: Exception()
class TransactionNotFoundException: Exception()

class InvalidUserException: Exception()
class UserCardMismatchException: Exception()

fun String.encrypted() = "${this.length}"