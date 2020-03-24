package com.pitzdev.gamification.services.game

import com.pitzdev.gamification.dtos.gameStats.GameStatsDTO

interface GameService {

    fun newAttemptForUser(userId: Long, attemptId: Long, correct: Boolean) : GameStatsDTO

    fun retrieveStatsForUser(userId: Long) : GameStatsDTO

}