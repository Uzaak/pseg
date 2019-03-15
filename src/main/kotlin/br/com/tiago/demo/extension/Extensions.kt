package br.com.tiago.demo.extension

import org.springframework.security.crypto.encrypt.Encryptors

/**
 * Encrypts current String
 * @return the encrypted String.
 */
fun String.encrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    var crypt = encryptor.encrypt(this)
    return crypt
}

/**
 * Decrypts current String
 * @return the decrypted String.
 */
fun String.decrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    return encryptor.decrypt(this)
}

/**
 * Encrypts current Long
 * @return the encrypted value as a String.
 */
fun Long.encrypted(): String {
    val encryptor = Encryptors.text("password", "bad123")
    var crypt = encryptor.encrypt(this.toString())
    return crypt
}

/**
 * Decrypts current String as a Long
 * @return the decrypted String as a Long value (may throw).
 */
fun String.decryptedLong(): Long {
    val encryptor = Encryptors.text("password", "bad123")
    return encryptor.decrypt(this).toLong()
}