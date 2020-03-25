package com.pitzdev.gamification.controllers.user

import com.pitzdev.gamification.dtos.gameStats.GameStatsDTO
import com.pitzdev.gamification.services.game.GameService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val gameService: GameService) {

    @GetMapping("/list")
    public fun getStatsForUser(@RequestParam("userId") userId: Long) : GameStatsDTO {
        return gameService.retrieveStatsForUser(userId)
    }

}