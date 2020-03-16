package com.pitzdev.boilerplate.services.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import org.springframework.stereotype.Service

@Service
class MultiplicationService() {

    public fun createRandomMultiplication() : Multiplication {
        val multiplication : Multiplication = Multiplication(getRandomNumber(), getRandomNumber())
        println(" @ multiplication, $multiplication")
        return multiplication
    }

    fun getRandomNumber(): Int {
        return (11..99).shuffled().first()
    }
}