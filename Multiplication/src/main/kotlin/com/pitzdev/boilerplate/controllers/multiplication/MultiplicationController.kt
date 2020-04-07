package com.pitzdev.boilerplate.controllers.multiplication

import com.pitzdev.boilerplate.dtos.multiplication.SaveMultiplicationDTO
import com.pitzdev.boilerplate.dtos.multiplicationResultAttempt.GetAttemptResponseDTO
import com.pitzdev.boilerplate.dtos.multiplicationResultAttempt.SaveAttemptDTO
import com.pitzdev.boilerplate.dtos.multiplicationResultAttempt.SaveAttemptResponseDTO
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

    @GetMapping("/list")
    fun list(@RequestParam("alias") alias: String): List<MultiplicationResultAttempt>? {
        return multiplicationService.getLastUserAttemptList(alias)
    }

    @PostMapping("/attempt")
    fun save(@RequestBody saveAttemptDTO: SaveAttemptDTO): SaveAttemptResponseDTO {
        val attempt: MultiplicationResultAttempt = multiplicationService.checkAttempt(saveAttemptDTO)
        return SaveAttemptResponseDTO(attempt.multiplication.id!!, attempt.correct)
    }

    @GetMapping("/attempt")
    fun getAttempt(@RequestParam("id") id: Long): GetAttemptResponseDTO? {
        return multiplicationService.getAttempt(id)
    }
}