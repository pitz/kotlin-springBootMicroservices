package com.pitzdev.gamification.services.multiplication

import com.pitzdev.gamification.dtos.multiplicationResultAttempt.SaveAttemptDTO
import com.pitzdev.gamification.models.badgeCard.ScoreCard
import com.pitzdev.gamification.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.gamification.models.user.User
import com.pitzdev.gamification.repositories.badgeCard.ScoreCardRepository
import com.pitzdev.gamification.repositories.multiplicationResultAttempt.MultiplicationResultAttemptRepository
import com.pitzdev.gamification.services.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception

@Transactional
@Service
class MultiplicationService(val multiplicationEventService: MultiplicationEventService,
                            val userService: UserService,
                            val multiplicationRepository: ScoreCardRepository,
                            val multiplicationResultAttemptRepository: MultiplicationResultAttemptRepository) {

    public fun createMultiplication() : ScoreCard {
        val multiplication = ScoreCard(getRandomNumber(), getRandomNumber())
        return multiplicationRepository.save(multiplication)
    }

    public fun getRandomNumber(): Int {
        return (11..99).shuffled().first()
    }

    public fun checkAttempt(attemptDto: SaveAttemptDTO) : MultiplicationResultAttempt {
        val user: User = userService.saveUserIfNecessary(attemptDto.alias)
        val multiplication = getMultipliacation(attemptDto.multiplicationId)

        val isCorrect = attemptDto.result == multiplication.result
        var checkedAttempt = MultiplicationResultAttempt(user, multiplication, attemptDto.result, isCorrect)
        checkedAttempt = multiplicationResultAttemptRepository.save(checkedAttempt)
        multiplicationEventService.dispatchMultiplicationSolved(checkedAttempt)

        return checkedAttempt
    }

    private fun getMultipliacation(multiplicationId: Long) : ScoreCard {
        val multiplication = multiplicationRepository.findById(multiplicationId)
        if (multiplication.isEmpty) throw Exception("Multiplication not found.")
        return multiplication.get()
    }

    public fun getLastUserAttemptList(userAlias: String) : List<MultiplicationResultAttempt>? {
        return multiplicationResultAttemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias)
    }

}