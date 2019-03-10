package br.com.tiago.demo.business

import br.com.tiago.demo.entity.UserEntity
import br.com.tiago.demo.exception.UserNotFoundException
import br.com.tiago.demo.model.User
import br.com.tiago.demo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserBusiness {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUser(userId: Long): User {
        val entity = userRepository.findById(userId).orElseThrow { UserNotFoundException() }
        return User(entity)
    }

    fun createUser(user: User): User {
        val entity = UserEntity(user)
        val persistedUser = userRepository.save(entity)
        return User(persistedUser)
    }

}