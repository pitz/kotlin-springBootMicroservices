package com.pitzdev.boilerplate.repositories.multiplicationResultAttempt

import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MultiplicationResultAttemptRepository : CrudRepository<MultiplicationResultAttempt, Long> {

    fun findTop5ByUserAliasOrderByIdDesc(userAlias : String) : List<MultiplicationResultAttempt>?

}