package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.model.ChatMessage
import com.example.model.KiperScreen
import com.example.ui.theme.*
import com.example.viewmodel.KiperViewModel
import kotlinx.coroutines.launch

@Composable
fun KiperAIScreen(
  viewModel: KiperViewModel,
  onNavigateToScreen: (KiperScreen) -> Unit
) {
  val messages by viewModel.chatMessages.collectAsState()
  val inputMessage by viewModel.currentChatInput.collectAsState()
  val isBotTyping by viewModel.isBotTyping.collectAsState()

  val listState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()

  val quickQuestions = listOf(
    "📚 Apa itu koperasi?",
    "💰 Apa itu SHU?",
    "🏫 Apa itu koperasi sekolah?",
    "👤 Siapa Mohammad Hatta?",
    "🛒 Apa itu koperasi konsumen?",
    "🧮 Bagaimana menghitung SHU?"
  )

  LaunchedEffect(messages.size) {
    if (messages.isNotEmpty()) {
      listState.animateScrollToItem(messages.size - 1)
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
  ) {
    // Top Info Header Banner
    Surface(
      color = Color(0xFF5E35B1),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color.White),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "🤖", fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "KIPER AI",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = "Teman Belajar Koperasi (Local Knowledge Base)",
            fontSize = 11.sp,
            color = Color.White.copy(alpha = 0.85f)
          )
        }
      }
    }

    // Quick suggestion pills scrollable row
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(SurfaceSoft)
        .horizontalScroll(rememberScrollState())
        .padding(horizontal = 12.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      quickQuestions.forEach { prompt ->
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = Color.White,
          shadowElevation = 1.dp,
          modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
              val cleanPrompt = prompt.substringAfter(" ")
              viewModel.sendChatMessage(cleanPrompt)
            }
            .testTag("quick_chip_${prompt.take(6)}")
        ) {
          Text(
            text = prompt,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF4527A0),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }
    }

    // Chat Message History List
    LazyColumn(
      state = listState,
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      contentPadding = PaddingValues(vertical = 12.dp)
    ) {
      items(messages, key = { it.id }) { msg ->
        ChatBubble(
          message = msg,
          onActionClick = { screen ->
            onNavigateToScreen(screen)
          }
        )
      }

      if (isBotTyping) {
        item {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 4.dp)
          ) {
            Box(
              modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Color(0xFFEDE7F6)),
              contentAlignment = Alignment.Center
            ) {
              Text(text = "🤖", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color(0xFFEDE7F6)
            ) {
              Text(
                text = "KIPER AI sedang mengetik...",
                fontSize = 11.sp,
                color = Color(0xFF5E35B1),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Input Bar at Bottom
    Surface(
      color = Color.White,
      tonalElevation = 6.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedTextField(
          value = inputMessage,
          onValueChange = { viewModel.currentChatInput.value = it },
          placeholder = { Text("Ketik pertanyaan tentang koperasi...", fontSize = 12.sp) },
          maxLines = 3,
          shape = RoundedCornerShape(20.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            focusedPlaceholderColor = TextMuted,
            unfocusedPlaceholderColor = TextMuted,
            focusedContainerColor = SurfaceSoft,
            unfocusedContainerColor = SurfaceSoft,
            focusedBorderColor = TurquoisePrimary,
            unfocusedBorderColor = OutlineSoft,
            cursorColor = TurquoisePrimary
          ),
          modifier = Modifier
            .weight(1f)
            .testTag("chat_input_textfield")
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
          onClick = {
            viewModel.sendChatMessage()
          },
          enabled = inputMessage.isNotBlank(),
          modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(if (inputMessage.isNotBlank()) TurquoisePrimary else SurfaceSoft)
            .testTag("chat_send_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Kirim",
            tint = if (inputMessage.isNotBlank()) Color.White else TextMuted
          )
        }
      }
    }
  }
}

@Composable
private fun ChatBubble(
  message: ChatMessage,
  onActionClick: (KiperScreen) -> Unit
) {
  val isUser = message.isUser

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
    verticalAlignment = Alignment.Top
  ) {
    if (!isUser) {
      Box(
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(Color(0xFFEDE7F6)),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "🤖", fontSize = 16.sp)
      }
      Spacer(modifier = Modifier.width(8.dp))
    }

    Column(
      horizontalAlignment = if (isUser) Alignment.End else Alignment.Start,
      modifier = Modifier.widthIn(max = 280.dp)
    ) {
      Surface(
        shape = RoundedCornerShape(
          topStart = 16.dp,
          topEnd = 16.dp,
          bottomStart = if (isUser) 16.dp else 4.dp,
          bottomEnd = if (isUser) 4.dp else 16.dp
        ),
        color = if (isUser) TurquoisePrimary else Color.White,
        shadowElevation = if (isUser) 1.dp else 2.dp
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(
            text = message.text,
            fontSize = 13.sp,
            color = if (isUser) Color.White else TextPrimary,
            lineHeight = 17.sp
          )

          // Embedded Action Button if provided (e.g. [ BUKA KALKULATOR SHU ])
          if (message.actionLabel != null && message.actionScreen != null) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
              onClick = { onActionClick(message.actionScreen) },
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(containerColor = GoldDark),
              contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.testTag("chat_action_btn_${message.actionLabel.take(6)}")
            ) {
              Text(
                text = "👉 ${message.actionLabel}",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
          }
        }
      }
    }

    if (isUser) {
      Spacer(modifier = Modifier.width(8.dp))
      Box(
        modifier = Modifier
          .size(32.dp)
          .clip(CircleShape)
          .background(TurquoiseContainer),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "🧑‍🎓", fontSize = 16.sp)
      }
    }
  }
}
