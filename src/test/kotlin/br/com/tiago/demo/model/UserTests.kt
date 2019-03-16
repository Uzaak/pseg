package br.com.tiago.demo.model

import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.extension.decrypted
import br.com.tiago.demo.testEntity
import br.com.tiago.demo.testUser
import org.hamcrest.Matchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class UserTests {

    @Test
    fun shouldProperlyConvertToUserEntity() {
        // GIVEN
        val user = User.testUser()
        val entity = UserEntity.testEntity()

        // WHEN
        val convertedEntity = user.toEntity()

        // THEN
        assertThat(convertedEntity.id, equalTo(entity.id))
        assertThat(convertedEntity.name, equalTo(entity.name))
        assertThat(convertedEntity.cpf, equalTo(entity.cpf))
        assertThat(convertedEntity.email, equalTo(entity.email))
        assertThat(convertedEntity.password.decrypted(), equalTo(entity.password.decrypted()))
    }

    @Test
    fun shouldProperlyConvertFromUserEntity() {
        // GIVEN
        val user = User.testUser()
        val entity = UserEntity.testEntity()

        // WHEN
        val convertedModel = User.fromEntity(entity)

        // THEN
        assertThat(convertedModel, equalTo(user))
    }

}