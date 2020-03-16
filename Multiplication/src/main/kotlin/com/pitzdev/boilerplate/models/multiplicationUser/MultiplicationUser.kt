package com.pitzdev.boilerplate.models.multiplicationUser

import javax.persistence.*
import com.pitzdev.boilerplate.models.base.BaseEntity
import com.pitzdev.boilerplate.models.multiplication.Multiplication
import com.pitzdev.boilerplate.models.user.User

@Entity
data class MultiplicationUser(
        @ManyToOne
        val user: User,

        @ManyToOne
        val multiplication: Multiplication,

        val resultAttempt: Int
) : BaseEntity()