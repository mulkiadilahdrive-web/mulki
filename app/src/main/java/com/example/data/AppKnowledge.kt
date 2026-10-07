package com.example.data

import com.example.model.*

data class ChatKnowledge(
  val keywords: List<String>,
  val response: String,
  val actionLabel: String? = null,
  val actionScreen: KiperScreen? = null
)

object AppKnowledge {
  val arHotspots = listOf(
    ARHotspot(
      id = "koperasi_building",
      name = "Gedung Koperasi",
      icon = "🏪",
      shortDesc = "Pusat kegiatan usaha dan layanan warga sekolah",
      fullDesc = "Gedung koperasi sekolah adalah tempat berputarnya kegiatan jual-beli alat tulis, buku, dan jajanan sehat bagi siswa serta guru.",
      aiQuestion = "Apa fungsi dan manfaat koperasi sekolah bagi siswa?",
      xPercent = 0.50f,
      yPercent = 0.35f
    ),
    ARHotspot(
      id = "produk",
      name = "Produk Koperasi",
      icon = "📦",
      shortDesc = "Barang kebutuhan yang dijual dengan harga bersahabat",
      fullDesc = "Produk yang dijual dipilih berdasarkan kebutuhan primer siswa: alat tulis, seragam, buku panduan belajar, hingga konsumsi higienis.",
      aiQuestion = "Bagaimana cara koperasi memilih produk yang akan dijual?",
      xPercent = 0.28f,
      yPercent = 0.56f
    ),
    ARHotspot(
      id = "anggota",
      name = "Anggota Siswa",
      icon = "👥",
      shortDesc = "Pemilik sekaligus pengguna jasa koperasi",
      fullDesc = "Di koperasi, anggota memegang peranan ganda: sebagai pemilik yang menyetor simpanan dan sebagai pelanggan yang berbelanja.",
      aiQuestion = "Apa saja hak dan kewajiban anggota koperasi?",
      xPercent = 0.72f,
      yPercent = 0.55f
    ),
    ARHotspot(
      id = "modal",
      name = "Modal Koperasi",
      icon = "💰",
      shortDesc = "Dana dari simpanan pokok, wajib, dan cadangan",
      fullDesc = "Modal koperasi terkumpul dari gotong royong setoran simpanan anggota. Modal ini diputar untuk pengadaan barang dagangan.",
      aiQuestion = "Apa saja sumber modal koperasi dan perbedaannya?",
      xPercent = 0.30f,
      yPercent = 0.74f
    ),
    ARHotspot(
      id = "pengurus",
      name = "Pengurus Koperasi",
      icon = "👨‍💼",
      shortDesc = "Pengelola harian yang melayani anggota dengan jujur",
      fullDesc = "Pengurus dipilih oleh anggota untuk mencatat transaksi, mengatur stok barang, dan menyusun laporan pertanggungjawaban.",
      aiQuestion = "Apa tugas utama pengurus koperasi sekolah?",
      xPercent = 0.50f,
      yPercent = 0.85f
    ),
    ARHotspot(
      id = "shu",
      name = "Sisa Hasil Usaha (SHU)",
      icon = "📊",
      shortDesc = "Keuntungan bersih yang dibagikan secara adil",
      fullDesc = "SHU adalah pendapatan koperasi setelah dikurangi biaya operasional. Sebagian dibagikan kepada anggota sesuai partisipasinya.",
      aiQuestion = "Apa itu SHU dan bagaimana cara menghitungnya?",
      xPercent = 0.70f,
      yPercent = 0.74f
    )
  )

  val arCoopTypes = listOf(
    ARCoopType(
      id = "konsumen",
      name = "Koperasi Konsumen",
      icon = "🛒",
      visualDesc = "Toko ritel dengan rak berisi buku, alat tulis, dan sembako.",
      detail = "Menyediakan barang konsumsi berkualitas dengan harga terjangkau untuk anggota dan masyarakat sekitar."
    ),
    ARCoopType(
      id = "produsen",
      name = "Koperasi Produsen",
      icon = "🌾",
      visualDesc = "Pusat penampungan dan pengolahan hasil panen tani dan susu.",
      detail = "Anggota bersatu mengolah hasil produksi agar memiliki nilai jual tinggi dan terlindungi dari tengkulak."
    ),
    ARCoopType(
      id = "ksp",
      name = "Koperasi Simpan Pinjam",
      icon = "🏦",
      visualDesc = "Loket pelayanan tabungan dan pencairan pinjaman modal kerja.",
      detail = "Menghidupkan budaya gemar menabung dan memberi pinjaman modal usaha kecil dengan bunga ramah."
    ),
    ARCoopType(
      id = "jasa",
      name = "Koperasi Jasa",
      icon = "🚗",
      visualDesc = "Armada transportasi umum dan layanan fotokopi sekolah.",
      detail = "Memberikan solusi layanan jasa bersama yang efisien bagi masyarakat seperti transportasi sekolah."
    )
  )

