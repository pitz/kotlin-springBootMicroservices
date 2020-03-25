package com.pitzdev.gamification.controllers.leaderBoard

import com.pitzdev.gamification.models.leaderBord.LeaderBoard
import com.pitzdev.gamification.services.leaderBoard.LeaderBoardService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/leaderBoard")
class LeaderBoardController(private val leaderBoardService: LeaderBoardService) {

    @GetMapping("/list")
    public fun getLeaderBoard() : List<LeaderBoard> {
        return leaderBoardService.getCurrentLeaderBoard()
    }

}