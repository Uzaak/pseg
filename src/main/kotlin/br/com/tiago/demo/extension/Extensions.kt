package br.com.tiago.demo.extension

import org.springframework.security.crypto.encrypt.Encryptors

// TODO: Documentation
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

fun String.decryptedLong(): Long {
    val encryptor = Encryptors.text("password", "bad123")
    return encryptor.decrypt(this).toLong()
}