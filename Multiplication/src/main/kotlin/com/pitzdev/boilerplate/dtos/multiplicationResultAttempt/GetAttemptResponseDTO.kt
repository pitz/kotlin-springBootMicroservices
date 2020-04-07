package com.pitzdev.boilerplate.dtos.multiplicationResultAttempt

data class GetAttemptResponseDTO(val userId: Long,
                                 val multiplicationId: Long,
                                 val resultAttempt: Int,
                                 val correct: Boolean,
                                 val multiplicationFactorA: Int,
                                 val multiplicationFactorB: Int)