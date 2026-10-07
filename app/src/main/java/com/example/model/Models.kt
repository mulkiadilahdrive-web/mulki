package com.example.model

sealed class KiperScreen {
  object Splash : KiperScreen()
  object Home : KiperScreen()
  object MateriList : KiperScreen()
  data class MateriDetail(val materiId: Int) : KiperScreen()
  object KiperAR : KiperScreen()
  object SHUTheory : KiperScreen()
  object SHUCalculator : KiperScreen()
  object KoperasiSekolah : KiperScreen()
  object TokohKoperasi : KiperScreen()
  object DaftarPustaka : KiperScreen()
  object Petunjuk : KiperScreen()
  object Kompetensi : KiperScreen()
  data class KiperAI(val initialPrompt: String? = null) : KiperScreen()
  object Permainan : KiperScreen()
  data class GameResult(
    val score: Int,
    val correctCount: Int,
    val totalQuestions: Int,
    val highestLevel: Int
  ) : KiperScreen()
  object ProgressBelajar : KiperScreen()
}

data class MateriItem(
  val id: Int,
  val number: Int,
  val icon: String,
  val title: String,
  val subtitle: String,
  val description: String,
  val studentDefinition: String,
  val examples: List<Pair<String, String>>,
  val reflectionQuestion: String,
  val interactiveCards: List<InteractiveCard> = emptyList(),
  val miniQuiz: MiniQuiz? = null
)

data class InteractiveCard(
  val title: String,
  val icon: String,
  val subtitle: String,
  val description: String,
  val example: String
)

data class MiniQuiz(
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val explanation: String
)

data class ARHotspot(
  val id: String,
  val name: String,
  val icon: String,
  val shortDesc: String,
  val fullDesc: String,
  val aiQuestion: String,
  val xPercent: Float,
  val yPercent: Float
)

data class ARCoopType(
  val id: String,
  val name: String,
  val icon: String,
  val visualDesc: String,
  val detail: String
)

data class ChatMessage(
  val id: String,
  val text: String,
  val isUser: Boolean,
  val timestamp: Long = System.currentTimeMillis(),
  val actionLabel: String? = null,
  val actionScreen: KiperScreen? = null
)

data class BadgeItem(
  val id: String,
  val name: String,
  val icon: String,
  val description: String,
  val isUnlocked: Boolean = false
)

data class ProductItem(
  val id: String,
  val name: String,
  val icon: String,
  val category: String,
  val priceDesc: String,
  val reason: String
)

data class GameQuestion(
  val id: String,
  val level: Int,
  val question: String,
  val options: List<String>,
  val correctIndex: Int,
  val points: Int = 5,
  val explanation: String,
  val calculationData: CalculationChallengeData? = null
)

data class CalculationChallengeData(
  val totalSHU: Long,
  val modalPct: Int,
  val usahaPct: Int,
  val simpananAnggota: Long,
  val totalSimpanan: Long,
  val transaksiAnggota: Long,
  val totalTransaksi: Long,
  val expectedTotal: Long
)

data class SHUCalculationResult(
  val jasaModal: Double,
  val jasaUsaha: Double,
  val totalSHU: Double,
  val isValid: Boolean,
  val errorMessage: String? = null,
  val step1Text: String = "",
  val step2Text: String = "",
  val step3Text: String = ""
)
