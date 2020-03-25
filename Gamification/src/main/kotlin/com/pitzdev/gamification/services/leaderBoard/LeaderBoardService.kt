package com.pitzdev.gamification.services.leaderBoard

import com.pitzdev.gamification.models.leaderBord.LeaderBoard

interface LeaderBoardService {

    public fun getCurrentLeaderBoard() : List<LeaderBoard>

}