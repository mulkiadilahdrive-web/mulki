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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.KiperProgressState
import com.example.data.MateriContent
import com.example.model.InteractiveCard
import com.example.model.MateriItem
import com.example.ui.theme.*

@Composable
fun MateriListScreen(
  progress: KiperProgressState,
  onSelectMateri: (Int) -> Unit
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    // Header Banner
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Text(
          text = "MATERI KOPERASI",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Pahami koperasi melalui materi yang dekat dengan kehidupan sehari-hari.",
          fontSize = 13.sp,
          color = Color.White.copy(alpha = 0.9f),
          lineHeight = 17.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Progress bar summary
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .padding(horizontal = 14.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "Progres: ${progress.completedMateriIds.size} dari 7 Materi Selesai",
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = TextPrimary
      )
      Text(
        text = "${(progress.completedMateriIds.size * 100) / 7}%",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = TurquoisePrimary
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // List of 7 Modules
    MateriContent.allMateri.forEach { materi ->
      val isCompleted = materi.id in progress.completedMateriIds
      MateriCardItem(
        materi = materi,
        isCompleted = isCompleted,
        onOpen = { onSelectMateri(materi.id) }
      )
      Spacer(modifier = Modifier.height(12.dp))
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}

@Composable
private fun MateriCardItem(
  materi: MateriItem,
  isCompleted: Boolean,
  onOpen: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onOpen() }
      .testTag("materi_item_${materi.id}")
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Number & Icon avatar
      Box(
        modifier = Modifier
          .size(50.dp)
          .clip(CircleShape)
          .background(if (isCompleted) GreenContainer else TurquoiseContainer),
        contentAlignment = Alignment.Center
      ) {
        Text(text = materi.icon, fontSize = 24.sp)
      }

      Spacer(modifier = Modifier.width(14.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = if (isCompleted) ForestGreen else TurquoisePrimary
          ) {
            Text(
              text = "MATERI ${materi.number}",
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          if (isCompleted) {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = GreenContainer
            ) {
              Text(
                text = "✓ Selesai",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          } else {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = SurfaceSoft
            ) {
              Text(
                text = "Belum Dipelajari",
                fontSize = 9.sp,
                color = TextMuted,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = materi.title,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = materi.subtitle,
          fontSize = 12.sp,
          color = TextSecondary,
          lineHeight = 15.sp,
          maxLines = 2
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Button(
        onClick = onOpen,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = if (isCompleted) ForestGreen else TurquoisePrimary
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        modifier = Modifier.testTag("materi_open_btn_${materi.id}")
      ) {
        Text(
          text = if (isCompleted) "Buka" else "Pelajari",
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}

@Composable
fun MateriDetailScreen(
  materiId: Int,
  progress: KiperProgressState,
  onMarkCompleted: (Int) -> Unit,
  onOpenAIWithQuestion: (String) -> Unit
) {
  val materi = MateriContent.allMateri.firstOrNull { it.id == materiId } ?: return
  val isCompleted = materi.id in progress.completedMateriIds
  val scrollState = rememberScrollState()

  var reflectionAnswer by remember { mutableStateOf("") }
  var isReflectionSaved by remember { mutableStateOf(false) }

  var selectedQuizOption by remember { mutableStateOf<Int?>(null) }
  var isQuizSubmitted by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    // Header
    Card(
      shape = RoundedCornerShape(22.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = materi.icon, fontSize = 32.sp)
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "MATERI ${materi.number}",
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = GoldLight
            )
            Text(
              text = materi.title,
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Student friendly definition box
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "💡", fontSize = 18.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Bahasa Siswa (Sederhananya...)",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = materi.studentDefinition,
          fontSize = 13.sp,
          color = TextPrimary,
          lineHeight = 18.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Formal definition card
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Definisi Konseptual",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = materi.description,
          fontSize = 13.sp,
          color = TextSecondary,
          lineHeight = 18.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Section "Contoh di Sekitarmu"
    if (materi.examples.isNotEmpty()) {
      Text(
        text = "Contoh di Sekitarmu",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      )
      Spacer(modifier = Modifier.height(8.dp))

      materi.examples.forEach { (title, desc) ->
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = Color.White),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
          ) {
            Text(text = "📌", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TurquoisePrimary
              )
              Spacer(modifier = Modifier.height(3.dp))
              Text(
                text = desc,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 16.sp
              )
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    // Interactive Expandable Cards (Principles, Emblems, Capital, Types)
    if (materi.interactiveCards.isNotEmpty()) {
      Text(
        text = "Ketuk Kartu untuk Eksplorasi Interaktif",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      )
      Spacer(modifier = Modifier.height(8.dp))

      materi.interactiveCards.forEach { card ->
        InteractiveDetailCard(card = card)
        Spacer(modifier = Modifier.height(8.dp))
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    // Reflection Question Card
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🤔", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Pertanyaan Refleksi",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = materi.reflectionQuestion,
          fontSize = 13.sp,
          fontWeight = FontWeight.Medium,
          color = TextPrimary,
          lineHeight = 17.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
          value = reflectionAnswer,
          onValueChange = { reflectionAnswer = it },
          placeholder = { Text("Tuliskan pendapatmu di sini...", fontSize = 12.sp) },
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            focusedPlaceholderColor = TextMuted,
            unfocusedPlaceholderColor = TextMuted,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = TurquoisePrimary,
            unfocusedBorderColor = OutlineSoft,
            cursorColor = TurquoisePrimary
          ),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("reflection_input_${materi.id}")
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
          onClick = { isReflectionSaved = true },
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(containerColor = GoldDark),
          modifier = Modifier.testTag("reflection_save_btn_${materi.id}")
        ) {
          Text("Simpan Jawaban", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        if (isReflectionSaved) {
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "✓ Jawaban refleksimu tersimpan dengan baik! Bagus sekali telah merenungkan konsep ini.",
            fontSize = 11.sp,
            color = ForestGreen,
            fontWeight = FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Mini Quiz Section
    materi.miniQuiz?.let { quiz ->
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "🎯", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Mini Quiz Materi",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = TurquoisePrimary
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = quiz.question,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )

          Spacer(modifier = Modifier.height(12.dp))

          quiz.options.forEachIndexed { index, option ->
            val isSelected = selectedQuizOption == index
            val isCorrect = index == quiz.correctIndex

            val optionColor = when {
              !isQuizSubmitted -> if (isSelected) TurquoiseContainer else SurfaceSoft
              isCorrect -> GreenContainer
              isSelected -> Color(0xFFFFEBEE)
              else -> SurfaceSoft
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = optionColor,
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable(enabled = !isQuizSubmitted) { selectedQuizOption = index }
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "${('A' + index)}. ",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = TextPrimary
                )
                Text(
                  text = option,
                  fontSize = 12.sp,
                  color = TextPrimary,
                  modifier = Modifier.weight(1f)
                )
                if (isQuizSubmitted) {
                  if (isCorrect) {
                    Text(text = "✓ Benar", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
                  } else if (isSelected) {
                    Text(text = "✗ Salah", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                  }
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          if (!isQuizSubmitted) {
            Button(
              onClick = {
                if (selectedQuizOption != null) {
                  isQuizSubmitted = true
                }
              },
              enabled = selectedQuizOption != null,
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
              modifier = Modifier.fillMaxWidth().testTag("materi_quiz_submit_${materi.id}")
            ) {
              Text("Periksa Jawaban", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
          } else {
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = TurquoiseContainer),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = if (selectedQuizOption == quiz.correctIndex) "Hebat! 🎉 Jawabanmu benar." else "Belum tepat. Yuk cermati penjelasannya:",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = OnTurquoiseContainer
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = quiz.explanation,
                  fontSize = 11.sp,
                  color = TextPrimary,
                  lineHeight = 15.sp
                )
              }
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    // Ask KIPER AI about this topic button
    OutlinedButton(
      onClick = { onOpenAIWithQuestion("Jelaskan lebih detail tentang ${materi.title}") },
      shape = RoundedCornerShape(16.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF5E35B1)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "🤖", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Tanya KIPER AI tentang ${materi.title}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // "Sudah Paham ✓" action button
    Button(
      onClick = { onMarkCompleted(materi.id) },
      shape = RoundedCornerShape(18.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = if (isCompleted) ForestGreen else TurquoisePrimary
      ),
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("materi_mark_completed_btn")
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "Selesai",
          tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = if (isCompleted) "Materi Selesai (Sudah Paham ✓)" else "Sudah Paham ✓ (+5 XP)",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }
}

@Composable
private fun InteractiveDetailCard(card: InteractiveCard) {
  var isExpanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (isExpanded) TurquoiseContainer else Color.White
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = Modifier
      .fillMaxWidth()
      .clickable { isExpanded = !isExpanded }
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = card.icon, fontSize = 22.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = card.title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Text(
            text = card.subtitle,
            fontSize = 11.sp,
            color = TurquoisePrimary,
            fontWeight = FontWeight.Medium
          )
        }
        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = "Buka",
          tint = TextSecondary
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(modifier = Modifier.padding(top = 10.dp)) {
          Divider(color = OutlineSoft.copy(alpha = 0.5f))
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = card.description,
            fontSize = 12.sp,
            color = TextPrimary,
            lineHeight = 16.sp
          )
          Spacer(modifier = Modifier.height(6.dp))
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White.copy(alpha = 0.7f)
          ) {
            Text(
              text = "Contoh: ${card.example}",
              fontSize = 11.sp,
              color = ForestGreen,
              fontWeight = FontWeight.Medium,
              modifier = Modifier.padding(8.dp)
            )
          }
        }
      }
    }
  }
}
