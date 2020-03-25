package com.pitzdev.gamification.services.leaderBoard

import com.pitzdev.gamification.models.leaderBord.LeaderBoard
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class LeaderBoardServiceImpl : LeaderBoardService {

    public override fun getCurrentLeaderBoard() : List<LeaderBoard> {
        return emptyList()
    }
}