package com.pitzdev.boilerplate.services.multiplication

import com.pitzdev.boilerplate.dto.multiplicationResultAttempt.SaveAttemptDTO
import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.boilerplate.models.user.User
import com.pitzdev.boilerplate.repositories.multiplication.MultiplicationRepository
import com.pitzdev.boilerplate.repositories.multiplicationResultAttempt.MultiplicationResultAttemptRepository
import com.pitzdev.boilerplate.services.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Transactional
@Service
class MultiplicationService(var userService: UserService,
                            val multiplicationRepository: MultiplicationRepository,
                            val multiplicationResultAttemptRepository: MultiplicationResultAttemptRepository) {

    public fun createMultiplication() : Multiplication {
        val multiplication = Multiplication(getRandomNumber(), getRandomNumber())
        return multiplicationRepository.save(multiplication)
    }

    public fun getRandomNumber(): Int {
        return (11..99).shuffled().first()
    }

    public fun checkAttempt(attemptDto: SaveAttemptDTO) : MultiplicationResultAttempt {
        val user: User = userService.saveUserIfNecessary(attemptDto.alias)
        val multiplication = getMultipliacation(attemptDto.multiplicationId)

        val isCorrect = attemptDto.result == multiplication.result
        val checkedAttempt = MultiplicationResultAttempt(user, multiplication, attemptDto.result, isCorrect)
        return multiplicationResultAttemptRepository.save(checkedAttempt)
    }

    private fun getMultipliacation(multiplicationId: Long) : Multiplication {
        val multiplication = multiplicationRepository.findById(multiplicationId)
        if (multiplication.isEmpty) throw Exception("Multiplication not found.")
        return multiplication.get()
    }

    public fun getLastUserAttemptList(userAlias: String) : List<MultiplicationResultAttempt>? {
        return multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias)
    }

}