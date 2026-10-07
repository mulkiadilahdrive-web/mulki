package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class KiperProgressState(
  val openedMateriIds: Set<Int> = emptySet(),
  val completedMateriIds: Set<Int> = emptySet(),
  val arHotspotsViewed: Set<String> = emptySet(),
  val arQuizCompleted: Boolean = false,
  val calculatorUsedCount: Int = 0,
  val chatbotInteractionsCount: Int = 0,
  val completedGameLevels: Set<Int> = emptySet(),
  val highestGameScore: Int = 0,
  val totalXp: Int = 0,
  val unlockedBadgeIds: Set<String> = emptySet()
)

class KiperRepository(context: Context) {
  private val prefs: SharedPreferences =
    context.getSharedPreferences("kiper_prefs_v1", Context.MODE_PRIVATE)

  private val _progressState = MutableStateFlow(loadState())
  val progressState: StateFlow<KiperProgressState> = _progressState.asStateFlow()

  private fun loadState(): KiperProgressState {
    val openedMateri = prefs.getStringSet("opened_materi", emptySet())?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    val completedMateri = prefs.getStringSet("completed_materi", emptySet())?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    val arHotspots = prefs.getStringSet("ar_hotspots", emptySet()) ?: emptySet()
    val arQuiz = prefs.getBoolean("ar_quiz", false)
    val calcCount = prefs.getInt("calc_count", 0)
    val chatCount = prefs.getInt("chat_count", 0)
    val gameLevels = prefs.getStringSet("game_levels", emptySet())?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    val highestScore = prefs.getInt("game_score", 0)
    val xp = prefs.getInt("total_xp", 0)
    val badges = prefs.getStringSet("badges", emptySet()) ?: emptySet()

    return KiperProgressState(
      openedMateriIds = openedMateri,
      completedMateriIds = completedMateri,
      arHotspotsViewed = arHotspots,
      arQuizCompleted = arQuiz,
      calculatorUsedCount = calcCount,
      chatbotInteractionsCount = chatCount,
      completedGameLevels = gameLevels,
      highestGameScore = highestScore,
      totalXp = xp,
      unlockedBadgeIds = badges
    )
  }

  private fun saveState(state: KiperProgressState) {
    prefs.edit().apply {
      putStringSet("opened_materi", state.openedMateriIds.map { it.toString() }.toSet())
      putStringSet("completed_materi", state.completedMateriIds.map { it.toString() }.toSet())
      putStringSet("ar_hotspots", state.arHotspotsViewed)
      putBoolean("ar_quiz", state.arQuizCompleted)
      putInt("calc_count", state.calculatorUsedCount)
      putInt("chat_count", state.chatbotInteractionsCount)
      putStringSet("game_levels", state.completedGameLevels.map { it.toString() }.toSet())
      putInt("game_score", state.highestGameScore)
      putInt("total_xp", state.totalXp)
      putStringSet("badges", state.unlockedBadgeIds)
      apply()
    }
    _progressState.value = state
  }

  fun markMateriOpened(materiId: Int) {
    val current = _progressState.value
    if (materiId in current.openedMateriIds) return
    val newOpened = current.openedMateriIds + materiId
    val newXp = current.totalXp + 2
    val newBadges = checkBadges(current.copy(openedMateriIds = newOpened, totalXp = newXp))
    saveState(current.copy(openedMateriIds = newOpened, totalXp = newXp, unlockedBadgeIds = newBadges))
  }

  fun markMateriCompleted(materiId: Int) {
    val current = _progressState.value
    if (materiId in current.completedMateriIds) return
    val newCompleted = current.completedMateriIds + materiId
    val newOpened = current.openedMateriIds + materiId
    val newXp = current.totalXp + 5
    val newBadges = checkBadges(current.copy(completedMateriIds = newCompleted, openedMateriIds = newOpened, totalXp = newXp))
    saveState(current.copy(completedMateriIds = newCompleted, openedMateriIds = newOpened, totalXp = newXp, unlockedBadgeIds = newBadges))
  }

  fun markARHotspotViewed(hotspotId: String) {
    val current = _progressState.value
    val newHotspots = current.arHotspotsViewed + hotspotId
    val xpGain = if (hotspotId !in current.arHotspotsViewed) 10 else 0
    val newXp = current.totalXp + xpGain
    val newBadges = checkBadges(current.copy(arHotspotsViewed = newHotspots, totalXp = newXp))
    saveState(current.copy(arHotspotsViewed = newHotspots, totalXp = newXp, unlockedBadgeIds = newBadges))
  }

  fun markARQuizCompleted() {
    val current = _progressState.value
    if (current.arQuizCompleted) return
    val newXp = current.totalXp + 10
    val updated = current.copy(arQuizCompleted = true, totalXp = newXp)
    val newBadges = checkBadges(updated)
    saveState(updated.copy(unlockedBadgeIds = newBadges))
  }

  fun recordCalculatorUsed() {
    val current = _progressState.value
    val firstTime = current.calculatorUsedCount == 0
    val newCount = current.calculatorUsedCount + 1
    val newXp = if (firstTime) current.totalXp + 5 else current.totalXp
    val updated = current.copy(calculatorUsedCount = newCount, totalXp = newXp)
    val newBadges = checkBadges(updated)
    saveState(updated.copy(unlockedBadgeIds = newBadges))
  }

  fun recordChatbotUsed() {
    val current = _progressState.value
    val firstTime = current.chatbotInteractionsCount == 0
    val newCount = current.chatbotInteractionsCount + 1
    val newXp = if (firstTime) current.totalXp + 2 else current.totalXp
    val updated = current.copy(chatbotInteractionsCount = newCount, totalXp = newXp)
    val newBadges = checkBadges(updated)
    saveState(updated.copy(unlockedBadgeIds = newBadges))
  }

  fun recordGameLevelCompleted(level: Int, score: Int) {
    val current = _progressState.value
    val firstLevel = level !in current.completedGameLevels
    val newLevels = current.completedGameLevels + level
    val levelXp = if (firstLevel) 10 else 0
    val allCompletedXp = if (newLevels.size == 4 && current.completedGameLevels.size < 4) 25 else 0
    val newXp = current.totalXp + levelXp + allCompletedXp
    val newHighestScore = maxOf(current.highestGameScore, score)
    val updated = current.copy(
      completedGameLevels = newLevels,
      highestGameScore = newHighestScore,
      totalXp = newXp
    )
    val newBadges = checkBadges(updated)
    saveState(updated.copy(unlockedBadgeIds = newBadges))
  }

  private fun checkBadges(state: KiperProgressState): Set<String> {
    val badges = state.unlockedBadgeIds.toMutableSet()
    if (state.completedMateriIds.size >= 7 || state.openedMateriIds.size >= 7) {
      badges.add("badge_materi")
    }
    if (state.arHotspotsViewed.size >= 4 || state.arQuizCompleted) {
      badges.add("badge_ar")
    }
    if (state.chatbotInteractionsCount >= 1) {
      badges.add("badge_ai")
    }
    if (state.calculatorUsedCount >= 1) {
      badges.add("badge_shu")
    }
    if (state.completedGameLevels.size >= 4) {
      badges.add("badge_game")
    }
    return badges
  }

  fun resetProgress() {
    prefs.edit().clear().apply()
    _progressState.value = KiperProgressState()
  }
}
