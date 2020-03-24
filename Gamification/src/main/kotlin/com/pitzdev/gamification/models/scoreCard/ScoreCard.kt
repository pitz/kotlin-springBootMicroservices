package com.pitzdev.gamification.models.scoreCard

import com.pitzdev.gamification.models.base.BaseEntity
import javax.persistence.Entity

@Entity
data class ScoreCard(var userId: Long, var attemptId: Long, var score: Int) : BaseEntity()