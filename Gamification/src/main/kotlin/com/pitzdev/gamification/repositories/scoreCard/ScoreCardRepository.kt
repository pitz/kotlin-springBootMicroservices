package com.pitzdev.gamification.repositories.scoreCard

import com.pitzdev.gamification.models.leaderBord.LeaderBoard
import com.pitzdev.gamification.models.scoreCard.ScoreCard
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ScoreCardRepository : CrudRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
    fun getTotalScoreForUser(@Param("userId") userId: Long) : Int


//    @Query("SELECT LeaderBoard(s.userId, SUM(s.score)) FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
//    fun findFirst10(): List<LeaderBoard>

    fun findByUserIdOrderByDateCreatedDesc(userId: Long): List<ScoreCard>

}