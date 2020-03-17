package com.pitzdev.boilerplate.repositories.user

import com.pitzdev.boilerplate.models.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>