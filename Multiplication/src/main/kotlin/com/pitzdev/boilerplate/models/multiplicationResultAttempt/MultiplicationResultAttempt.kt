package com.pitzdev.boilerplate.models.multiplicationResultAttempt

import javax.persistence.*
import com.pitzdev.boilerplate.models.base.BaseEntity
import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.user.User

@Entity
data class MultiplicationResultAttempt (
        @ManyToOne
        val user: User,

        @ManyToOne
        val multiplication: Multiplication,

        val resultAttempt: Int,

        val correct: Boolean = false
) : BaseEntity()