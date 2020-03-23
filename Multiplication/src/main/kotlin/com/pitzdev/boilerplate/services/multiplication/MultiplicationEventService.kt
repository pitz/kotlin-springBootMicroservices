package com.pitzdev.boilerplate.services.multiplication

import com.pitzdev.boilerplate.dtos.events.MultiplicationSolvedDTO
import com.pitzdev.boilerplate.events.publisher.EventPublisher
import com.pitzdev.boilerplate.models.multiplicationResultAttempt.MultiplicationResultAttempt
import org.springframework.stereotype.Service

@Service
class MultiplicationEventService() {

    public fun dispatchMultiplicationSolved(attempt: MultiplicationResultAttempt) {
        val eventInfo = MultiplicationSolvedDTO(attempt.id!!, attempt.user.id!!, attempt.correct)

        val eventPublisher = EventPublisher()
        eventPublisher.publish("multiplication.solved", eventInfo.toString().toByteArray())
    }
}