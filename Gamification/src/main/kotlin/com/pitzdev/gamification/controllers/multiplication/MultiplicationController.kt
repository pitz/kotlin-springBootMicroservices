package com.pitzdev.gamification.controllers.multiplication

import com.pitzdev.gamification.dtos.gameStats.GameStatsDTO
import com.pitzdev.gamification.dtos.multiplicationResultAttempt.SaveAttemptDTO
import com.pitzdev.gamification.dtos.multiplicationResultAttempt.SaveAttemptResponseDTO
import com.pitzdev.gamification.models.multiplicationResultAttempt.MultiplicationResultAttempt
import com.pitzdev.gamification.services.multiplication.MultiplicationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/multiplication")
class MultiplicationController(private val multiplicationService: MultiplicationService) {

    @PostMapping("/save")
    fun save(): GameStatsDTO {
        val multiplication = multiplicationService.createMultiplication()
        return GameStatsDTO(multiplication.id!!, multiplication.factorA, multiplication.factorB)
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