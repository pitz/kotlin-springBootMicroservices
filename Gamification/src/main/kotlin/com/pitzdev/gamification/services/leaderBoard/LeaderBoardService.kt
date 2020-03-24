package com.pitzdev.gamification.services.leaderBoard

import com.pitzdev.gamification.dtos.gameStats.GameStatsDTO

interface LeaderBoardService {

    fun newAttemptForUser(userId: Long, attemptId: Long, correct: Boolean) : GameStatsDTO

    fun retrieveStatsForUser(userId: Long) : GameStatsDTO

}