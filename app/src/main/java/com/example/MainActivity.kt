package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.model.KiperScreen
import com.example.ui.components.KiperBottomBar
import com.example.ui.components.KiperDrawerSheet
import com.example.ui.components.KiperTopBar
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.KiperViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  private val viewModel: KiperViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      MyApplicationTheme {
        KiperApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun KiperApp(viewModel: KiperViewModel) {
  val currentScreen by viewModel.currentScreen.collectAsState()
  val progress by viewModel.progressState.collectAsState()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val coroutineScope = rememberCoroutineScope()

  // Handle Android Back Navigation
  BackHandler(enabled = currentScreen !is KiperScreen.Splash && currentScreen !is KiperScreen.Home) {
    if (drawerState.isOpen) {
      coroutineScope.launch { drawerState.close() }
    } else {
      viewModel.navigateBack()
    }
  }

  val isSplash = currentScreen is KiperScreen.Splash

  val topBarTitle = when (currentScreen) {
    is KiperScreen.Splash -> ""
    is KiperScreen.Home -> "KIPER • Beranda"
    is KiperScreen.MateriList -> "Materi Koperasi"
    is KiperScreen.MateriDetail -> "Materi Koperasi"
    is KiperScreen.KiperAR -> "KIPER AR"
    is KiperScreen.SHUTheory -> "Perhitungan SHU"
    is KiperScreen.SHUCalculator -> "Kalkulator SHU"
    is KiperScreen.KoperasiSekolah -> "Koperasi Sekolah"
    is KiperScreen.TokohKoperasi -> "Tokoh Koperasi"
    is KiperScreen.DaftarPustaka -> "Daftar Pustaka"
    is KiperScreen.Petunjuk -> "Petunjuk Penggunaan"
    is KiperScreen.Kompetensi -> "Kompetensi Siswa"
    is KiperScreen.KiperAI -> "KIPER AI Tutor"
    is KiperScreen.Permainan -> "Permainan Koperasi"
    is KiperScreen.GameResult -> "Hasil Permainan"
    is KiperScreen.ProgressBelajar -> "Progress Belajarku"
  }

  val canNavigateBack = currentScreen !is KiperScreen.Splash && currentScreen !is KiperScreen.Home

  ModalNavigationDrawer(
    drawerState = drawerState,
    gesturesEnabled = !isSplash,
    drawerContent = {
      KiperDrawerSheet(
        currentScreen = currentScreen,
        totalXp = progress.totalXp,
        onSelectScreen = { screen ->
          coroutineScope.launch { drawerState.close() }
          viewModel.navigateTo(screen)
        },
        onCloseDrawer = {
          coroutineScope.launch { drawerState.close() }
        }
      )
    }
  ) {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        if (!isSplash) {
          KiperTopBar(
            title = topBarTitle,
            totalXp = progress.totalXp,
            canNavigateBack = canNavigateBack,
            onNavigateBack = { viewModel.navigateBack() },
            onOpenDrawer = {
              coroutineScope.launch { drawerState.open() }
            },
            onOpenProgress = {
              viewModel.navigateTo(KiperScreen.ProgressBelajar)
            }
          )
        }
      },
      bottomBar = {
        if (!isSplash && currentScreen !is KiperScreen.GameResult) {
          KiperBottomBar(
            currentScreen = currentScreen,
            onSelectScreen = { screen ->
              viewModel.navigateTo(screen)
            }
          )
        }
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      ) {
        when (val screen = currentScreen) {
          is KiperScreen.Splash -> {
            SplashScreen(
              onStartClick = { viewModel.navigateTo(KiperScreen.Home) }
            )
          }

          is KiperScreen.Home -> {
            HomeScreen(
              progress = progress,
              onNavigate = { target -> viewModel.navigateTo(target) }
            )
          }

          is KiperScreen.MateriList -> {
            MateriListScreen(
              progress = progress,
              onSelectMateri = { id -> viewModel.navigateTo(KiperScreen.MateriDetail(id)) }
            )
          }

          is KiperScreen.MateriDetail -> {
            MateriDetailScreen(
              materiId = screen.materiId,
              progress = progress,
              onMarkCompleted = { id -> viewModel.markMateriDone(id) },
              onOpenAIWithQuestion = { q -> viewModel.navigateTo(KiperScreen.KiperAI(q)) }
            )
          }

          is KiperScreen.KiperAR -> {
            ARScreen(
              viewModel = viewModel,
              onOpenAIWithQuestion = { q -> viewModel.navigateTo(KiperScreen.KiperAI(q)) }
            )
          }

          is KiperScreen.SHUTheory -> {
            SHUTheoryScreen(
              onOpenCalculator = { viewModel.navigateTo(KiperScreen.SHUCalculator) }
            )
          }

          is KiperScreen.SHUCalculator -> {
            SHUCalculatorScreen(
              viewModel = viewModel
            )
          }

          is KiperScreen.KoperasiSekolah -> {
            KoperasiSekolahScreen()
          }

          is KiperScreen.TokohKoperasi -> {
            TokohKoperasiScreen(
              onOpenAIWithQuestion = { q -> viewModel.navigateTo(KiperScreen.KiperAI(q)) }
            )
          }

          is KiperScreen.DaftarPustaka -> {
            DaftarPustakaScreen()
          }

          is KiperScreen.Petunjuk -> {
            PetunjukScreen()
          }

          is KiperScreen.Kompetensi -> {
            KompetensiScreen()
          }

          is KiperScreen.KiperAI -> {
            KiperAIScreen(
              viewModel = viewModel,
              onNavigateToScreen = { target -> viewModel.navigateTo(target) }
            )
          }

          is KiperScreen.Permainan -> {
            GameScreen(
              viewModel = viewModel
            )
          }

          is KiperScreen.GameResult -> {
            GameResultScreen(
              result = screen,
              onPlayAgain = { viewModel.restartGame() },
              onGoHome = { viewModel.navigateTo(KiperScreen.Home) }
            )
          }

          is KiperScreen.ProgressBelajar -> {
            ProgressScreen(
              progress = progress,
              onResetProgress = { viewModel.resetAllProgress() }
            )
          }
        }
      }
    }
  }
}
