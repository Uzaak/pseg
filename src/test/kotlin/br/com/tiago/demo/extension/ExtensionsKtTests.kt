package br.com.tiago.demo.extension

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.not
import org.junit.Test

class ExtensionsKtTests {

    @Test
    fun encryptDecryptSimpleTest() {
        // GIVEN
        val someString = "someString"

        // WHEN
        val encrypted = someString.encrypted()
        val decrypted = encrypted.decrypted()

        // THEN
        assertThat(decrypted, equalTo(someString))
    }

    @Test
    fun eachEncryptionIsDifferentButConsistent() {
        // GIVEN
        val someString = "someString"

        // WHEN
        val encrypted = someString.encrypted()
        val encrypted2 = someString.encrypted()
        val encrypted3 = someString.encrypted()
        val decrypted = encrypted.decrypted()
        val decrypted2 = encrypted2.decrypted()
        val decrypted3 = encrypted3.decrypted()

        // THEN
        assertThat(encrypted, not(equalTo(encrypted2)))
        assertThat(encrypted, not(equalTo(encrypted3)))
        assertThat(encrypted2, not(equalTo(encrypted3)))
        assertThat(decrypted, equalTo(decrypted2))
        assertThat(decrypted, equalTo(decrypted3))
        assertThat(decrypted2, equalTo(decrypted3))
        assertThat(decrypted, equalTo(someString))
        assertThat(decrypted2, equalTo(someString))
        assertThat(decrypted3, equalTo(someString))
    }

    @Test
    fun encryptDecryptSimpleTestWithLongNumbers() {
        // GIVEN
        val someLong: Long = 123456789012345678

        // WHEN
        val encrypted = someLong.encrypted()
        val decrypted = encrypted.decryptedLong()

        // THEN
        assertThat(decrypted, equalTo(someLong))
    }

    @Test
    fun eachEncryptionIsDifferentButConsistentWithLongNumbers() {
        // GIVEN
        val someLong: Long = 123456789012345678

        // WHEN
        val encrypted = someLong.encrypted()
        val encrypted2 = someLong.encrypted()
        val encrypted3 = someLong.encrypted()
        val decrypted = encrypted.decryptedLong()
        val decrypted2 = encrypted2.decryptedLong()
        val decrypted3 = encrypted3.decryptedLong()

        // THEN
        assertThat(encrypted, not(equalTo(encrypted2)))
        assertThat(encrypted, not(equalTo(encrypted3)))
        assertThat(encrypted2, not(equalTo(encrypted3)))
        assertThat(decrypted, equalTo(decrypted2))
        assertThat(decrypted, equalTo(decrypted3))
        assertThat(decrypted2, equalTo(decrypted3))
        assertThat(decrypted, equalTo(someLong))
        assertThat(decrypted2, equalTo(someLong))
        assertThat(decrypted3, equalTo(someLong))
    }

}