  val badges = listOf(
    BadgeItem("badge_materi", "Penjelajah Koperasi", "🏆", "Membuka dan mempelajari seluruh 7 materi koperasi."),
    BadgeItem("badge_ar", "Eksplorer AR", "📱", "Menjelajahi objek simulasi AR dan menyelesaikan kuis AR."),
    BadgeItem("badge_ai", "Sahabat KIPER AI", "🤖", "Berdiskusi dan mengajukan pertanyaan kepada KIPER AI."),
    BadgeItem("badge_shu", "Ahli SHU", "💰", "Menghitung pembagian SHU menggunakan Kalkulator SHU."),
    BadgeItem("badge_game", "Jagoan Koperasi", "🎮", "Menyelesaikan seluruh 4 level tantangan permainan KIPER.")
  )

  val schoolProducts = listOf(
    ProductItem("p1", "Buku Tulis & Gambar", "📚", "Alat Tulis", "Rp 5.000", "Kebutuhan wajib belajar sehari-hari siswa di kelas."),
    ProductItem("p2", "Pulpen & Pensil 2B", "✏️", "Alat Tulis", "Rp 3.000", "Tersedia lengkap untuk kegiatan ujian dan mencatat."),
    ProductItem("p3", "Minuman Mineral Sehat", "🥤", "Konsumsi", "Rp 3.500", "Menjaga konsentrasi dan hidrasi siswa saat belajar."),
    ProductItem("p4", "Camilan Biskuit Sehat", "🍪", "Konsumsi", "Rp 4.000", "Jajanan higienis terdaftar BPOM tanpa pengawet berbahaya."),
    ProductItem("p5", "Dasi & Topi Seragam", "👕", "Perlengkapan", "Rp 15.000", "Melengkapi kerapian siswa untuk upacara bendera."),
    ProductItem("p6", "Penggaris & Penghapus", "🎒", "Perlengkapan", "Rp 2.500", "Perlengkapan penting untuk mata pelajaran matematika dan gambar.")
  )

  val bibliography = listOf(
    Triple("📚 Buku", "Ekonomi untuk SMA/MA Kelas X", "Kementerian Pendidikan, Kebudayaan, Riset, dan Teknologi RI (Kurikulum Merdeka)"),
    Triple("📚 Buku", "Undang-Undang No. 25 Tahun 1992", "Tentang Perkoperasian Republik Indonesia"),
    Triple("📰 Artikel", "Peran Koperasi Sekolah dalam Pendidikan Karakter", "Jurnal Pendidikan Ekonomi Indonesia"),
    Triple("🌐 Website", "Kementerian Koperasi dan UKM RI", "Portal Resmi Informasi Perkoperasian Nasional (kemenkopukm.go.id)"),
    Triple("▶️ Video Edukasi", "Animasi Seri Belajar Ekonomi: Apa Itu Koperasi?", "Kanal Edukasi Pembelajaran Interaktif Siswa")
  )

