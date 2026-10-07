package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SHUCalculationResult
import com.example.ui.theme.*
import com.example.viewmodel.KiperViewModel

@Composable
fun SHUTheoryScreen(
  onOpenCalculator: () -> Unit
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
          text = "Perhitungan SHU",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Memahami konsep dan rumus pembagian Sisa Hasil Usaha secara adil dan transparan.",
          fontSize = 13.sp,
          color = Color.White.copy(alpha = 0.9f),
          lineHeight = 17.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 1. Pengertian SHU
    TheoryCard(
      title = "1. Pengertian SHU",
      icon = "💡",
      content = "SHU atau Sisa Hasil Usaha merupakan pendapatan koperasi yang diperoleh dalam satu periode (biasanya 1 tahun buku) setelah dikurangi biaya, penyusutan, kewajiban, dan ketentuan lainnya.\n\nSHU tidak selalu dibagi sama rata. Pembagian kepada anggota mempertimbangkan partisipasi nyata anggota, seperti besaran simpanan modal dan keaktifan transaksi berbelanja."
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 2. Pembagian SHU
    TheoryCard(
      title = "2. Pembagian SHU",
      icon = "⚖️",
      content = "Pembagian SHU mengikuti keputusan Rapat Anggota dan ketentuan anggaran dasar koperasi.\n\nDalam simulasi pembelajaran KIPER, pembagian untuk anggota terdiri dari dua komponen utama:\n• Jasa Modal = 40%\n• Jasa Usaha = 30%\n\nSisa 30% lainnya dialokasikan untuk dana cadangan, dana pendidikan pengurus/anggota, dan dana sosial."
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 3. Cara Menghitung SHU (Rumus)
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "📐", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "3. Rumus Menghitung SHU",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TurquoisePrimary
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Rumus 1
        FormulaBox(
          title = "SHU Jasa Modal (JM)",
          formula = "Total SHU × %Jasa Modal × (Simpanan Anggota / Total Simpanan)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rumus 2
        FormulaBox(
          title = "SHU Jasa Usaha (JU)",
          formula = "Total SHU × %Jasa Usaha × (Transaksi Anggota / Total Transaksi)"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rumus 3
        FormulaBox(
          title = "Total SHU Anggota",
          formula = "SHU Jasa Modal + SHU Jasa Usaha"
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Action button to Calculator
    Button(
      onClick = onOpenCalculator,
      shape = RoundedCornerShape(18.dp),
      colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("btn_open_shu_calc")
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(imageVector = Icons.Default.Calculate, contentDescription = "Hitung", tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Coba Hitung Sendiri →", fontSize = 15.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

@Composable
fun SHUCalculatorScreen(
  viewModel: KiperViewModel
) {
  val totalSHU by viewModel.totalSHUInput.collectAsState()
  val modalPct by viewModel.modalPctInput.collectAsState()
  val usahaPct by viewModel.usahaPctInput.collectAsState()
  val simpanan by viewModel.simpananAnggotaInput.collectAsState()
  val totalSimp by viewModel.totalSimpananInput.collectAsState()
  val transaksi by viewModel.transaksiAnggotaInput.collectAsState()
  val totalTrans by viewModel.totalTransaksiInput.collectAsState()

  val result by viewModel.shuResult.collectAsState()
  val showSteps by viewModel.showSteps.collectAsState()

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
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🧮", fontSize = 28.sp)
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Kalkulator SHU",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "Hitung bagian SHU anggotamu secara interaktif",
              fontSize = 12.sp,
              color = Color.White.copy(alpha = 0.9f)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Form inputs Card
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Data Koperasi & Anggota",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        // 1. Total SHU
        CalculatorInputField(
          label = "TOTAL SHU KOPERASI (Rp)",
          value = totalSHU,
          onValueChange = { viewModel.updateSHUInput("totalSHU", it) },
          tag = "input_total_shu"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 2 & 3. Persentase Jasa Modal & Usaha
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          CalculatorInputField(
            label = "% JASA MODAL",
            value = modalPct,
            onValueChange = { viewModel.updateSHUInput("modalPct", it) },
            tag = "input_modal_pct",
            modifier = Modifier.weight(1f)
          )
          CalculatorInputField(
            label = "% JASA USAHA",
            value = usahaPct,
            onValueChange = { viewModel.updateSHUInput("usahaPct", it) },
            tag = "input_usaha_pct",
            modifier = Modifier.weight(1f)
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 4. Simpanan Anggota
        CalculatorInputField(
          label = "SIMPANAN ANGGOTA (Rp)",
          value = simpanan,
          onValueChange = { viewModel.updateSHUInput("simpananAnggota", it) },
          tag = "input_simpanan_anggota"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 5. Total Simpanan Koperasi
        CalculatorInputField(
          label = "TOTAL SIMPANAN KOPERASI (Rp)",
          value = totalSimp,
          onValueChange = { viewModel.updateSHUInput("totalSimpanan", it) },
          tag = "input_total_simpanan"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 6. Transaksi Anggota
        CalculatorInputField(
          label = "TRANSAKSI ANGGOTA (Rp)",
          value = transaksi,
          onValueChange = { viewModel.updateSHUInput("transaksiAnggota", it) },
          tag = "input_transaksi_anggota"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 7. Total Transaksi Koperasi
        CalculatorInputField(
          label = "TOTAL TRANSAKSI KOPERASI (Rp)",
          value = totalTrans,
          onValueChange = { viewModel.updateSHUInput("totalTransaksi", it) },
          tag = "input_total_transaksi"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Action Button: HITUNG SHU
        Button(
          onClick = { viewModel.triggerCalculateSHU() },
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(containerColor = TurquoisePrimary),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("btn_hitung_shu")
        ) {
          Text(text = "HITUNG SHU", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Results Box
    if (result.isValid) {
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("card_shu_result")
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Text(
            text = "Hasil Perhitungan SHU Anggota",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen
          )

          Spacer(modifier = Modifier.height(12.dp))

          ResultRow(label = "Jasa Modal (40%)", value = KiperViewModel.formatRupiah(result.jasaModal))
          Spacer(modifier = Modifier.height(6.dp))
          ResultRow(label = "Jasa Usaha (30%)", value = KiperViewModel.formatRupiah(result.jasaUsaha))

          Spacer(modifier = Modifier.height(10.dp))
          Divider(color = ForestGreen.copy(alpha = 0.3f))
          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "TOTAL SHU ANGGOTA",
              fontSize = 13.sp,
              fontWeight = FontWeight.ExtraBold,
              color = ForestGreen
            )
            Text(
              text = KiperViewModel.formatRupiah(result.totalSHU),
              fontSize = 20.sp,
              fontWeight = FontWeight.ExtraBold,
              color = ForestGreen
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Toggle Steps button
          OutlinedButton(
            onClick = { viewModel.toggleSteps() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = ForestGreen),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("btn_toggle_steps")
          ) {
            Text(
              text = if (showSteps) "Sembunyikan Langkah Perhitungan" else "Lihat Langkah Perhitungan",
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }

          // Step-by-step breakdown container
          AnimatedVisibility(visible = showSteps) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(12.dp)
            ) {
              Text(
                text = "LANGKAH 1 : Jasa Modal",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TurquoisePrimary
              )
              Text(
                text = result.step1Text,
                fontSize = 11.sp,
                color = TextPrimary,
                lineHeight = 15.sp
              )

              Spacer(modifier = Modifier.height(8.dp))
              Divider(color = OutlineSoft)
              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = "LANGKAH 2 : Jasa Usaha",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TurquoisePrimary
              )
              Text(
                text = result.step2Text,
                fontSize = 11.sp,
                color = TextPrimary,
                lineHeight = 15.sp
              )

              Spacer(modifier = Modifier.height(8.dp))
              Divider(color = OutlineSoft)
              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = "LANGKAH 3 : Total SHU Anggota",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ForestGreen
              )
              Text(
                text = result.step3Text,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                lineHeight = 15.sp
              )
            }
          }
        }
      }
    } else {
      // Error validation notice
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ErrorContainer),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(imageVector = Icons.Default.Info, contentDescription = "Error", tint = ErrorRed)
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = result.errorMessage ?: "Data tidak valid",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ErrorRed
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }
}

@Composable
private fun CalculatorInputField(
  label: String,
  value: String,
  onValueChange: (String) -> Unit,
  tag: String,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    Text(
      text = label,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      color = TextSecondary
    )
    Spacer(modifier = Modifier.height(4.dp))
    OutlinedTextField(
      value = value,
      onValueChange = onValueChange,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      singleLine = true,
      shape = RoundedCornerShape(12.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = TextPrimary,
        unfocusedTextColor = TextPrimary,
        focusedContainerColor = SurfaceSoft,
        unfocusedContainerColor = SurfaceSoft,
        focusedBorderColor = TurquoisePrimary,
        unfocusedBorderColor = OutlineSoft,
        cursorColor = TurquoisePrimary
      ),
      modifier = Modifier
        .fillMaxWidth()
        .testTag(tag)
    )
  }
}

@Composable
private fun ResultRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(text = label, fontSize = 12.sp, color = TextPrimary)
    Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
  }
}

@Composable
private fun TheoryCard(title: String, icon: String, content: String) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = icon, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = title,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = TurquoisePrimary
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = content,
        fontSize = 13.sp,
        color = TextSecondary,
        lineHeight = 18.sp
      )
    }
  }
}

@Composable
private fun FormulaBox(title: String, formula: String) {
  Surface(
    shape = RoundedCornerShape(10.dp),
    color = TurquoiseContainer,
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Text(
        text = title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = OnTurquoiseContainer
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = formula,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = TurquoiseDark
      )
    }
  }
}
