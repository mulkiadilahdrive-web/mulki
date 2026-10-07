package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KiperScreen
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KiperTopBar(
  title: String,
  totalXp: Int,
  canNavigateBack: Boolean,
  onNavigateBack: () -> Unit,
  onOpenDrawer: () -> Unit,
  onOpenProgress: () -> Unit
) {
  TopAppBar(
    title = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = title,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onPrimary
        )
      }
    },
    navigationIcon = {
      if (canNavigateBack) {
        IconButton(
          onClick = onNavigateBack,
          modifier = Modifier.testTag("top_bar_back_button")
        ) {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Kembali",
            tint = MaterialTheme.colorScheme.onPrimary
          )
        }
      } else {
        IconButton(
          onClick = onOpenDrawer,
          modifier = Modifier.testTag("top_bar_menu_button")
        ) {
          Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Buka Menu",
            tint = MaterialTheme.colorScheme.onPrimary
          )
        }
      }
    },
    actions = {
      // XP badge button
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = GoldPrimary,
        modifier = Modifier
          .padding(end = 12.dp)
          .clip(RoundedCornerShape(20.dp))
          .clickable { onOpenProgress() }
          .testTag("top_bar_xp_badge")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
          Text(text = "⚡", fontSize = 14.sp)
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "$totalXp XP",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3E2723)
          )
        }
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = TurquoisePrimary,
      titleContentColor = MaterialTheme.colorScheme.onPrimary,
      navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
      actionIconContentColor = MaterialTheme.colorScheme.onPrimary
    )
  )
}

@Composable
fun KiperBottomBar(
  currentScreen: KiperScreen,
  onSelectScreen: (KiperScreen) -> Unit
) {
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp
  ) {
    NavigationBarItem(
      selected = currentScreen is KiperScreen.MateriList || currentScreen is KiperScreen.MateriDetail,
      onClick = { onSelectScreen(KiperScreen.MateriList) },
      icon = { Text(text = "📖", fontSize = 18.sp) },
      label = { Text("Materi", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
      modifier = Modifier.testTag("nav_bottom_materi")
    )
    NavigationBarItem(
      selected = currentScreen is KiperScreen.KiperAR,
      onClick = { onSelectScreen(KiperScreen.KiperAR) },
      icon = { Text(text = "📱", fontSize = 18.sp) },
      label = { Text("AR", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
      modifier = Modifier.testTag("nav_bottom_ar")
    )
    NavigationBarItem(
      selected = currentScreen is KiperScreen.KiperAI,
      onClick = { onSelectScreen(KiperScreen.KiperAI()) },
      icon = { Text(text = "🤖", fontSize = 18.sp) },
      label = { Text("KIPER AI", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
      modifier = Modifier.testTag("nav_bottom_ai")
    )
    NavigationBarItem(
      selected = currentScreen is KiperScreen.SHUCalculator,
      onClick = { onSelectScreen(KiperScreen.SHUCalculator) },
      icon = { Text(text = "🧮", fontSize = 18.sp) },
      label = { Text("Kalkulator", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
      modifier = Modifier.testTag("nav_bottom_calc")
    )
    NavigationBarItem(
      selected = currentScreen is KiperScreen.Permainan || currentScreen is KiperScreen.GameResult,
      onClick = { onSelectScreen(KiperScreen.Permainan) },
      icon = { Text(text = "🎮", fontSize = 18.sp) },
      label = { Text("Permainan", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
      modifier = Modifier.testTag("nav_bottom_game")
    )
  }
}

@Composable
fun KiperDrawerSheet(
  currentScreen: KiperScreen,
  totalXp: Int,
  onSelectScreen: (KiperScreen) -> Unit,
  onCloseDrawer: () -> Unit
) {
  ModalDrawerSheet(
    drawerContainerColor = MaterialTheme.colorScheme.background,
    modifier = Modifier.width(310.dp)
  ) {
    // Header Drawer
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(TurquoisePrimary)
        .padding(20.dp)
    ) {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(48.dp)
              .clip(CircleShape)
              .background(Color.White),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "🏪", fontSize = 26.sp)
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = "KIPER",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "Ekonomi Koperasi",
              fontSize = 12.sp,
              color = Color.White.copy(alpha = 0.9f)
            )
          }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = "“Belajar • Eksplorasi • Praktik • Bersama Koperasi”",
          fontSize = 11.sp,
          color = Color.White.copy(alpha = 0.85f),
          lineHeight = 14.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // List of 12 required drawer items + progress
    val menuItems = listOf(
      Triple("🏠 Beranda", KiperScreen.Home, "drawer_item_home"),
      Triple("📖 Materi Koperasi", KiperScreen.MateriList, "drawer_item_materi"),
      Triple("📱 KIPER AR", KiperScreen.KiperAR, "drawer_item_ar"),
      Triple("💰 Perhitungan SHU", KiperScreen.SHUTheory, "drawer_item_shu_theory"),
      Triple("🏫 Koperasi Sekolah", KiperScreen.KoperasiSekolah, "drawer_item_coop_school"),
      Triple("👤 Tokoh Koperasi", KiperScreen.TokohKoperasi, "drawer_item_tokoh"),
      Triple("📚 Daftar Pustaka", KiperScreen.DaftarPustaka, "drawer_item_bibliography"),
      Triple("❓ Petunjuk", KiperScreen.Petunjuk, "drawer_item_guide"),
      Triple("🎯 Kompetensi", KiperScreen.Kompetensi, "drawer_item_competence"),
      Triple("🤖 KIPER AI", KiperScreen.KiperAI(), "drawer_item_ai"),
      Triple("🧮 Kalkulator SHU", KiperScreen.SHUCalculator, "drawer_item_calc"),
      Triple("🎮 Permainan", KiperScreen.Permainan, "drawer_item_game"),
      Triple("📊 Progress Belajarku", KiperScreen.ProgressBelajar, "drawer_item_progress")
    )

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
    ) {
      menuItems.forEach { (label, screen, tag) ->
        val isSelected = currentScreen::class == screen::class
        NavigationDrawerItem(
          label = { Text(text = label, fontSize = 13.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
          selected = isSelected,
          onClick = {
            onCloseDrawer()
            onSelectScreen(screen)
          },
          colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = TurquoiseContainer,
            selectedTextColor = OnTurquoiseContainer,
            unselectedTextColor = TextPrimary
          ),
          modifier = Modifier
            .padding(vertical = 2.dp)
            .testTag(tag)
        )
      }
    }
  }
}
