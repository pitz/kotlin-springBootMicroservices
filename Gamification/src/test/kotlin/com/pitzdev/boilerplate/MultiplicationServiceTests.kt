package com.pitzdev.gamification

import com.pitzdev.gamification.models.badgeCard.ScoreCard
import com.pitzdev.gamification.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.gamification.models.user.User
import com.pitzdev.gamification.services.multiplication.UserService

import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MultiplicationServiceTests() {

    @Mock
    lateinit var multiplicationService : UserService

	@Test
	fun whenValidRandomNumber_returnValue() {
        val factorA = 15
        val factorB = 20
        val mockMultiplicationService = Mockito.mock(UserService::class.java)

        Mockito.`when`(mockMultiplicationService.createRandomMultiplication()).thenReturn(ScoreCard(factorA, factorB))
        val multiplication : ScoreCard = mockMultiplicationService.createRandomMultiplication()

        assert(multiplication.factorA == factorA)
        assert(multiplication.factorB == factorB)
        assert(multiplication.result == factorA * factorB)
    }

    @Test
    fun whenInvalidAttempt_returnFalse() {
        val multiplication : ScoreCard = ScoreCard(5, 5)
        val attempt  : MultiplicationResultAttempt = MultiplicationResultAttempt(User("Pitz"), multiplication, 10)

        assert(!multiplicationService.checkAttempt(attempt))
    }

    @Test
    fun whenValidAttempt_returnTrue() {
        val multiplication : ScoreCard = ScoreCard(5, 5)
        val attempt  : MultiplicationResultAttempt = MultiplicationResultAttempt(User("Pitz"), multiplication, 25)

        assert(attempt.multiplication.result == 25)
        assert(attempt.resultAttempt == attempt.multiplication.result)
    }
}
