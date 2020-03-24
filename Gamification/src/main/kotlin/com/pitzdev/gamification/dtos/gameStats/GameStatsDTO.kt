package com.pitzdev.gamification.dtos.gameStats

import com.pitzdev.gamification.enums.Badge

data class GameStatsDTO(val userId: Long, val score: Int, val badgeList: List<Badge>?)