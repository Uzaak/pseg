package br.com.tiago.demo.exception

class CreditCardNotFoundException: Exception()
class ProductNotFoundException: Exception()
class UserNotFoundException: Exception()
class TransactionNotFoundException: Exception()

class InvalidUserException: Exception()
class InvalidCreditCardException: Exception()
class InvalidProductException: Exception()

class UserCardMismatchException: Exception()