package com.pitzdev.gamification.repositories.badgeCard

import com.pitzdev.gamification.models.badgeCard.BadgeCard
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BadgeCardRepository : CrudRepository<BadgeCard, Long> {

    fun findByUserIdOrderByDateCreatedDesc(userId: Long): List<BadgeCard>

}