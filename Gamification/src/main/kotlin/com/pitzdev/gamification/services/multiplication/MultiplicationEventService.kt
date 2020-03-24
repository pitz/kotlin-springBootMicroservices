package com.pitzdev.gamification.services.multiplication

import com.google.gson.Gson
import com.pitzdev.gamification.dtos.events.MultiplicationSolvedDTO
import com.pitzdev.gamification.events.publisher.EventPublisher
import com.pitzdev.gamification.models.multiplicationResultAttempt.MultiplicationResultAttempt
import org.springframework.stereotype.Service

@Service
class MultiplicationEventService() {

    public fun dispatchMultiplicationSolved(attempt: MultiplicationResultAttempt) {
        val eventInfo = MultiplicationSolvedDTO(attempt.id!!, attempt.user.id!!, attempt.correct)
        val eventInfoJson = Gson().toJson(eventInfo)

        val eventPublisher = EventPublisher()
        eventPublisher.publish("multiplication.solved", eventInfoJson)
    }
}