package com.pitzdev.boilerplate.services.user

import com.pitzdev.boilerplate.models.user.User
import com.pitzdev.boilerplate.repositories.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(val userRepository: UserRepository) {

    public fun saveUserIfNecessary(alias: String) : User {
        val user: User? = userRepository.findByAlias(alias)
        if (user != null) return user

        val createdUser = User(alias)
        return userRepository.save(createdUser)
    }
}