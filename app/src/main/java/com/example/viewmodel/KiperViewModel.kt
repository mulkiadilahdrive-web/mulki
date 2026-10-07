package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class KiperViewModel(application: Application) : AndroidViewModel(application) {
  val repository = KiperRepository(application.applicationContext)
  val progressState = repository.progressState

  // Navigation
  private val _screenStack = MutableStateFlow<List<KiperScreen>>(listOf(KiperScreen.Splash))
  val currentScreen: StateFlow<KiperScreen> = _screenStack.map { it.lastOrNull() ?: KiperScreen.Home }
    .stateIn(viewModelScope, SharingStarted.Eagerly, KiperScreen.Splash)

  private val _isDrawerOpen = MutableStateFlow(false)
  val isDrawerOpen = _isDrawerOpen.asStateFlow()

  fun navigateTo(screen: KiperScreen) {
    _isDrawerOpen.value = false
    val current = _screenStack.value
    if (screen is KiperScreen.Splash || screen is KiperScreen.Home) {
      _screenStack.value = listOf(screen)
    } else {
      _screenStack.value = current + screen
    }

    if (screen is KiperScreen.MateriDetail) {
      repository.markMateriOpened(screen.materiId)
    } else if (screen is KiperScreen.KiperAI && screen.initialPrompt != null) {
      sendChatMessage(screen.initialPrompt)
    }
  }

  fun navigateBack(): Boolean {
    val current = _screenStack.value
    if (current.size > 1) {
      _screenStack.value = current.dropLast(1)
      return true
    }
    return false
  }

  fun toggleDrawer(open: Boolean? = null) {
    _isDrawerOpen.value = open ?: !_isDrawerOpen.value
  }

  // --- KALKULATOR SHU ---
  val totalSHUInput = MutableStateFlow("10000000")
  val modalPctInput = MutableStateFlow("40")
  val usahaPctInput = MutableStateFlow("30")
  val simpananAnggotaInput = MutableStateFlow("2000000")
  val totalSimpananInput = MutableStateFlow("20000000")
  val transaksiAnggotaInput = MutableStateFlow("5000000")
  val totalTransaksiInput = MutableStateFlow("40000000")

  val showSteps = MutableStateFlow(false)

  private val _shuResult = MutableStateFlow(calculateSHUInternal())
  val shuResult = _shuResult.asStateFlow()

  fun updateSHUInput(field: String, value: String) {
    val clean = value.filter { it.isDigit() }
    when (field) {
      "totalSHU" -> totalSHUInput.value = clean
      "modalPct" -> modalPctInput.value = clean
      "usahaPct" -> usahaPctInput.value = clean
      "simpananAnggota" -> simpananAnggotaInput.value = clean
      "totalSimpanan" -> totalSimpananInput.value = clean
      "transaksiAnggota" -> transaksiAnggotaInput.value = clean
      "totalTransaksi" -> totalTransaksiInput.value = clean
    }
    _shuResult.value = calculateSHUInternal()
  }

  fun triggerCalculateSHU() {
    val res = calculateSHUInternal()
    _shuResult.value = res
    if (res.isValid) {
      repository.recordCalculatorUsed()
    }
  }

  fun toggleSteps() {
    showSteps.value = !showSteps.value
  }

  private fun calculateSHUInternal(): SHUCalculationResult {
    val totalSHU = totalSHUInput.value.toDoubleOrNull()
    val modalPct = modalPctInput.value.toDoubleOrNull()
    val usahaPct = usahaPctInput.value.toDoubleOrNull()
    val simpanan = simpananAnggotaInput.value.toDoubleOrNull()
    val totalSimp = totalSimpananInput.value.toDoubleOrNull()
    val transaksi = transaksiAnggotaInput.value.toDoubleOrNull()
    val totalTrans = totalTransaksiInput.value.toDoubleOrNull()

    if (totalSHU == null || modalPct == null || usahaPct == null ||
      simpanan == null || totalSimp == null || transaksi == null || totalTrans == null
    ) {
      return SHUCalculationResult(0.0, 0.0, 0.0, false, "Silakan lengkapi data terlebih dahulu.")
    }

    if (totalSHU < 0 || simpanan < 0 || totalSimp < 0 || transaksi < 0 || totalTrans < 0) {
      return SHUCalculationResult(0.0, 0.0, 0.0, false, "Nilai tidak boleh negatif.")
    }

    if (modalPct < 0 || modalPct > 100 || usahaPct < 0 || usahaPct > 100) {
      return SHUCalculationResult(0.0, 0.0, 0.0, false, "Masukkan persentase antara 0–100%.")
    }

    if (totalSimp == 0.0) {
      return SHUCalculationResult(0.0, 0.0, 0.0, false, "Total simpanan tidak boleh 0.")
    }

    if (totalTrans == 0.0) {
      return SHUCalculationResult(0.0, 0.0, 0.0, false, "Total transaksi tidak boleh 0.")
    }

    val jasaModal = totalSHU * (modalPct / 100.0) * (simpanan / totalSimp)
    val jasaUsaha = totalSHU * (usahaPct / 100.0) * (transaksi / totalTrans)
    val totalSHUAnggota = jasaModal + jasaUsaha

    val s1 = "Jasa Modal = ${formatRupiah(totalSHU)} × ${modalPct.toInt()}% × (${formatRupiah(simpanan)} / ${formatRupiah(totalSimp)})\n= ${formatRupiah(jasaModal)}"
    val s2 = "Jasa Usaha = ${formatRupiah(totalSHU)} × ${usahaPct.toInt()}% × (${formatRupiah(transaksi)} / ${formatRupiah(totalTrans)})\n= ${formatRupiah(jasaUsaha)}"
    val s3 = "Total SHU Anggota = ${formatRupiah(jasaModal)} + ${formatRupiah(jasaUsaha)}\n= ${formatRupiah(totalSHUAnggota)}"

    return SHUCalculationResult(
      jasaModal = jasaModal,
      jasaUsaha = jasaUsaha,
      totalSHU = totalSHUAnggota,
      isValid = true,
      step1Text = s1,
      step2Text = s2,
      step3Text = s3
    )
  }

  // --- KIPER AI (CHATBOT) ---
  private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
    listOf(
      ChatMessage(
        id = "init_1",
        text = "Halo! 👋\n\nAku KIPER AI, teman belajar koperasimu. 🤖\n\nAku siap membantumu memahami materi koperasi, cara menghitung SHU, koperasi sekolah, hingga tokoh Mohammad Hatta.\n\nCoba tanyakan sesuatu!",
        isUser = false
      )
    )
  )
  val chatMessages = _chatMessages.asStateFlow()

  val currentChatInput = MutableStateFlow("")
  val isBotTyping = MutableStateFlow(false)

  fun sendChatMessage(customPrompt: String? = null) {
    val textToSend = (customPrompt ?: currentChatInput.value).trim()
    if (textToSend.isBlank()) return

    if (customPrompt == null) {
      currentChatInput.value = ""
    }

    val userMsg = ChatMessage(id = System.currentTimeMillis().toString(), text = textToSend, isUser = true)
    _chatMessages.value = _chatMessages.value + userMsg
    repository.recordChatbotUsed()

    viewModelScope.launch {
      isBotTyping.value = true
      delay(400) // Realistic tutor response latency
      val botReply = matchChatResponse(textToSend)
      _chatMessages.value = _chatMessages.value + botReply
      isBotTyping.value = false
    }
  }

  private fun matchChatResponse(userQuery: String): ChatMessage {
    val normalized = userQuery.lowercase(Locale.ROOT)

    // Specific trigger for SHU calculation
    if (normalized.contains("hitung shu") || normalized.contains("cara menghitung shu") || normalized.contains("kalkulator")) {
      return ChatMessage(
        id = System.currentTimeMillis().toString(),
        text = "Yuk kita hitung bersama! Kamu dapat memasukkan datamu di Kalkulator SHU.",
        isUser = false,
        actionLabel = "BUKA KALKULATOR SHU",
        actionScreen = KiperScreen.SHUCalculator
      )
    }

    // Match keywords against local knowledge base
    for (entry in AppKnowledge.knowledgeBase) {
      for (kw in entry.keywords) {
        if (normalized.contains(kw) || kw.contains(normalized)) {
          return ChatMessage(
            id = System.currentTimeMillis().toString(),
            text = entry.response,
            isUser = false,
            actionLabel = entry.actionLabel,
            actionScreen = entry.actionScreen
          )
        }
      }
    }

    // Default fallback guidance
    return ChatMessage(
      id = System.currentTimeMillis().toString(),
      text = "Maaf, aku belum memahami pertanyaan itu. 😊\n\nCoba tanyakan tentang:\n• Koperasi & pengertiannya\n• Tujuan dan asas kekeluargaan\n• SHU & cara menghitungnya\n• Modal & perangkat koperasi\n• Jenis-jenis koperasi\n• Koperasi sekolah\n• Tokoh Mohammad Hatta",
      isUser = false
    )
  }

  // --- KIPER AR ---
  val isSimulatedAR = MutableStateFlow(true)
  val selectedHotspot = MutableStateFlow<ARHotspot?>(null)
  val selectedCoopType = MutableStateFlow<ARCoopType>(AppKnowledge.arCoopTypes.first())
  val isScanning = MutableStateFlow(false)
  val isObjectDetected = MutableStateFlow(true)

  val arQuizAnswer = MutableStateFlow<Int?>(null)
  val arQuizFeedback = MutableStateFlow<String?>(null)

  fun startARScan() {
    viewModelScope.launch {
      isScanning.value = true
      isObjectDetected.value = false
      delay(1200)
      isScanning.value = false
      isObjectDetected.value = true
    }
  }

  fun selectHotspot(hotspot: ARHotspot?) {
    selectedHotspot.value = hotspot
    if (hotspot != null) {
      repository.markARHotspotViewed(hotspot.id)
    }
  }

  fun answerARQuiz(selectedIndex: Int) {
    arQuizAnswer.value = selectedIndex
    if (selectedIndex == 0) { // Option A is Konsumen
      arQuizFeedback.value = "Hebat! 🎉 Jawabanmu benar (+10 XP). Koperasi sekolah yang menjual alat tulis dan kebutuhan siswa adalah contoh Koperasi Konsumen."
      repository.markARQuizCompleted()
    } else {
      arQuizFeedback.value = "Belum tepat. Koperasi yang menjual kebutuhan barang sehari-hari adalah Koperasi Konsumen. Yuk pelajari lagi!"
    }
  }

  // --- PERMAINAN (GAME 4 LEVELS) ---
  val currentGameLevel = MutableStateFlow(1)
  val currentQuestionIndex = MutableStateFlow(0)
  val selectedGameOption = MutableStateFlow<Int?>(null)
  val isGameAnswerSubmitted = MutableStateFlow(false)
  val gameAnswerFeedback = MutableStateFlow<String?>(null)
  val currentLevelScore = MutableStateFlow(0)
  val totalGameScore = MutableStateFlow(0)
  val correctAnswersCount = MutableStateFlow(0)
  val totalQuestionsAnswered = MutableStateFlow(0)

  fun getQuestionsForLevel(level: Int): List<GameQuestion> {
    return when (level) {
      1 -> GameContent.level1Questions
      2 -> GameContent.level2Questions
      3 -> GameContent.level3Questions
      else -> GameContent.level4Questions
    }
  }

  fun submitGameAnswer(optionIndex: Int) {
    if (isGameAnswerSubmitted.value) return
    selectedGameOption.value = optionIndex
    isGameAnswerSubmitted.value = true
    totalQuestionsAnswered.value += 1

    val questions = getQuestionsForLevel(currentGameLevel.value)
    val question = questions.getOrNull(currentQuestionIndex.value) ?: return

    if (optionIndex == question.correctIndex) {
      currentLevelScore.value += question.points
      totalGameScore.value += question.points
      correctAnswersCount.value += 1
      gameAnswerFeedback.value = "Hebat! 🎉 Jawabanmu benar. (+${question.points} Poin)\n${question.explanation}"
    } else {
      gameAnswerFeedback.value = "Belum tepat. 😊\n${question.explanation}"
    }
  }

  fun nextGameQuestion() {
    val questions = getQuestionsForLevel(currentGameLevel.value)
    if (currentQuestionIndex.value + 1 < questions.size) {
      currentQuestionIndex.value += 1
      selectedGameOption.value = null
      isGameAnswerSubmitted.value = false
      gameAnswerFeedback.value = null
    } else {
      // Completed this level!
      repository.recordGameLevelCompleted(currentGameLevel.value, totalGameScore.value)
      if (currentGameLevel.value < 4) {
        currentGameLevel.value += 1
        currentQuestionIndex.value = 0
        selectedGameOption.value = null
        isGameAnswerSubmitted.value = false
        gameAnswerFeedback.value = null
      } else {
        // Game Finished!
        navigateTo(
          KiperScreen.GameResult(
            score = totalGameScore.value,
            correctCount = correctAnswersCount.value,
            totalQuestions = 20,
            highestLevel = 4
          )
        )
      }
    }
  }

  fun restartGame() {
    currentGameLevel.value = 1
    currentQuestionIndex.value = 0
    selectedGameOption.value = null
    isGameAnswerSubmitted.value = false
    gameAnswerFeedback.value = null
    currentLevelScore.value = 0
    totalGameScore.value = 0
    correctAnswersCount.value = 0
    totalQuestionsAnswered.value = 0
    navigateTo(KiperScreen.Permainan)
  }

  // --- MATERI MINI-QUIZ ---
  val materiQuizAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
  val materiQuizChecked = MutableStateFlow<Map<Int, Boolean>>(emptyMap())

  fun answerMateriQuiz(materiId: Int, optionIndex: Int) {
    materiQuizAnswers.value = materiQuizAnswers.value + (materiId to optionIndex)
    materiQuizChecked.value = materiQuizChecked.value + (materiId to true)
  }

  fun markMateriDone(materiId: Int) {
    repository.markMateriCompleted(materiId)
  }

  fun resetAllProgress() {
    repository.resetProgress()
    restartGame()
  }

  companion object {
    fun formatRupiah(amount: Double): String {
      val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
      format.maximumFractionDigits = 0
      return format.format(amount).replace(",00", "").replace("Rp", "Rp ")
    }
  }
}