  val knowledgeBase = listOf(
    ChatKnowledge(
      keywords = listOf("apa itu koperasi", "pengertian koperasi", "jelaskan koperasi", "koperasi itu apa", "definisi koperasi", "maksud koperasi"),
      response = "Koperasi adalah organisasi ekonomi yang dibentuk oleh anggota untuk bekerja sama memenuhi kebutuhan dan meningkatkan kesejahteraan bersama berdasarkan prinsip koperasi dan asas kekeluargaan.",
      actionLabel = "Buka Materi Koperasi",
      actionScreen = KiperScreen.MateriList
    ),
    ChatKnowledge(
      keywords = listOf("apa tujuan koperasi", "tujuan koperasi", "maksud tujuan koperasi", "kenapa ada koperasi"),
      response = "Tujuan koperasi antara lain: 1. Meningkatkan kesejahteraan anggota, 2. Membantu memenuhi kebutuhan anggota, 3. Mengembangkan usaha bersama, dan 4. Membangun tatanan ekonomi nasional berdasarkan kekeluargaan.",
      actionLabel = "Buka Materi Tujuan & Asas",
      actionScreen = KiperScreen.MateriDetail(2)
    ),
    ChatKnowledge(
      keywords = listOf("apa asas koperasi", "asas koperasi", "landasan kekeluargaan", "prinsip kekeluargaan"),
      response = "Asas koperasi adalah ASAS KEKELUARGAAN. Artinya anggota mengutamakan kerja sama, kebersamaan, rasa saling peduli, dan kepentingan bersama di atas keuntungan pribadi.",
      actionLabel = "Buka Materi Tujuan & Asas",
      actionScreen = KiperScreen.MateriDetail(2)
    ),
    ChatKnowledge(
      keywords = listOf("apa itu shu", "pengertian shu", "jelaskan shu", "sisa hasil usaha", "arti shu", "definisi shu"),
      response = "SHU adalah Sisa Hasil Usaha, yaitu hasil usaha koperasi setelah dikurangi biaya dan kewajiban sesuai ketentuan. SHU dibagikan secara adil sesuai modal dan keaktifan transaksi.",
      actionLabel = "Buka Kalkulator SHU",
      actionScreen = KiperScreen.SHUCalculator
    ),
    ChatKnowledge(
      keywords = listOf("bagaimana menghitung shu", "cara hitung shu", "rumus shu", "hitung shu", "perhitungan shu", "kalkulator shu"),
      response = "Dalam simulasi KIPER, SHU anggota dihitung dari bagian jasa modal dan jasa usaha. Kamu dapat menggunakan Kalkulator SHU untuk mencoba menghitungnya secara interaktif!",
      actionLabel = "Buka Kalkulator SHU",
      actionScreen = KiperScreen.SHUCalculator
    ),
    ChatKnowledge(
      keywords = listOf("apa itu koperasi sekolah", "koperasi sekolah", "manfaat koperasi sekolah", "koperasi di sekolah"),
      response = "Koperasi sekolah adalah koperasi di lingkungan sekolah yang membantu menyediakan kebutuhan warga sekolah dan melatih jiwa kewirausahaan siswa.",
      actionLabel = "Buka Koperasi Sekolah",
      actionScreen = KiperScreen.KoperasiSekolah
    ),
    ChatKnowledge(
      keywords = listOf("siapa mohammad hatta", "mohammad hatta", "bung hatta", "bapak koperasi", "tokoh koperasi"),
      response = "Mohammad Hatta adalah salah satu tokoh penting dalam perkembangan koperasi Indonesia dan dikenal sebagai Bapak Koperasi Indonesia.",
      actionLabel = "Buka Tokoh Koperasi",
      actionScreen = KiperScreen.TokohKoperasi
    ),
    ChatKnowledge(
      keywords = listOf("koperasi konsumen", "apa itu koperasi konsumen"),
      response = "Koperasi konsumen menyediakan barang atau kebutuhan yang diperlukan anggota, misalnya alat tulis di koperasi sekolah.",
      actionLabel = "Buka Materi Jenis Koperasi",
      actionScreen = KiperScreen.MateriDetail(7)
    ),
    ChatKnowledge(
      keywords = listOf("koperasi produsen", "apa itu koperasi produsen"),
      response = "Koperasi produsen beranggotakan orang-orang yang menghasilkan produk atau barang, seperti peternak sapi perah atau pengrajin batik.",
      actionLabel = "Buka Materi Jenis Koperasi",
      actionScreen = KiperScreen.MateriDetail(7)
    ),
    ChatKnowledge(
      keywords = listOf("koperasi simpan pinjam", "apa itu ksp", "simpan pinjam"),
      response = "Koperasi simpan pinjam melayani kegiatan simpanan dan pinjaman anggota sesuai ketentuan koperasi.",
      actionLabel = "Buka Materi Jenis Koperasi",
      actionScreen = KiperScreen.MateriDetail(7)
    ),
    ChatKnowledge(
      keywords = listOf("koperasi jasa", "apa itu koperasi jasa"),
      response = "Koperasi jasa menyediakan layanan jasa bagi anggota atau masyarakat, seperti jasa transportasi atau fotokopi.",
      actionLabel = "Buka Materi Jenis Koperasi",
      actionScreen = KiperScreen.MateriDetail(7)
    ),
    ChatKnowledge(
      keywords = listOf("apa itu modal koperasi", "modal koperasi", "sumber modal", "simpanan pokok", "simpanan wajib"),
      response = "Modal koperasi merupakan sumber dana yang digunakan untuk menjalankan kegiatan koperasi. Contohnya simpanan pokok, simpanan wajib, dan dana cadangan.",
      actionLabel = "Buka Materi Perangkat & Modal",
      actionScreen = KiperScreen.MateriDetail(5)
    ),
    ChatKnowledge(
      keywords = listOf("siapa anggota koperasi", "anggota koperasi", "hak anggota", "kewajiban anggota"),
      response = "Anggota adalah orang yang bergabung dalam koperasi dan ikut berpartisipasi dalam kegiatan koperasi sesuai ketentuan.",
      actionLabel = "Buka Materi Perangkat & Modal",
      actionScreen = KiperScreen.MateriDetail(5)
    )
  )
}
