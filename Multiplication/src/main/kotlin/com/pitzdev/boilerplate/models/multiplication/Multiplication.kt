package com.pitzdev.boilerplate.models.multiplication

import javax.persistence.*
import com.pitzdev.boilerplate.models.base.BaseEntity

@Entity
data class Multiplication(var factorA: Int = 0,
                          var factorB: Int = 0) : BaseEntity() {
    var result: Int = factorA * factorB
}