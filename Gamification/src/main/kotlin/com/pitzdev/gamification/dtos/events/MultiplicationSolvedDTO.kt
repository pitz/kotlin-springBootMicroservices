package com.pitzdev.gamification.dtos.events

data class MultiplicationSolvedDTO(val multiplicationResultAttemptId: Long, val userId: Long, val correct: Boolean)