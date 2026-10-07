package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KiperScreen
import com.example.ui.theme.*
import com.example.viewmodel.KiperViewModel

@Composable
fun GameScreen(
  viewModel: KiperViewModel
) {
  val currentLevel by viewModel.currentGameLevel.collectAsState()
  val questionIndex by viewModel.currentQuestionIndex.collectAsState()
  val selectedOption by viewModel.selectedGameOption.collectAsState()
  val isAnswerSubmitted by viewModel.isGameAnswerSubmitted.collectAsState()
  val feedback by viewModel.gameAnswerFeedback.collectAsState()
  val totalScore by viewModel.totalGameScore.collectAsState()

  val questions = viewModel.getQuestionsForLevel(currentLevel)
  val currentQuestion = questions.getOrNull(questionIndex) ?: return

  val levelNames = listOf(
    "Level 1: Kenali Koperasi",
    "Level 2: Koperasi Sekolah",
    "Level 3: Kelola Koperasi",
    "Level 4: Tantangan SHU"
  )

  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    // Header Bar with Level and Score
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = levelNames.getOrElse(currentLevel - 1) { "Level $currentLevel" },
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = "Soal ${questionIndex + 1} dari ${questions.size}",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.85f)
          )
        }

        Surface(
          shape = RoundedCornerShape(12.dp),
          color = GoldPrimary
        ) {
          Text(
            text = "⭐ $totalScore Poin",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3E2723),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Level Progress Indicators (4 Level Dots)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      (1..4).forEach { lvl ->
        val isCurrent = lvl == currentLevel
        val isDone = lvl < currentLevel
        Box(
          modifier = Modifier
            .weight(1f)
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(
              when {
                isDone -> ForestGreen
                isCurrent -> GoldPrimary
                else -> OutlineSoft
              }
            )
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Question Card
    Card(
      shape = RoundedCornerShape(22.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Text(
          text = currentQuestion.question,
          fontSize = 14.sp,
          fontWeight = FontWeight.SemiBold,
          color = TextPrimary,
          lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Options List
        currentQuestion.options.forEachIndexed { index, option ->
          val isSelected = selectedOption == index
          val isCorrect = index == currentQuestion.correctIndex

          val backgroundColor = when {
            !isAnswerSubmitted -> if (isSelected) TurquoiseContainer else SurfaceSoft
            isCorrect -> GreenContainer
            isSelected -> Color(0xFFFFEBEE)
            else -> SurfaceSoft
          }

          val borderColor = when {
            !isAnswerSubmitted -> if (isSelected) TurquoisePrimary else Color.Transparent
            isCorrect -> ForestGreen
            isSelected -> ErrorRed
            else -> Color.Transparent
          }

          Surface(
            shape = RoundedCornerShape(14.dp),
            color = backgroundColor,
            border = if (borderColor != Color.Transparent) {
              androidx.compose.foundation.BorderStroke(2.dp, borderColor)
            } else null,
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 5.dp)
              .clickable(enabled = !isAnswerSubmitted) {
                viewModel.submitGameAnswer(index)
              }
              .testTag("game_opt_$index")
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "${('A' + index)}.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = option,
                fontSize = 13.sp,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
              )

              if (isAnswerSubmitted) {
                if (isCorrect) {
                  Text(text = "✓ Benar", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
                } else if (isSelected) {
                  Text(text = "✗ Salah", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                }
              }
            }
          }
        }

        // Educational Feedback message
        AnimatedVisibility(visible = isAnswerSubmitted && feedback != null) {
          Column(modifier = Modifier.padding(top = 14.dp)) {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(
                containerColor = if (selectedOption == currentQuestion.correctIndex) GreenContainer else Color(0xFFFFF3E0)
              ),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = feedback.orEmpty(),
                  fontSize = 12.sp,
                  color = TextPrimary,
                  lineHeight = 16.sp
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Next Question Button
    if (isAnswerSubmitted) {
      Button(
        onClick = { viewModel.nextGameQuestion() },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("game_next_question_btn")
      ) {
        Text(
          text = if (questionIndex + 1 < questions.size) "Soal Berikutnya →" else "Selesaikan Level Ini →",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }
}

@Composable
fun GameResultScreen(
  result: KiperScreen.GameResult,
  onPlayAgain: () -> Unit,
  onGoHome: () -> Unit
) {
  val (categoryTitle, categoryIcon, categoryDesc) = when {
    result.score >= 90 -> Triple("Ahli Koperasi", "🏆", "Luar biasa! Kamu sangat menguasai seluruh konsep, asas, dan perhitungan SHU koperasi.")
    result.score >= 75 -> Triple("Cerdas Berkoperasi", "🥇", "Hebat! Pemahamanmu tentang koperasi sudah sangat baik dan siap diamalkan.")
    result.score >= 60 -> Triple("Pejuang Koperasi", "🥈", "Bagus! Kamu sudah memahami konsep dasar koperasi dengan cukup baik.")
    else -> Triple("Yuk Belajar Lagi", "📚", "Jangan patah semangat! Baca kembali materi di menu KIPER dan coba tantangan ini lagi.")
  }

  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(16.dp))

    // Celebration Trophy Avatar
    Box(
      modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .background(GoldContainer),
      contentAlignment = Alignment.Center
    ) {
      Text(text = categoryIcon, fontSize = 56.sp)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "SELAMAT! 🎉",
      fontSize = 26.sp,
      fontWeight = FontWeight.ExtraBold,
      color = TurquoisePrimary
    )

    Text(
      text = "Kamu berhasil menyelesaikan tantangan permainan!",
      fontSize = 13.sp,
      color = TextSecondary,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Result Card
    Card(
      shape = RoundedCornerShape(22.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = TurquoiseContainer
        ) {
          Text(
            text = "Kategori: $categoryTitle",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = OnTurquoiseContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
          text = "${result.score}",
          fontSize = 52.sp,
          fontWeight = FontWeight.ExtraBold,
          color = ForestGreen
        )
        Text(
          text = "TOTAL SKOR (DARI 100)",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          color = TextMuted,
          letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = OutlineSoft)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceEvenly
        ) {
          ResultStat(label = "Jawaban Benar", value = "${result.correctCount}", icon = "✓", color = ForestGreen)
          ResultStat(label = "Jawaban Salah", value = "${result.totalQuestions - result.correctCount}", icon = "✗", color = ErrorRed)
          ResultStat(label = "Level Tertinggi", value = "Level ${result.highestLevel}", icon = "⭐", color = GoldDark)
        }

        Spacer(modifier = Modifier.height(14.dp))
        Text(
          text = categoryDesc,
          fontSize = 12.sp,
          color = TextSecondary,
          textAlign = TextAlign.Center,
          lineHeight = 16.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // Action Buttons
    Button(
      onClick = onPlayAgain,
      shape = RoundedCornerShape(18.dp),
      colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("btn_repeat_game")
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Ulangi", tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Ulangi Permainan", fontSize = 14.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedButton(
      onClick = onGoHome,
      shape = RoundedCornerShape(18.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("btn_back_home_from_game")
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "Beranda", tint = TextPrimary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Kembali ke Beranda", fontSize = 14.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
private fun ResultStat(label: String, value: String, icon: String, color: Color) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = icon, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = color)
    Spacer(modifier = Modifier.height(2.dp))
    Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
    Text(text = label, fontSize = 10.sp, color = TextMuted)
  }
}
