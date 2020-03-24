package com.pitzdev.gamification.models.badgeCard

import com.pitzdev.gamification.enums.Badge
import com.pitzdev.gamification.models.base.BaseEntity
import javax.persistence.Entity

@Entity
data class BadgeCard(var userId: Long, var badge: Badge) : BaseEntity()