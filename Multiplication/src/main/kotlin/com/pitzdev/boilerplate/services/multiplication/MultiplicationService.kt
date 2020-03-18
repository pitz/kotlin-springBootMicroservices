package com.pitzdev.boilerplate.services.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.boilerplate.models.user.User
import com.pitzdev.boilerplate.repositories.multiplicationResultAttempt.MultiplicationResultAttemptRepository
import com.pitzdev.boilerplate.repositories.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MultiplicationService(val userRepository: UserRepository,
                            val multiplicationResultAttemptRepository: MultiplicationResultAttemptRepository) {

    public fun createRandomMultiplication() : Multiplication {
        return Multiplication(getRandomNumber(), getRandomNumber())
    }

    public fun getRandomNumber(): Int {
        return (11..99).shuffled().first()
    }

    public fun checkAttempt(attempt: MultiplicationResultAttempt) : Boolean {
        val user: User = userRepository.findByAlias(attempt.user.alias) ?: attempt.user
        val isCorrect = attempt.resultAttempt == attempt.multiplication.result
        val checkedAttempt = MultiplicationResultAttempt(user, attempt.multiplication, attempt.resultAttempt, isCorrect)
        multiplicationResultAttemptRepository.save(checkedAttempt)

       return isCorrect
    }

    public fun getLastUserAttemptList(userAlias: String) : List<MultiplicationResultAttempt>? {
        return multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias)
    }

}