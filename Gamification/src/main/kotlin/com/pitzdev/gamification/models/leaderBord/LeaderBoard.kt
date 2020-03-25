package com.pitzdev.gamification.models.leaderBord

import com.pitzdev.gamification.models.base.BaseEntity
import javax.persistence.Entity

@Entity
data class LeaderBoard(var userId: Long, var totalScore: Long) : BaseEntity()