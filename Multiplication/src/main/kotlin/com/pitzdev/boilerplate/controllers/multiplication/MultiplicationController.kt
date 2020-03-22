package com.pitzdev.boilerplate.controllers.multiplication

import com.pitzdev.boilerplate.dto.multiplication.SaveMultiplicationDTO
import com.pitzdev.boilerplate.dto.multiplicationResultAttempt.SaveAttemptDTO
import com.pitzdev.boilerplate.dto.multiplicationResultAttempt.SaveAttemptResponseDTO
import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.boilerplate.services.multiplication.MultiplicationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/multiplication")
class MultiplicationController(private val multiplicationService: MultiplicationService) {

    @PostMapping("/save")
    fun save(): SaveMultiplicationDTO {
        val multiplication = multiplicationService.createMultiplication()
        return SaveMultiplicationDTO(multiplication.id!!, multiplication.factorA, multiplication.factorB)
    }

    @PostMapping("/attempt")
    fun save(@RequestBody saveAttemptDTO : SaveAttemptDTO): SaveAttemptResponseDTO {
        val attempt: MultiplicationResultAttempt = multiplicationService.checkAttempt(saveAttemptDTO)
        return SaveAttemptResponseDTO(attempt.multiplication.id!!, attempt.correct)
    }

    @GetMapping("/list")
    fun list(@RequestParam("alias") alias : String): List<MultiplicationResultAttempt>? {
        return multiplicationService.getLastUserAttemptList(alias)
    }
}