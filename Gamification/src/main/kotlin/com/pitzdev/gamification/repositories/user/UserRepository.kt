package com.pitzdev.gamification.repositories.user

import com.pitzdev.gamification.models.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findByAlias(alias: String): User?

}