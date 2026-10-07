package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppKnowledge
import com.example.model.ARCoopType
import com.example.model.ARHotspot
import com.example.ui.theme.*
import com.example.viewmodel.KiperViewModel

@Composable
fun ARScreen(
  viewModel: KiperViewModel,
  onOpenAIWithQuestion: (String) -> Unit
) {
  val isScanning by viewModel.isScanning.collectAsState()
  val isDetected by viewModel.isObjectDetected.collectAsState()
  val selectedHotspot by viewModel.selectedHotspot.collectAsState()
  val selectedCoopType by viewModel.selectedCoopType.collectAsState()
  val arQuizAnswer by viewModel.arQuizAnswer.collectAsState()
  val arQuizFeedback by viewModel.arQuizFeedback.collectAsState()

  var showInstructionsDialog by remember { mutableStateOf(false) }

  val infiniteTransition = rememberInfiniteTransition(label = "scanLine")
  val scanProgress by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(2000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "scanProgress"
  )

  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    // Header
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "KIPER AR",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "Eksplorasi Koperasi Interaktif",
              fontSize = 12.sp,
              color = Color.White.copy(alpha = 0.9f)
            )
          }
          IconButton(onClick = { viewModel.startARScan() }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Scan Ulang", tint = Color.White)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 3 Action Tabs: [ MULAI AR ] [ MODE SIMULASI ] [ CARA MENGGUNAKAN ]
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Button(
        onClick = { viewModel.startARScan() },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
        modifier = Modifier.weight(1f).testTag("btn_ar_start")
      ) {
        Text("MULAI AR", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }
      OutlinedButton(
        onClick = { viewModel.startARScan() },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
        modifier = Modifier.weight(1f).testTag("btn_ar_simulasi")
      ) {
        Text("SIMULASI", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }
      OutlinedButton(
        onClick = { showInstructionsDialog = true },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
        modifier = Modifier.weight(1.2f).testTag("btn_ar_petunjuk")
      ) {
        Text("PETUNJUK", fontSize = 11.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Interactive AR Viewfinder Simulation Box
    Card(
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF1E2825)),
      elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(340.dp)
    ) {
      Box(modifier = Modifier.fillMaxSize()) {
        // Grid Viewfinder Overlay
        Canvas(modifier = Modifier.fillMaxSize()) {
          val stroke = 1.dp.toPx()
          val color = Color(0x334DB6AC)
          // 3x3 camera rule-of-thirds grid
          drawLine(color, Offset(size.width / 3f, 0f), Offset(size.width / 3f, size.height), stroke)
          drawLine(color, Offset(size.width * 2f / 3f, 0f), Offset(size.width * 2f / 3f, size.height), stroke)
          drawLine(color, Offset(0f, size.height / 3f), Offset(size.width, size.height / 3f), stroke)
          drawLine(color, Offset(0f, size.height * 2f / 3f), Offset(size.width, size.height * 2f / 3f), stroke)
        }

        // Live Scanning line animation when isScanning is true
        if (isScanning) {
          Canvas(modifier = Modifier.fillMaxSize()) {
            val y = size.height * scanProgress
            drawLine(
              color = TurquoiseLight,
              start = Offset(0f, y),
              end = Offset(size.width, y),
              strokeWidth = 3.dp.toPx()
            )
          }
        }

        // Status pill indicator
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = if (isScanning) Color(0xFFFFA000) else ForestGreen,
          modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 12.dp)
        ) {
          Text(
            text = if (isScanning) "🔍 Scanning..." else "Objek ditemukan! ✓",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
          )
        }

        // 3D/Isometric Cooperative Building in Center
        Column(
          modifier = Modifier
            .align(Alignment.Center)
            .padding(bottom = 20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(text = "🏪", fontSize = 84.sp)
          Text(
            text = "KOPERASI SEKOLAH INTERAKTIF",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(alpha = 0.9f),
            letterSpacing = 1.sp
          )
        }

        // 6 Interactive Hotspots placed around the building
        if (isDetected) {
          AppKnowledge.arHotspots.forEach { hotspot ->
            Box(
              modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                  x = (hotspot.xPercent * 300).dp,
                  y = (hotspot.yPercent * 260).dp
                )
            ) {
              HotspotPin(
                hotspot = hotspot,
                onClick = { viewModel.selectHotspot(hotspot) }
              )
            }
          }
        }

        // Tap hint at bottom of viewfinder
        Text(
          text = "Ketuk ikon hotspot untuk melihat detail",
          fontSize = 11.sp,
          color = Color.White.copy(alpha = 0.7f),
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 8.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Popup modal when a hotspot is tapped
    selectedHotspot?.let { hotspot ->
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hotspot_popup_${hotspot.id}")
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(40.dp)
                  .clip(CircleShape)
                  .background(TurquoiseContainer),
                contentAlignment = Alignment.Center
              ) {
                Text(text = hotspot.icon, fontSize = 20.sp)
              }
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = hotspot.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
            }
            IconButton(onClick = { viewModel.selectHotspot(null) }) {
              Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup", tint = TextMuted)
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = hotspot.fullDesc,
            fontSize = 13.sp,
            color = TextSecondary,
            lineHeight = 18.sp
          )

          Spacer(modifier = Modifier.height(14.dp))

          // "🤖 Tanya KIPER AI" button linking directly to chatbot with auto question
          Button(
            onClick = {
              viewModel.selectHotspot(null)
              onOpenAIWithQuestion(hotspot.aiQuestion)
            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5E35B1)),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("btn_ask_ai_from_hotspot")
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(text = "🤖", fontSize = 16.sp)
              Spacer(modifier = Modifier.width(8.dp))
              Text(text = "Tanya KIPER AI: \"${hotspot.name}\"", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    // AR Jenis Koperasi Explorer
    Text(
      text = "Pilih Objek Jenis Koperasi yang Ingin Dieksplorasi:",
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = TextPrimary
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      AppKnowledge.arCoopTypes.forEach { type ->
        val isSelected = selectedCoopType.id == type.id
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelected) TurquoiseContainer else Color.White
          ),
          modifier = Modifier
            .weight(1f)
            .clickable { viewModel.selectedCoopType.value = type }
        ) {
          Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(text = type.icon, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = type.name.replace("Koperasi ", ""),
              fontSize = 10.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              color = if (isSelected) TurquoiseDark else TextPrimary,
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Selected Coop Type Detail Card
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = selectedCoopType.icon, fontSize = 24.sp)
          Spacer(modifier = Modifier.width(10.dp))
          Text(text = selectedCoopType.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TurquoisePrimary)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = "Visual: ${selectedCoopType.visualDesc}", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = selectedCoopType.detail, fontSize = 12.sp, color = TextSecondary, lineHeight = 16.sp)
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // AR Quiz: "Uji Pengamatanmu!"
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🎯", fontSize = 22.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Uji Pengamatanmu! (Quiz AR)",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TurquoisePrimary
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = "Kamu melihat sebuah koperasi yang menjual alat tulis dan kebutuhan siswa di lingkungan sekolah. Jenis koperasi tersebut adalah…",
          fontSize = 13.sp,
          fontWeight = FontWeight.Medium,
          color = TextPrimary,
          lineHeight = 17.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        val options = listOf("A. Koperasi Konsumen", "B. Koperasi Produsen", "C. Koperasi Simpan Pinjam", "D. Koperasi Jasa")
        options.forEachIndexed { index, option ->
          val isSelected = arQuizAnswer == index
          val isCorrect = index == 0

          val bgColor = when {
            arQuizAnswer == null -> SurfaceSoft
            isCorrect -> GreenContainer
            isSelected -> Color(0xFFFFEBEE)
            else -> SurfaceSoft
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = bgColor,
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp)
              .clickable(enabled = arQuizAnswer == null) { viewModel.answerARQuiz(index) }
              .testTag("ar_quiz_opt_$index")
          ) {
            Row(
              modifier = Modifier.padding(12.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = option,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = TextPrimary,
                modifier = Modifier.weight(1f)
              )
              if (arQuizAnswer != null) {
                if (isCorrect) {
                  Text(text = "✓ Benar (+10 XP)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
                } else if (isSelected) {
                  Text(text = "✗ Salah", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                }
              }
            }
          }
        }

        arQuizFeedback?.let { feedback ->
          Spacer(modifier = Modifier.height(10.dp))
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = TurquoiseContainer,
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = feedback,
              fontSize = 12.sp,
              color = OnTurquoiseContainer,
              lineHeight = 16.sp,
              modifier = Modifier.padding(10.dp)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }

  // Instructions Dialog
  if (showInstructionsDialog) {
    AlertDialog(
      onDismissRequest = { showInstructionsDialog = false },
      title = { Text("Cara Menggunakan KIPER AR") },
      text = {
        Text(
          "1. Tekan tombol 'MULAI AR' untuk mengaktifkan pemindaian.\n" +
          "2. Tunggu 1–2 detik hingga animasi scanning mendeteksi objek koperasi.\n" +
          "3. Ketuk lingkaran hotspot (🏪 Koperasi, 📦 Produk, 👥 Anggota, 💰 Modal, 👨‍💼 Pengurus, 📊 SHU) untuk memunculkan penjelasan.\n" +
          "4. Tekan 'Tanya KIPER AI' dari setiap popup untuk langsung mendiskusikan objek tersebut dengan chatbot!\n" +
          "5. Jawab kuis pengamatan di bagian bawah untuk mendapatkan +10 XP."
        )
      },
      confirmButton = {
        TextButton(onClick = { showInstructionsDialog = false }) {
          Text("Paham")
        }
      }
    )
  }
}

@Composable
private fun HotspotPin(
  hotspot: ARHotspot,
  onClick: () -> Unit
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pinPulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.9f,
    targetValue = 1.15f,
    animationSpec = infiniteRepeatable(
      animation = tween(1000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseScale"
  )

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .clickable { onClick() }
      .testTag("hotspot_pin_${hotspot.id}")
  ) {
    Box(
      modifier = Modifier
        .size(34.dp)
        .clip(CircleShape)
        .background(GoldPrimary)
        .border(2.dp, Color.White, CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Text(text = hotspot.icon, fontSize = 16.sp)
    }
    Surface(
      shape = RoundedCornerShape(6.dp),
      color = Color.Black.copy(alpha = 0.75f),
      modifier = Modifier.padding(top = 2.dp)
    ) {
      Text(
        text = hotspot.name,
        fontSize = 8.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
      )
    }
  }
}
