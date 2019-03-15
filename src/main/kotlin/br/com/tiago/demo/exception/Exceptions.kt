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