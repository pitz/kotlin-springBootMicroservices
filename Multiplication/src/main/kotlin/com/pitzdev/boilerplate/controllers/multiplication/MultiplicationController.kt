package com.pitzdev.boilerplate.controllers.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.services.multiplication.MultiplicationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/multiplication")
class MultiplicationController(private val multiplicationService: MultiplicationService) {

    @GetMapping("/test")
    fun list() {
        val test : Multiplication = multiplicationService.createRandomMultiplication()
        assert(test.factorA > 0)
    }
}