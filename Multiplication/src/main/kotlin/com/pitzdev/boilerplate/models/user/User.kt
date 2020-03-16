package com.pitzdev.boilerplate.models.user

import javax.persistence.*
import com.pitzdev.boilerplate.models.base.BaseEntity

@Entity
data class User(var alias: String) : BaseEntity()