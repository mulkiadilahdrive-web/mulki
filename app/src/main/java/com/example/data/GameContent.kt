package com.example.data

import com.example.model.CalculationChallengeData
import com.example.model.GameQuestion

object GameContent {
  val level1Questions = listOf(
    GameQuestion(
      id = "g1_1",
      level = 1,
      question = "Koperasi yang menyediakan barang-barang kebutuhan sehari-hari bagi anggotanya disebut koperasi...",
      options = listOf("Konsumen", "Produsen", "Jasa", "Simpan Pinjam"),
      correctIndex = 0,
      points = 5,
      explanation = "Koperasi konsumen menjual barang atau kebutuhan yang diperlukan anggota dan masyarakat sekitar."
    ),
    GameQuestion(
      id = "g1_2",
      level = 1,
      question = "Koperasi yang membantu anggota dalam kegiatan simpanan tabungan dan pinjaman modal adalah...",
      options = listOf("Koperasi Produsen", "Koperasi Jasa", "Koperasi Simpan Pinjam", "Koperasi Sekolah"),
      correctIndex = 2,
      points = 5,
      explanation = "Koperasi Simpan Pinjam (KSP) melayani kegiatan menabung dan meminjam dana untuk anggota."
    ),
    GameQuestion(
      id = "g1_3",
      level = 1,
      question = "Koperasi Indonesia berlandaskan asas utama yaitu...",
      options = listOf("Asas Individualis", "Asas Persaingan Bebas", "Asas Kekeluargaan", "Asas Monopoli"),
      correctIndex = 2,
      points = 5,
      explanation = "Asas kekeluargaan mengedepankan kerja sama, gotong royong, dan rasa kebersamaan."
    ),
    GameQuestion(
      id = "g1_4",
      level = 1,
      question = "Koperasi sekolah biasanya didirikan untuk menyediakan...",
      options = listOf("Kebutuhan belajar siswa", "Kendaraan bermotor mewah", "Tiket penerbangan internasional", "Pinjaman uang berbunga tinggi"),
      correctIndex = 0,
      points = 5,
      explanation = "Koperasi sekolah fokus memenuhi kebutuhan belajar siswa seperti alat tulis, seragam, dan buku."
    ),
    GameQuestion(
      id = "g1_5",
      level = 1,
      question = "Tujuan utama berdirinya sebuah koperasi adalah...",
      options = listOf("Mencari kekayaan pribadi pengurus", "Meningkatkan kesejahteraan para anggota", "Mengalahkan semua pedagang pasar", "Menarik pajak dari masyarakat"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi bertujuan utama untuk menyejahterakan para anggotanya melalui kerja sama ekonomi."
    )
  )

  val level2Questions = listOf(
    GameQuestion(
      id = "g2_1",
      level = 2,
      question = "Kasus: Koperasi sekolah mengalami penurunan penjualan alat tulis. Apa langkah terbaik yang sebaiknya dilakukan pengurus?",
      options = listOf("Menutup koperasi sekolah seketika", "Menaikkan semua harga barang lain", "Melakukan survei kebutuhan siswa", "Mengurangi semua produk secara drastis"),
      correctIndex = 2,
      points = 5,
      explanation = "Melakukan survei kebutuhan siswa adalah keputusan bijak agar pengadaan barang sesuai dengan keinginan anggota."
    ),
    GameQuestion(
      id = "g2_2",
      level = 2,
      question = "Kasus: Salah satu anggota lupa membawa uang tunai saat ingin membeli penggaris untuk ujian matematika. Kebijakan ramah apa yang paling tepat?",
      options = listOf("Mengusir siswa tersebut dari toko", "Mencatat bon kasbon dengan batas pelunasan jelas bagi anggota terdaftar", "Menyita tas siswa sebagai jaminan", "Menaikkan harga penggaris dua kali lipat"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi bersifat menolong sesama anggota dengan sistem administrasi yang tertib dan bertanggung jawab."
    ),
    GameQuestion(
      id = "g2_3",
      level = 2,
      question = "Kasus: Ada sisa keuntungan (SHU) koperasi sekolah di akhir tahun ajaran. Bagaimana cara membagikannya secara adil?",
      options = listOf("Diberikan seluruhnya kepada kepala sekolah", "Dibagikan kepada anggota sesuai proporsi simpanan dan belanjanya", "Dibagikan secara acak melalui undian lotre", "Disimpan oleh ketua koperasi untuk jalan-jalan"),
      correctIndex = 1,
      points = 5,
      explanation = "SHU dibagikan secara adil berdasarkan partisipasi modal dan keaktifan transaksi setiap anggota."
    ),
    GameQuestion(
      id = "g2_4",
      level = 2,
      question = "Kasus: Makanan yang dijual di koperasi sekolah mulai mendekati kedaluwarsa. Apa tindakan yang benar sesuai standar etika?",
      options = listOf("Tetap menjualnya diam-diam", "Mengganti label tanggal kedaluwarsa", "Menarik makanan tersebut dan mengevaluasi manajemen stok barang", "Menyuruh adik kelas membelinya"),
      correctIndex = 2,
      points = 5,
      explanation = "Koperasi mengutamakan keselamatan dan kejujuran dengan menarik barang kedaluwarsa dari peredaran."
    ),
    GameQuestion(
      id = "g2_5",
      level = 2,
      question = "Kasus: Banyak siswa ingin menambahkan produk jas hujan di koperasi sekolah saat musim hujan tiba. Hal ini mencerminkan prinsip...",
      options = listOf("Koperasi tanggap terhadap kebutuhan nyata anggotanya", "Koperasi bersaing tidak sehat dengan toko luar", "Koperasi boros mengeluarkan anggaran", "Koperasi melanggar aturan sekolah"),
      correctIndex = 0,
      points = 5,
      explanation = "Koperasi yang adaptif selalu memperhatikan kebutuhan primer anggotanya sesuai situasi dan kondisi."
    )
  )

  val level3Questions = listOf(
    GameQuestion(
      id = "g3_1",
      level = 3,
      question = "Simulasi Manajer: Siswa menjadi pengelola koperasi sekolah. Pilihlah produk awal yang paling tepat untuk disediakan:",
      options = listOf("Aksesoris gelang impor", "Alat Tulis dan Buku Catatan", "Mainan elektronik mahal", "Kembang api"),
      correctIndex = 1,
      points = 5,
      explanation = "Produk yang baik untuk koperasi sekolah adalah produk yang sesuai dengan kebutuhan belajar siswa."
    ),
    GameQuestion(
      id = "g3_2",
      level = 3,
      question = "Simulasi Manajer: Dua pemasok menawarkan buku tulis. Pemasok A murah tapi tipis dan mudah robek, Pemasok B berkualitas dan tebal dengan harga wajar. Pilihanmu?",
      options = listOf("Pilih Pemasok A untuk untung sesaat", "Pilih Pemasok B karena anggota berhak mendapat barang berkualitas", "Tidak menjual buku sama sekali", "Beli buku tanpa izin"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi memprioritaskan kepuasan dan manfaat anggota, bukan sekadar keuntungan sesaat."
    ),
    GameQuestion(
      id = "g3_3",
      level = 3,
      question = "Simulasi Manajer: Siswa memilih jajanan untuk koperasi sekolah. Makanan seperti apa yang wajib dipilih?",
      options = listOf("Banyak pewarna buatan", "Makanan higienis berizin BPOM/P-IRT dan bergizi", "Makanan tanpa tanggal kedaluwarsa", "Harganya sangat mahal"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi sekolah bertanggung jawab menjaga kesehatan dan nutrisi para siswa."
    ),
    GameQuestion(
      id = "g3_4",
      level = 3,
      question = "Simulasi Manajer: Menjelang pekan ujian semester, produk apa yang perlu ditambah stoknya?",
      options = listOf("Pensil 2B, penghapus khusus ujian, dan papan jalan", "Topi upacara", "Seragam pramuka", "Kapur tulis"),
      correctIndex = 0,
      points = 5,
      explanation = "Perencanaan stok yang tepat mengikuti kalender kegiatan belajar siswa meningkatkan efisiensi koperasi."
    ),
    GameQuestion(
      id = "g3_5",
      level = 3,
      question = "Simulasi Manajer: Saat pembukuan kas mingguan ada selisih kurang Rp10.000. Tindakan apa yang paling tepat?",
      options = listOf("Menutupi laporan dan diam saja", "Mengecek kembali catatan mutasi kas masuk dan keluar secara teliti bersama bendahara", "Menuduh teman tanpa bukti", "Mengambil uang kas pribadi"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi menjunjung tinggi transparansi, akuntabilitas, dan kejujuran dalam pembukuan."
    )
  )

  val level4Questions = listOf(
    GameQuestion(
      id = "g4_1",
      level = 4,
      question = "Tantangan SHU 1:\nTotal SHU Koperasi: Rp12.000.000\nJasa Modal: 40%\nJasa Usaha: 30%\nSimpananmu: Rp3.000.000 dari Total Simpanan Rp30.000.000\nTransaksimu: Rp4.000.000 dari Total Transaksi Rp40.000.000\nBerapakah total SHU yang kamu terima?",
      options = listOf("Rp 480.000", "Rp 700.000", "Rp 840.000", "Rp 960.000"),
      correctIndex = 2,
      points = 5,
      explanation = "Jasa Modal = 12.000.000 × 40% × 3/30 = Rp480.000\nJasa Usaha = 12.000.000 × 30% × 4/40 = Rp360.000\nTotal SHU = Rp480.000 + Rp360.000 = Rp840.000",
      calculationData = CalculationChallengeData(
        totalSHU = 12000000L,
        modalPct = 40,
        usahaPct = 30,
        simpananAnggota = 3000000L,
        totalSimpanan = 30000000L,
        transaksiAnggota = 4000000L,
        totalTransaksi = 40000000L,
        expectedTotal = 840000L
      )
    ),
    GameQuestion(
      id = "g4_2",
      level = 4,
      question = "Tantangan SHU 2:\nTotal SHU: Rp10.000.000\nJasa Modal: 40%\nSimpananmu: Rp2.000.000 dari Total Simpanan Rp20.000.000\nBerapakah bagian Jasa Modalmu saja?",
      options = listOf("Rp 200.000", "Rp 400.000", "Rp 500.000", "Rp 800.000"),
      correctIndex = 1,
      points = 5,
      explanation = "Jasa Modal = Rp10.000.000 × 40% × (Rp2.000.000 / Rp20.000.000) = Rp4.000.000 × 0,1 = Rp400.000."
    ),
    GameQuestion(
      id = "g4_3",
      level = 4,
      question = "Tantangan SHU 3:\nTotal SHU: Rp10.000.000\nJasa Usaha: 30%\nTransaksimu: Rp5.000.000 dari Total Transaksi Rp40.000.000\nBerapakah bagian Jasa Usahamu saja?",
      options = listOf("Rp 375.000", "Rp 300.000", "Rp 450.000", "Rp 500.000"),
      correctIndex = 0,
      points = 5,
      explanation = "Jasa Usaha = Rp10.000.000 × 30% × (Rp5.000.000 / Rp40.000.000) = Rp3.000.000 × 0,125 = Rp375.000."
    ),
    GameQuestion(
      id = "g4_4",
      level = 4,
      question = "Tantangan SHU 4:\nDari perhitungan di atas, jika Jasa Modal = Rp400.000 dan Jasa Usaha = Rp375.000, berapa Total SHU Anggota?",
      options = listOf("Rp 675.000", "Rp 775.000", "Rp 800.000", "Rp 875.000"),
      correctIndex = 1,
      points = 5,
      explanation = "Total SHU Anggota = Jasa Modal + Jasa Usaha = Rp400.000 + Rp375.000 = Rp775.000."
    ),
    GameQuestion(
      id = "g4_5",
      level = 4,
      question = "Tantangan SHU 5:\nDalam perhitungan koperasi, SHU tidak selalu dibagi sama rata karena mempertimbangkan...",
      options = listOf("Usia anggota", "Partisipasi anggota seperti simpanan modal dan keaktifan transaksi", "Tinggi badan", "Warna baju seragam"),
      correctIndex = 1,
      points = 5,
      explanation = "Koperasi menghargai partisipasi nyata anggota: makin aktif menabung dan bertransaksi, makin besar SHU yang diterima secara adil."
    )
  )
}
