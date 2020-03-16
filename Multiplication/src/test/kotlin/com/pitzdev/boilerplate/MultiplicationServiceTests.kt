package com.pitzdev.boilerplate

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.services.multiplication.MultiplicationService

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MultiplicationServiceTests() {

	@Test
	fun whenValidRandomNumber_returnValue() {
        val factorA = 15
        val factorB = 20
        val mockBookService = Mockito.mock(MultiplicationService::class.java)

        Mockito.`when`(mockBookService.createRandomMultiplication()).thenReturn(User(factorA, factorB))
        val multiplication : Multiplication = mockBookService.createRandomMultiplication()

        assert(multiplication.factorA == factorA)
        assert(multiplication.factorB == factorB)
        assert(multiplication.result == factorA * factorB)
    }
}
