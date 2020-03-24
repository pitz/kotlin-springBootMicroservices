package com.pitzdev.gamification.repositories.scoreCard

import com.pitzdev.gamification.models.badgeCard.BadgeCard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoreCardRepository : CrudRepository<BadgeCard, Long> {

    fun findByUserIdOrderByDateCreatedDesc(userId: Long): List<BadgeCard>

}