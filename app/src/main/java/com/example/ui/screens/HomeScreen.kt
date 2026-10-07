package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.KiperProgressState
import com.example.model.KiperScreen
import com.example.ui.theme.*

@Composable
fun HomeScreen(
  progress: KiperProgressState,
  onNavigate: (KiperScreen) -> Unit
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    // Sapaan Header Card
    Card(
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Halo, Siswa! 👋",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Ayo jelajahi dunia koperasi dengan cara yang menyenangkan!",
            fontSize = 13.sp,
            color = Color.White.copy(alpha = 0.9f),
            lineHeight = 18.sp
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Box(
          modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f)),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "🎒", fontSize = 28.sp)
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Motivational section
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(14.dp))
        .background(GoldLight.copy(alpha = 0.5f))
        .padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(text = "💡", fontSize = 18.sp)
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Belajar hari ini untuk masa depan yang lebih baik!",
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF5D4037)
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 4 Main Feature Cards
    Text(
      text = "Menu Utama Pembelajaran",
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold,
      color = TextPrimary
    )
    Spacer(modifier = Modifier.height(12.dp))

    // 2x2 Grid using Rows and Columns
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      MainActionCard(
        icon = "📖",
        title = "MATERI KOPERASI",
        subtitle = "Pelajari konsep koperasi.",
        badge = "${progress.completedMateriIds.size}/7 Selesai",
        containerColor = Color(0xFFE8F5E9),
        accentColor = ForestGreen,
        tag = "home_card_materi",
        modifier = Modifier.weight(1f),
        onClick = { onNavigate(KiperScreen.MateriList) }
      )
      MainActionCard(
        icon = "📱",
        title = "KIPER AR",
        subtitle = "Jelajahi koperasi secara interaktif.",
        badge = "${progress.arHotspotsViewed.size}/4 Objek",
        containerColor = Color(0xFFE0F2F1),
        accentColor = TurquoisePrimary,
        tag = "home_card_ar",
        modifier = Modifier.weight(1f),
        onClick = { onNavigate(KiperScreen.KiperAR) }
      )
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      MainActionCard(
        icon = "🤖",
        title = "KIPER AI",
        subtitle = "Tanyakan apa saja tentang koperasi.",
        badge = "${progress.chatbotInteractionsCount} Tanya",
        containerColor = Color(0xFFEDE7F6),
        accentColor = Color(0xFF5E35B1),
        tag = "home_card_ai",
        modifier = Modifier.weight(1f),
        onClick = { onNavigate(KiperScreen.KiperAI()) }
      )
      MainActionCard(
        icon = "🧮",
        title = "KALKULATOR SHU",
        subtitle = "Hitung SHU dengan mudah.",
        badge = "Interaktif",
        containerColor = Color(0xFFFFF8E1),
        accentColor = GoldDark,
        tag = "home_card_calc",
        modifier = Modifier.weight(1f),
        onClick = { onNavigate(KiperScreen.SHUCalculator) }
      )
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Bonus 5th Game Card banner
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
      modifier = Modifier
        .fillMaxWidth()
        .clickable { onNavigate(KiperScreen.Permainan) }
        .testTag("home_card_game")
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(Color.White),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "🎮", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "PERMAINAN KOPERASI (4 LEVEL)",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE65100)
          )
          Text(
            text = "Uji pengetahuanmu dan kumpulkan hingga 100 poin!",
            fontSize = 11.sp,
            color = TextSecondary
          )
        }
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = "Mainkan",
          tint = Color(0xFFE65100)
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Real-time Progress Belajar Overview Card
    Card(
      shape = RoundedCornerShape(22.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      modifier = Modifier
        .fillMaxWidth()
        .clickable { onNavigate(KiperScreen.ProgressBelajar) }
        .testTag("home_progress_card")
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "PROGRESS BELAJAR",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = GoldContainer
          ) {
            Text(
              text = "⚡ ${progress.totalXp} XP",
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = GoldDark,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Metrics Grid
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          ProgressPill(label = "Materi", value = "${progress.completedMateriIds.size}/7", icon = "📖")
          ProgressPill(label = "AR", value = "${minOf(progress.arHotspotsViewed.size, 4)}/4", icon = "📱")
          ProgressPill(label = "Kuis", value = "${if (progress.arQuizCompleted) 1 else 0}/1", icon = "🎯")
          ProgressPill(label = "Game", value = "${progress.completedGameLevels.size}/4", icon = "🎮")
          ProgressPill(label = "Badge", value = "${progress.unlockedBadgeIds.size}/5", icon = "🏆")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Linear Progress Bar
        val totalProgressPercent = ((progress.completedMateriIds.size + progress.completedGameLevels.size) / 11f).coerceIn(0f, 1f)
        LinearProgressIndicator(
          progress = { totalProgressPercent },
          color = TurquoisePrimary,
          trackColor = TurquoiseContainer,
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp))
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Continue Learning Action Button
    Button(
      onClick = { onNavigate(KiperScreen.MateriList) },
      shape = RoundedCornerShape(20.dp),
      colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("home_continue_button")
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = "Lanjutkan Belajar",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowForward,
          contentDescription = "Lanjutkan",
          tint = Color.White
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
private fun MainActionCard(
  icon: String,
  title: String,
  subtitle: String,
  badge: String,
  containerColor: Color,
  accentColor: Color,
  tag: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = containerColor),
    modifier = modifier
      .clickable { onClick() }
      .testTag(tag)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color.White),
          contentAlignment = Alignment.Center
        ) {
          Text(text = icon, fontSize = 22.sp)
        }
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Color.White.copy(alpha = 0.85f)
        ) {
          Text(
            text = badge,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = accentColor,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = title,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = accentColor
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = subtitle,
        fontSize = 11.sp,
        color = TextSecondary,
        lineHeight = 14.sp
      )
    }
  }
}

@Composable
private fun ProgressPill(label: String, value: String, icon: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = icon, fontSize = 16.sp)
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = value,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
      color = TextPrimary
    )
    Text(
      text = label,
      fontSize = 10.sp,
      color = TextMuted
    )
  }
}
