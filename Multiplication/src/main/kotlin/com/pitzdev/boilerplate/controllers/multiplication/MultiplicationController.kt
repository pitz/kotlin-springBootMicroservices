package com.pitzdev.boilerplate.controllers.multiplication

import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.boilerplate.services.multiplication.MultiplicationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/multiplication")
class MultiplicationController(private val multiplicationService: MultiplicationService) {

    @GetMapping("/test")
    fun test() {
        val test : Multiplication = multiplicationService.createRandomMultiplication()
        println(test.result)
    }

    @GetMapping("/list")
    fun list(@RequestParam("alias") alias : String): List<MultiplicationResultAttempt>? {
        return multiplicationService.getLastUserAttemptList(alias)
    }
}