package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import com.example.data.AppKnowledge
import com.example.data.KiperProgressState
import com.example.model.BadgeItem
import com.example.model.ProductItem
import com.example.ui.theme.*

// 1. KOPERASI SEKOLAH SCREEN
@Composable
fun KoperasiSekolahScreen() {
  val scrollState = rememberScrollState()
  var selectedProduct by remember { mutableStateOf<ProductItem?>(null) }

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
        Text(
          text = "Koperasi Sekolah",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Wadah belajar nyata kegiatan ekonomi dan gotong royong bagi siswa.",
          fontSize = 12.sp,
          color = Color.White.copy(alpha = 0.9f)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Info overview
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Apa itu Koperasi Sekolah?",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = TurquoisePrimary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "Koperasi sekolah adalah koperasi yang didirikan di lingkungan sekolah yang beranggotakan siswa-siswi sekolah tersebut. Koperasi sekolah tidak berbadan hukum tersendiri tetapi berada di bawah pembinaan kepala sekolah dan dinas koperasi setempat.",
          fontSize = 12.sp,
          color = TextSecondary,
          lineHeight = 17.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Tujuan Koperasi Sekolah
    Card(
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = GreenContainer),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Tujuan Koperasi Sekolah",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = ForestGreen
        )
        Spacer(modifier = Modifier.height(8.dp))
        val tujuans = listOf(
          "Memenuhi kebutuhan perlengkapan belajar siswa",
          "Melatih sikap tanggung jawab, disiplin, dan kejujuran",
          "Mengenalkan kegiatan ekonomi dan kewirausahaan sejak dini",
          "Melatih kerja sama dan musyawarah antar siswa",
          "Menumbuhkan kemandirian ekonomi siswa"
        )
        tujuans.forEach { item ->
          Row(modifier = Modifier.padding(vertical = 3.dp)) {
            Text(text = "✓", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item, fontSize = 12.sp, color = TextPrimary)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    // Interactive Product Catalog
    Text(
      text = "Katalog Produk Koperasi Sekolah (Ketuk Produk):",
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = TextPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))

    AppKnowledge.schoolProducts.chunked(2).forEach { rowProducts ->
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        rowProducts.forEach { product ->
          Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
              .weight(1f)
              .clickable { selectedProduct = product }
              .testTag("school_prod_${product.id}")
          ) {
            Column(
              modifier = Modifier.padding(12.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(text = product.icon, fontSize = 32.sp)
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = product.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = product.priceDesc,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = ForestGreen
              )
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(10.dp))
    }

    Spacer(modifier = Modifier.height(20.dp))
  }

  // Product Detail Popup Dialog
  selectedProduct?.let { product ->
    AlertDialog(
      onDismissRequest = { selectedProduct = null },
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = product.icon, fontSize = 26.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = product.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
      },
      text = {
        Column {
          Text(text = "Kategori: ${product.category}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TurquoisePrimary)
          Text(text = "Harga Terjangkau: ${product.priceDesc}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
          Spacer(modifier = Modifier.height(8.dp))
          Text(text = product.reason, fontSize = 12.sp, color = TextPrimary, lineHeight = 16.sp)
        }
      },
      confirmButton = {
        TextButton(onClick = { selectedProduct = null }) {
          Text("Tutup")
        }
      }
    )
  }
}

// 2. TOKOH KOPERASI SCREEN
@Composable
fun TokohKoperasiScreen(
  onOpenAIWithQuestion: (String) -> Unit
) {
  val scrollState = rememberScrollState()
  var showQuiz by remember { mutableStateOf(false) }
  var quizAnswered by remember { mutableStateOf<Int?>(null) }

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
      Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(GoldContainer),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "👤", fontSize = 40.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
          text = "MOHAMMAD HATTA",
          fontSize = 20.sp,
          fontWeight = FontWeight.ExtraBold,
          color = Color.White
        )
        Text(
          text = "Bapak Koperasi Indonesia",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = GoldLight
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Profile & History
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Peran dan Pemikiran Bung Hatta",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "Drs. Mohammad Hatta (Bung Hatta) merupakan salah satu proklamator kemerdekaan RI yang sangat mencintai ekonomi kerakyatan. Beliau menegaskan bahwa koperasi adalah wadah yang paling sesuai dengan jiwa bangsa Indonesia yang berasaskan gotong royong dan kekeluargaan.\n\nAtas jasa dan dedikasinya dalam Kongres Koperasi I tahun 1947 dan Kongres Koperasi II tahun 1953 di Bandung, Bung Hatta secara resmi diangkat sebagai Bapak Koperasi Indonesia.",
          fontSize = 12.sp,
          color = TextSecondary,
          lineHeight = 18.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Nilai yang dapat diteladani
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoiseContainer),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Nilai yang Dapat Kita Teladani:",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = OnTurquoiseContainer
        )
        Spacer(modifier = Modifier.height(8.dp))
        val values = listOf(
          "🤝 Kerja Sama" to "Mengutamakan kepentingan bersama di atas ego pribadi.",
          "⚖️ Tanggung Jawab" to "Amanah dan disiplin dalam mengemban tugas organisasi.",
          "❤️ Kepedulian" to "Memiliki empati terhadap kesulitan saudara dan anggota.",
          "🌱 Kemandirian" to "Percaya pada kemampuan diri sendiri dan bangsa tanpa bergantung pihak asing."
        )
        values.forEach { (title, desc) ->
          Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TurquoiseDark)
            Text(text = desc, fontSize = 11.sp, color = TextPrimary)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Action button: Buka Kuis Tokoh
    Button(
      onClick = { showQuiz = !showQuiz },
      shape = RoundedCornerShape(16.dp),
      colors = ButtonDefaults.buttonColors(containerColor = GoldDark),
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .testTag("btn_open_tokoh_quiz")
    ) {
      Text(
        text = if (showQuiz) "Tutup Kuis Tokoh" else "Buka Kuis Tokoh Bung Hatta 🎯",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }

    // Mini Quiz Tokoh
    if (showQuiz) {
      Spacer(modifier = Modifier.height(12.dp))
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Kuis Tokoh: Mengapa Bung Hatta dijuluki Bapak Koperasi Indonesia?",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Spacer(modifier = Modifier.height(10.dp))
          val options = listOf(
            "Karena beliau adalah pedagang terkaya di Indonesia",
            "Karena beliau gigih memperjuangkan koperasi sebagai tiang utama ekonomi kerakyatan",
            "Karena beliau yang pertama kali mendirikan supermarket",
            "Karena beliau mewajibkan semua orang menjadi PNS"
          )
          options.forEachIndexed { idx, opt ->
            val isSelected = quizAnswered == idx
            val isCorrect = idx == 1
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = when {
                quizAnswered == null -> SurfaceSoft
                isCorrect -> GreenContainer
                isSelected -> Color(0xFFFFEBEE)
                else -> SurfaceSoft
              },
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable(enabled = quizAnswered == null) { quizAnswered = idx }
            ) {
              Text(
                text = "${('A' + idx)}. $opt",
                fontSize = 12.sp,
                color = TextPrimary,
                modifier = Modifier.padding(10.dp)
              )
            }
          }

          if (quizAnswered != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = if (quizAnswered == 1) "Hebat! 🎉 Jawabanmu tepat." else "Jawaban yang benar adalah B.",
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = if (quizAnswered == 1) ForestGreen else ErrorRed
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

// 3. DAFTAR PUSTAKA SCREEN
@Composable
fun DaftarPustakaScreen() {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Text(
          text = "Daftar Pustaka",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Text(
          text = "Referensi rujukan materi edukasi ekonomi perkoperasian KIPER",
          fontSize = 12.sp,
          color = Color.White.copy(alpha = 0.9f)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    AppKnowledge.bibliography.forEach { (category, title, source) ->
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = TurquoiseContainer
          ) {
            Text(
              text = category,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = OnTurquoiseContainer,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.height(6.dp))
          Text(text = title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Spacer(modifier = Modifier.height(2.dp))
          Text(text = source, fontSize = 11.sp, color = TextSecondary)
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Surface(
      shape = RoundedCornerShape(12.dp),
      color = SurfaceSoft,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = "Catatan: Daftar sumber dapat diperbarui secara berkala oleh pengembang aplikasi KIPER.",
        fontSize = 11.sp,
        color = TextMuted,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(12.dp)
      )
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

// 4. PETUNJUK SCREEN
@Composable
fun PetunjukScreen() {
  val scrollState = rememberScrollState()

  val steps = listOf(
    "1. Pelajari Materi" to "Buka 7 modul materi koperasi untuk memahami pengertian, asas, prinsip, dan lambang koperasi.",
    "2. Eksplorasi KIPER AR" to "Gunakan mode simulasi AR untuk mengenali gedung, produk, modal, anggota, dan pengurus koperasi.",
    "3. Bertanya kepada KIPER AI" to "Tanyakan konsep perkoperasian kepada asisten tutor AI interaktif kapan saja.",
    "4. Gunakan Kalkulator SHU" to "Praktikkan simulasi penghitungan pembagian SHU secara real-time dan pelajari langkah-langkahnya.",
    "5. Mainkan Permainan" to "Selesaikan 4 level tantangan seru untuk meraih total 100 poin dan menguji pemahamanmu.",
    "6. Periksa Hasil & Badge" to "Kumpulkan 5 badge prestasi dan pantau perkembangan belajar di halaman Progress."
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Text(
          text = "Petunjuk Penggunaan",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Text(
          text = "Panduan alur belajar efektif bersama aplikasi KIPER",
          fontSize = 12.sp,
          color = Color.White.copy(alpha = 0.9f)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    steps.forEach { (stepTitle, stepDesc) ->
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.Top
        ) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(TurquoiseContainer),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "📌", fontSize = 16.sp)
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(text = stepTitle, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TurquoiseDark)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = stepDesc, fontSize = 12.sp, color = TextSecondary, lineHeight = 16.sp)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

// 5. KOMPETENSI SCREEN
@Composable
fun KompetensiScreen() {
  val scrollState = rememberScrollState()

  val kompetensis = listOf(
    "Menjelaskan pengertian koperasi dengan bahasa yang mudah dipahami.",
    "Menjelaskan tujuan dan asas kekeluargaan koperasi.",
    "Mengidentifikasi 7 prinsip dasar koperasi Indonesia.",
    "Menjelaskan perangkat organisasi dan sumber modal koperasi.",
    "Mengidentifikasi jenis-jenis koperasi (konsumen, produsen, simpan pinjam, jasa).",
    "Menjelaskan fungsi, tujuan, dan tata kelola koperasi sekolah.",
    "Menjelaskan konsep Sisa Hasil Usaha (SHU).",
    "Menghitung pembagian SHU sederhana secara mandiri dan akurat.",
    "Menerapkan konsep dan nilai gotong royong koperasi dalam kehidupan sehari-hari."
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
    Card(
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = TurquoisePrimary),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(18.dp)) {
        Text(
          text = "Kompetensi yang Dicapai",
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
        Text(
          text = "Capaian pembelajaran yang diharapkan setelah menggunakan KIPER",
          fontSize = 12.sp,
          color = Color.White.copy(alpha = 0.9f)
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Setelah belajar dengan KIPER, siswa mampu:",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        kompetensis.forEach { komp ->
          Row(
            modifier = Modifier.padding(vertical = 4.dp),
            verticalAlignment = Alignment.Top
          ) {
            Text(text = "✓", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = ForestGreen)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = komp,
              fontSize = 12.sp,
              color = TextSecondary,
              lineHeight = 16.sp,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}

// 6. PROGRESS BELAJAR SCREEN
@Composable
fun ProgressScreen(
  progress: KiperProgressState,
  onResetProgress: () -> Unit
) {
  val scrollState = rememberScrollState()
  var showConfirmResetDialog by remember { mutableStateOf(false) }

  val materiPct = ((progress.completedMateriIds.size * 100) / 7).coerceIn(0, 100)
  val arPct = ((minOf(progress.arHotspotsViewed.size, 4) * 100) / 4).coerceIn(0, 100)
  val aiPct = if (progress.chatbotInteractionsCount > 0) 100 else 0
  val calcPct = if (progress.calculatorUsedCount > 0) 100 else 0
  val gamePct = ((progress.completedGameLevels.size * 100) / 4).coerceIn(0, 100)

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CreamBackground)
      .verticalScroll(scrollState)
      .padding(16.dp)
  ) {
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
              text = "Progress Belajarku",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Text(
              text = "Pantau pencapaian belajar dan badge prestasimu",
              fontSize = 12.sp,
              color = Color.White.copy(alpha = 0.9f)
            )
          }
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = GoldPrimary
          ) {
            Text(
              text = "⚡ ${progress.totalXp} XP",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF3E2723),
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Progress Breakdown Cards
    Card(
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = Color.White),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Ringkasan Pencapaian",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))

        ProgressRowItem(label = "Materi Koperasi", percent = materiPct, detail = "${progress.completedMateriIds.size}/7 Selesai")
        ProgressRowItem(label = "Eksplorasi AR", percent = arPct, detail = "${minOf(progress.arHotspotsViewed.size, 4)}/4 Objek")
        ProgressRowItem(label = "KIPER AI", percent = aiPct, detail = "${progress.chatbotInteractionsCount} Pertanyaan")
        ProgressRowItem(label = "Kalkulator SHU", percent = calcPct, detail = "${progress.calculatorUsedCount} Perhitungan")
        ProgressRowItem(label = "Permainan Koperasi", percent = gamePct, detail = "${progress.completedGameLevels.size}/4 Level")
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Badges Section
    Text(
      text = "Badge Prestasi (${progress.unlockedBadgeIds.size}/5)",
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = TextPrimary
    )
    Spacer(modifier = Modifier.height(8.dp))

    AppKnowledge.badges.forEach { badge ->
      val isUnlocked = badge.id in progress.unlockedBadgeIds
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isUnlocked) GoldContainer else SurfaceSoft
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isUnlocked) 2.dp else 0.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(if (isUnlocked) GoldPrimary else OutlineSoft),
            contentAlignment = Alignment.Center
          ) {
            Text(text = if (isUnlocked) badge.icon else "🔒", fontSize = 20.sp)
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = badge.name,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = if (isUnlocked) Color(0xFF5D4037) else TextMuted
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = badge.description,
              fontSize = 11.sp,
              color = if (isUnlocked) TextPrimary else TextMuted,
              lineHeight = 14.sp
            )
          }
          if (isUnlocked) {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = ForestGreen
            ) {
              Text(
                text = "Terbuka",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Reset Progress Button
    OutlinedButton(
      onClick = { showConfirmResetDialog = true },
      shape = RoundedCornerShape(14.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed),
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .testTag("btn_reset_progress")
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Delete, contentDescription = "Reset", tint = ErrorRed)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Reset Progress Belajar", fontSize = 13.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(24.dp))
  }

  // Confirmation Alert Dialog for Reset Progress
  if (showConfirmResetDialog) {
    AlertDialog(
      onDismissRequest = { showConfirmResetDialog = false },
      title = { Text("Konfirmasi Reset Progress") },
      text = {
        Text("Apakah kamu yakin ingin mereset seluruh data progress belajar, skor game, dan badge? Tindakan ini tidak dapat dibatalkan.")
      },
      confirmButton = {
        Button(
          onClick = {
            showConfirmResetDialog = false
            onResetProgress()
          },
          colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
        ) {
          Text("Ya, Reset Data")
        }
      },
      dismissButton = {
        TextButton(onClick = { showConfirmResetDialog = false }) {
          Text("Batal")
        }
      }
    )
  }
}

@Composable
private fun ProgressRowItem(label: String, percent: Int, detail: String) {
  Column(modifier = Modifier.padding(vertical = 5.dp)) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
      Text(text = "$percent% ($detail)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TurquoisePrimary)
    }
    Spacer(modifier = Modifier.height(4.dp))
    LinearProgressIndicator(
      progress = { percent / 100f },
      color = TurquoisePrimary,
      trackColor = TurquoiseContainer,
      modifier = Modifier
        .fillMaxWidth()
        .height(6.dp)
        .clip(RoundedCornerShape(3.dp))
    )
  }
}
