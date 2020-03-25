package com.pitzdev.gamification.services.game

import com.pitzdev.gamification.dtos.gameStats.GameStatsDTO
import com.pitzdev.gamification.enums.Badge
import com.pitzdev.gamification.models.badgeCard.BadgeCard
import com.pitzdev.gamification.models.scoreCard.ScoreCard
import com.pitzdev.gamification.repositories.badgeCard.BadgeCardRepository
import com.pitzdev.gamification.repositories.scoreCard.ScoreCardRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.streams.toList

@Transactional
@Service
class GameServiceImpl(private val scoreCardRepository: ScoreCardRepository,
                      private val badgeCardRepository: BadgeCardRepository) : GameService {

    override fun newAttemptForUser(userId: Long, attemptId: Long, correct: Boolean) : GameStatsDTO {
        if (correct) {
            val scoreCard = ScoreCard(userId, attemptId, 10)
            scoreCardRepository.save(scoreCard)

            println("User $userId scored $scoreCard.score points for attempt $attemptId")

            val badgeCards : List<BadgeCard> = processForBadges(userId, attemptId)
            return GameStatsDTO(userId, scoreCard.score, badgeCards.stream().map { it.badge }.toList())
        }

        return GameStatsDTO(userId, 0, null);
    }

    private fun processForBadges(userId: Long, attemptId: Long): List<BadgeCard> {
        val totalScore = scoreCardRepository.getTotalScoreForUser(userId)
        val scoreCardList = scoreCardRepository.findByUserIdOrderByDateCreatedDesc(userId).toMutableList()
        val badgeCardList = badgeCardRepository.findByUserIdOrderByDateCreatedDesc(userId).toMutableList()

        val bronzeBadge = checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.BRONZE_MULTIPLICATOR, totalScore, 100, userId)
        if (bronzeBadge.isPresent) badgeCardList.add(bronzeBadge.get())

        val silverBadge = checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.SILVER_MULTIPLICATOR, totalScore, 500, userId)
        if (silverBadge.isPresent) badgeCardList.add(silverBadge.get())

        val goldBadge = checkAndGiveBadgeBasedOnScore(badgeCardList, Badge.GOLD_MULTIPLICATOR, totalScore, 999, userId)
        if (goldBadge.isPresent) badgeCardList.add(goldBadge.get())

        if (scoreCardList.size == 1 && !containsBadge(badgeCardList, Badge. FIRST_WON)) {
            return listOf(giveBadgeToUser(Badge.FIRST_WON, userId))
        }

        return badgeCardList
    }

    override fun retrieveStatsForUser(userId: Long) : GameStatsDTO {
        return GameStatsDTO(userId,
                            scoreCardRepository.getTotalScoreForUser(userId),
                            badgeCardRepository.findByUserIdOrderByDateCreatedDesc(userId).stream().map { it.badge }.toList())
    }

    private fun checkAndGiveBadgeBasedOnScore(badgeCards: List<BadgeCard>,
                                              badge: Badge,
                                              score: Int,
                                              scoreThreshold: Int,
                                              userId: Long) : Optional<BadgeCard> {
        if (score >= scoreThreshold && !containsBadge(badgeCards, badge)) {
            return Optional.of(giveBadgeToUser(badge, userId));
        }
        return Optional.empty();
    }

    private fun containsBadge(badgeCards: List<BadgeCard>, badge: Badge) : Boolean {
        return badgeCards.stream().anyMatch { it?.badge == badge };
    }

    private fun giveBadgeToUser(badge: Badge, userId: Long) : BadgeCard {
        val badgeCard = BadgeCard(userId, badge)
        return badgeCardRepository.save(badgeCard)
    }
}