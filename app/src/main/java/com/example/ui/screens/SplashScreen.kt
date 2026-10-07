package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun SplashScreen(
  onStartClick: () -> Unit
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.96f,
    targetValue = 1.04f,
    animationSpec = infiniteRepeatable(
      animation = tween(1400, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseScale"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            CreamBackground,
            Color(0xFFE8F5E9),
            TurquoiseContainer
          )
        )
      )
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 24.dp, vertical = 32.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Spacer(modifier = Modifier.height(20.dp))

      // Header Brand
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = TurquoisePrimary,
          shadowElevation = 6.dp,
          modifier = Modifier.padding(bottom = 12.dp)
        ) {
          Text(
            text = "EKONOMI SMA/SMK",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
          )
        }

        Text(
          text = "KIPER",
          fontSize = 44.sp,
          fontWeight = FontWeight.ExtraBold,
          color = TurquoisePrimary,
          letterSpacing = 2.sp
        )

        Text(
          text = "Ekonomi Koperasi",
          fontSize = 22.sp,
          fontWeight = FontWeight.Bold,
          color = ForestGreen
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "“Belajar • Eksplorasi • Praktik • Bersama Koperasi”",
          fontSize = 13.sp,
          color = TextSecondary,
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Medium
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      // 2D Cartoon Educational Center Visual Card
      Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .scale(pulseScale)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Decorative Cartoon Illustration of Cooperative Building & Students
          Box(
            modifier = Modifier
              .size(120.dp)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  colors = listOf(GoldLight, TurquoiseContainer)
                )
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "🏪", fontSize = 60.sp)
          }

          Spacer(modifier = Modifier.height(16.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            CartoonBadge(icon = "👨‍🎓", label = "Siswa")
            CartoonBadge(icon = "📚", label = "Buku")
            CartoonBadge(icon = "🌱", label = "Gotong Royong")
            CartoonBadge(icon = "👩‍🎓", label = "Siswi")
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "KIPER = Koperasi dan Pembelajaran Ekonomi.\nMedia edukasi interaktif untuk memahami konsep koperasi, simulasi AR, perhitungan SHU, dan permainan seru!",
            fontSize = 12.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(32.dp))

      // Primary Start Button
      Button(
        onClick = onStartClick,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = TurquoisePrimary,
          contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .testTag("splash_start_button")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "MULAI",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Mulai",
            tint = Color.White
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = "Kurikulum Merdeka • Pembelajaran Interaktif",
        fontSize = 11.sp,
        color = TextMuted,
        textAlign = TextAlign.Center
      )
    }
  }
}

@Composable
private fun CartoonBadge(icon: String, label: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Box(
      modifier = Modifier
        .size(46.dp)
        .clip(CircleShape)
        .background(SurfaceSoft),
      contentAlignment = Alignment.Center
    ) {
      Text(text = icon, fontSize = 22.sp)
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = label,
      fontSize = 10.sp,
      fontWeight = FontWeight.SemiBold,
      color = TextSecondary
    )
  }
}
