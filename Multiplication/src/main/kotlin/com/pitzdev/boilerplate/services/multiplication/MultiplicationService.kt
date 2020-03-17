package com.pitzdev.boilerplate.services.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import org.springframework.stereotype.Service

@Service
class MultiplicationService() {

    public fun createRandomMultiplication() : Multiplication {
        return Multiplication(getRandomNumber(), getRandomNumber())
    }

    public fun getRandomNumber(): Int {
        return (11..99).shuffled().first()
    }

    public fun checkAttempt(multiplicationResultAttempt: MultiplicationResultAttempt) : Boolean {
        return multiplicationResultAttempt.resultAttempt == multiplicationResultAttempt.multiplication.result
    }

}