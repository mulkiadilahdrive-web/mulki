package com.example.data

import com.example.model.InteractiveCard
import com.example.model.MateriItem
import com.example.model.MiniQuiz

object MateriContent {
  val allMateri = listOf(
    MateriItem(
      id = 1,
      number = 1,
      icon = "🏪",
      title = "Pengertian Koperasi",
      subtitle = "Pahami apa itu koperasi dari sudut pandang sederhana",
      description = "Koperasi adalah badan usaha yang beranggotakan orang-seorang atau badan hukum koperasi dengan melandaskan kegiatannya berdasarkan prinsip koperasi sekaligus sebagai gerakan ekonomi rakyat yang berdasar atas asas kekeluargaan.",
      studentDefinition = "Sederhananya, koperasi adalah tempat orang-orang bekerja sama untuk memenuhi kebutuhan dan meningkatkan kesejahteraan bersama tanpa mencari keuntungan pribadi semata.",
      examples = listOf(
        "Koperasi Sekolah" to "Menyediakan alat tulis, buku panduan, seragam, dan camilan sehat bagi siswa di lingkungan sekolah.",
        "Koperasi Konsumen" to "Menyediakan kebutuhan pokok rumah tangga seperti sembako dengan harga yang terjangkau dan adil bagi anggota.",
        "Koperasi Simpan Pinjam" to "Membantu anggota menabung secara aman dan memberikan pinjaman modal usaha dengan bunga yang ringan."
      ),
      reflectionQuestion = "Apakah kamu pernah membeli sesuatu di koperasi sekolahmu? Barang apa yang paling sering kamu beli di sana?",
      miniQuiz = MiniQuiz(
        question = "Koperasi adalah badan usaha yang mengutamakan...",
        options = listOf("Keuntungan sebesar-besarnya", "Kerja sama dan kesejahteraan bersama", "Monopoli pasar", "Kepentingan pemilik modal besar"),
        correctIndex = 1,
        explanation = "Tujuan utama koperasi bukan mencari laba pribadi, melainkan kerja sama untuk kesejahteraan seluruh anggotanya."
      )
    ),

    MateriItem(
      id = 2,
      number = 2,
      icon = "🎯",
      title = "Tujuan dan Asas Koperasi",
      subtitle = "Mengenal fondasi utama semangat berkoperasi",
      description = "Koperasi memiliki tujuan luhur untuk menyejahterakan anggota pada khususnya dan masyarakat pada umumnya, serta ikut membangun tatanan perekonomian nasional.",
      studentDefinition = "Koperasi berlandaskan 'ASAS KEKELUARGAAN'. Artinya, semua anggota diperlakukan layaknya keluarga: saling peduli, saling bantu, gotong royong, dan tidak ada yang saling menindas.",
      examples = listOf(
        "Meningkatkan Kesejahteraan" to "Keuntungan (SHU) dibagikan kembali kepada anggota sesuai partisipasinya.",
        "Memenuhi Kebutuhan" to "Ketika koperasi sekolah menentukan barang yang akan dijual, kebutuhan siswa menjadi pertimbangan utama.",
        "Mengembangkan Usaha Bersama" to "Anggota yang memiliki usaha kecil dapat menjual produknya melalui koperasi.",
        "Membangun Ekonomi Kekeluargaan" to "Pengambilan keputusan dilakukan bersama dalam musyawarah."
      ),
      reflectionQuestion = "Mengapa asas kekeluargaan sangat cocok diterapkan dalam perekonomian Indonesia?",
      miniQuiz = MiniQuiz(
        question = "Asas utama gerakan koperasi Indonesia adalah...",
        options = listOf("Persaingan Bebas", "Asas Kekeluargaan", "Keuntungan Pribadi", "Monopoli Dagang"),
        correctIndex = 1,
        explanation = "Koperasi berlandaskan Asas Kekeluargaan yang menjunjung tinggi kebersamaan, gotong royong, dan mufakat."
      )
    ),

    MateriItem(
      id = 3,
      number = 3,
      icon = "⚖️",
      title = "Landasan dan Prinsip Koperasi",
      subtitle = "Pijakan hukum dan 7 prinsip perkoperasian",
      description = "Landasan koperasi terdiri dari: 1. Landasan Idiil: Pancasila; 2. Landasan Struktural: UUD 1945 Pasal 33 ayat 1; 3. Landasan Operasional: UU Perkoperasian & Prinsip Koperasi.",
      studentDefinition = "Prinsip koperasi adalah pedoman hidup yang wajib dijalankan oleh setiap pengurus dan anggota agar koperasi berjalan adil dan demokratis.",
      examples = listOf(
        "Landasan Idiil" to "Pancasila (terutama Sila ke-5 Keadilan Sosial bagi Seluruh Rakyat Indonesia).",
        "Landasan Struktural" to "UUD 1945 Pasal 33 ayat 1: Perekonomian disusun sebagai usaha bersama berdasar atas asas kekeluargaan.",
        "Landasan Operasional" to "UU No. 25 Tahun 1992 tentang Perkoperasian dan Anggaran Dasar Koperasi."
      ),
      reflectionQuestion = "Bagaimana prinsip demokrasi diterapkan dalam pemilihan ketua koperasi?",
      interactiveCards = listOf(
        InteractiveCard("1. Keanggotaan Sukarela dan Terbuka", "🚪", "Tidak ada paksaan", "Siapapun yang memenuhi syarat boleh bergabung tanpa membedakan agama, suku, atau ras.", "Siswa bebas memilih bergabung dengan koperasi sekolah tanpa paksaan."),
        InteractiveCard("2. Pengelolaan Demokratis", "🗳️", "Satu orang, satu suara", "Keputusan diambil melalui Rapat Anggota, di mana hak suara setiap anggota bernilai sama.", "Dalam voting ketua koperasi, setiap anggota berhak memilih 1 suara."),
        InteractiveCard("3. Pembagian SHU Adil", "📊", "Sesuai jasa dan partisipasi", "Bagian SHU yang diterima sebanding dengan besar simpanan dan transaksi yang dilakukan.", "Anggota yang sering belanja di koperasi mendapat SHU jasa usaha lebih besar."),
        InteractiveCard("4. Balas Jasa Modal Terbatas", "🪙", "Bukan penumpukan modal", "Bunga atau imbalan atas modal simpanan dibatasi secara wajar.", "Modal tidak menentukan kekuasaan mutlak dalam koperasi."),
        InteractiveCard("5. Kemandirian", "🌱", "Berdiri sendiri", "Koperasi mampu mengelola diri sendiri secara mandiri tanpa bergantung pada pihak lain.", "Koperasi mengelola keuangannya secara profesional dan mandiri."),
        InteractiveCard("6. Pendidikan Perkoperasian", "📚", "Mencerdaskan anggota", "Koperasi aktif memberikan pelatihan dan edukasi bagi anggota, pengurus, dan masyarakat.", "Koperasi sekolah mengadakan pelatihan kepemimpinan dan kewirausahaan."),
        InteractiveCard("7. Kerja Sama Antarkoperasi", "🤝", "Jaringan gotong royong", "Koperasi saling bekerja sama baik di tingkat lokal, daerah, nasional, maupun internasional.", "Koperasi sekolah bermitra dengan koperasi produsen susu lokal.")
      ),
      miniQuiz = MiniQuiz(
        question = "Landasan idiil koperasi Indonesia adalah...",
        options = listOf("UUD 1945", "Pancasila", "Ketetapan MPR", "Hukum Adat"),
        correctIndex = 1,
        explanation = "Landasan idiil koperasi Indonesia adalah Pancasila, yang mencerminkan jiwa luhur bangsa Indonesia."
      )
    ),

    MateriItem(
      id = 4,
      number = 4,
      icon = "🛡️",
      title = "Lambang Koperasi",
      subtitle = "Makna filosofis di balik simbol lambang Koperasi Indonesia",
      description = "Lambang koperasi klasik Indonesia kaya akan makna mendalam yang menggambarkan cita-cita persatuan, keadilan, dan kemakmuran rakyat.",
      studentDefinition = "Setiap unsur dalam lambang koperasi memiliki pesan moral yang mengajarkan persahabatan, kerja keras, kejujuran, dan kesejahteraan bersama.",
      examples = listOf(
        "Identitas Gerakan" to "Lambang ini menjadi pemersatu gerakan koperasi di seluruh pelosok Nusantara."
      ),
      reflectionQuestion = "Simbol mana dari lambang koperasi yang paling kamu sukai maknanya?",
      interactiveCards = listOf(
        InteractiveCard("Rantai", "⛓️", "Persahabatan & Persatuan", "Melambangkan ikatan persahabatan dan persatuan yang kokoh antar sesama anggota koperasi.", "Anggota saling mendukung bagaikan mata rantai yang tak terputus."),
        InteractiveCard("Roda Gigi", "⚙️", "Kerja Keras & Usaha", "Menggambarkan upaya keras dan usaha karya yang terus-menerus digerakkan demi kemajuan bersama.", "Semangat pantang menyerah dalam mengelola usaha koperasi."),
        InteractiveCard("Kapas dan Padi", "🌾", "Kemakmuran & Kesejahteraan", "Simbol sandang dan pangan yang melambangkan tujuan utama mencapai kemakmuran rakyat.", "Kebutuhan dasar sandang dan pangan anggota terpenuhi."),
        InteractiveCard("Timbangan", "⚖️", "Keadilan Sosial", "Melambangkan keadilan sosial bagi seluruh anggota sebagai salah satu dasar koperasi.", "Hak dan kewajiban setiap anggota dijalankan dengan seimbang dan adil."),
        InteractiveCard("Bintang dan Perisai", "⭐", "Nilai Ketuhanan & Pancasila", "Melambangkan Pancasila sebagai landasan idiil dan landasan moral perjuangan koperasi.", "Berusaha dengan memegang teguh kejujuran dan etika bermoral."),
        InteractiveCard("Pohon Beringin", "🌳", "Kehidupan & Pengayoman", "Menggambarkan sifat kemasyarakatan dan kepribadian Indonesia yang berakar kokoh serta mengayomi.", "Koperasi menjadi tempat berlindung ekonomi rakyat."),
        InteractiveCard("Tulisan Koperasi Indonesia", "🇮🇩", "Identitas Nasional", "Menunjukkan kepribadian koperasi rakyat Indonesia yang mandiri.", "Koperasi adalah soko guru perekonomian bangsa Indonesia.")
      )
    ),

    MateriItem(
      id = 5,
      number = 5,
      icon = "🏛️",
      title = "Perangkat dan Modal Koperasi",
      subtitle = "Struktur organisasi dan sumber keuangan koperasi",
      description = "Koperasi dikelola oleh perangkat organisasi yang jelas dan didukung oleh permodalan dari anggota serta sumber lainnya.",
      studentDefinition = "Perangkat adalah orang-orang dan forum yang menjalankan koperasi, sedangkan modal adalah dana yang digunakan untuk mengoperasikan toko/koperasi.",
      examples = listOf(
        "Rapat Anggota" to "Kekuasaan tertinggi dalam koperasi di mana semua anggota berhak menentukan kebijakan.",
        "Pengurus" to "Anggota yang dipilih untuk mengelola operasional harian koperasi.",
        "Pengawas" to "Anggota yang dipilih untuk mengawasi kinerja pengurus dan keuangan koperasi."
      ),
      reflectionQuestion = "Mengapa simpanan pokok hanya dibayarkan sekali saat pertama kali menjadi anggota?",
      interactiveCards = listOf(
        InteractiveCard("Rapat Anggota (RAT)", "🗳️", "Kekuasaan Tertinggi", "Forum tertinggi koperasi untuk menetapkan anggaran dasar, memilih pengurus, dan mengesahkan laporan pertanggungjawaban.", "Diadakan minimal setahun sekali."),
        InteractiveCard("Pengurus Koperasi", "👨‍💼", "Pengelola Operasional", "Dipilih dari dan oleh anggota untuk memimpin jalannya usaha dan mengelola aset koperasi.", "Ketua, sekretaris, dan bendahara koperasi."),
        InteractiveCard("Pengawas Koperasi", "🔍", "Pemeriksa Kinerja", "Bertugas mengawasi jalannya usaha, administrasi, dan laporan keuangan koperasi.", "Melaporkan hasil audit pengawasan kepada Rapat Anggota."),
        InteractiveCard("Simpanan Pokok", "🪙", "Modal Awal Anggota", "Sejumlah uang yang wajib disetorkan sekali saat pertama mendaftar menjadi anggota koperasi. Tidak dapat diambil selama menjadi anggota.", "Contoh: Rp50.000 saat resmi mendaftar."),
        InteractiveCard("Simpanan Wajib", "📅", "Setoran Rutin Berkala", "Simpanan tertentu yang harus dibayarkan anggota secara berkala (misal tiap bulan).", "Contoh: Rp10.000 setiap bulan."),
        InteractiveCard("Simpanan Sukarela", "🏦", "Tabungan Fleksibel", "Simpanan sukarela dari anggota dengan jumlah dan waktu penyetoran yang bebas, serta dapat diambil sewaktu-waktu.", "Mirip tabungan biasa di bank."),
        InteractiveCard("Dana Cadangan", "🛡️", "Benteng Keuangan", "Bagian dari SHU yang disisihkan untuk memupuk modal sendiri dan menutup kerugian bila terjadi.", "Menjaga stabilitas koperasi saat krisis.")
      ),
      miniQuiz = MiniQuiz(
        question = "Kekuasaan tertinggi dalam tata kelola koperasi berada pada...",
        options = listOf("Ketua Koperasi", "Pengawas", "Rapat Anggota", "Dinas Koperasi"),
        correctIndex = 2,
        explanation = "Rapat Anggota adalah pemegang kekuasaan tertinggi di koperasi secara demokratis."
      )
    ),

    MateriItem(
      id = 6,
      number = 6,
      icon = "🚀",
      title = "Fungsi dan Peran Koperasi",
      subtitle = "Manfaat nyata koperasi bagi anggota dan masyarakat",
      description = "Menurut UU No. 25 Tahun 1992 Pasal 4, koperasi memiliki 4 fungsi utama dalam menopang perekonomian bangsa.",
      studentDefinition = "Koperasi hadir untuk memperkuat daya saing masyarakat, mencegah rentenir, dan memastikan tidak ada pihak yang tertinggal dalam perputaran ekonomi.",
      examples = listOf(
        "Membangun & Mengembangkan Potensi" to "Membantu petani atau siswa mengembangkan potensi ekonomi agar mandiri.",
        "Meningkatkan Kualitas Hidup" to "Menyediakan barang kebutuhan dengan harga jujur dan berkualitas.",
        "Memperkokoh Perekonomian Rakyat" to "Sebagai soko guru (tiang penyangga) ekonomi Indonesia berlandaskan gotong royong.",
        "Mewujudkan Demokrasi Ekonomi" to "Membuka peluang bagi siapapun untuk berpartisipasi dan memiliki badan usaha."
      ),
      reflectionQuestion = "Bagaimana koperasi sekolah membantumu menghemat uang saku sehari-hari?",
      miniQuiz = MiniQuiz(
        question = "Mengapa koperasi disebut sebagai 'Soko Guru' perekonomian Indonesia?",
        options = listOf("Karena hanya koperasi yang boleh berdagang", "Karena koperasi menjadi tiang penyangga ekonomi kerakyatan berbasis kekeluargaan", "Karena koperasi dipimpin oleh guru di sekolah", "Karena keuntungan koperasi paling besar"),
        correctIndex = 1,
        explanation = "Istilah Soko Guru berarti tiang penyangga utama yang kokoh, di mana ekonomi dibangun atas usaha bersama."
      )
    ),

    MateriItem(
      id = 7,
      number = 7,
      icon = "🗂️",
      title = "Jenis-Jenis Koperasi",
      subtitle = "Klasifikasi koperasi berdasarkan jenis usaha dan anggotanya",
      description = "Koperasi dikelompokkan ke dalam beberapa jenis sesuai dengan fokus kegiatan usaha dan kebutuhan para anggotanya.",
      studentDefinition = "Ada 4 jenis koperasi yang paling umum kita jumpai: Konsumen, Produsen, Simpan Pinjam, dan Jasa. Masing-masing memiliki peran unik dalam melayani masyarakat.",
      examples = listOf(
        "Koperasi Konsumen" to "Menjual barang kebutuhan sehari-hari (contoh: Koperasi Sekolah, Minimarket Koperasi).",
        "Koperasi Produsen" to "Anggotanya para produsen pembuat barang (contoh: Koperasi Peternak Susu Sapi, Koperasi Pengrajin Batik).",
        "Koperasi Simpan Pinjam (KSP)" to "Melayani tabungan dan pinjaman modal usaha kecil dengan bunga ramah.",
        "Koperasi Jasa" to "Menyediakan layanan jasa seperti angkutan umum, fotokopi, atau pariwisata."
      ),
      reflectionQuestion = "Di lingkungan sekitarmu, jenis koperasi apa saja yang pernah kamu lihat?",
      interactiveCards = listOf(
        InteractiveCard("🛒 Koperasi Konsumen", "🛍️", "Penyedia Barang", "Fokus menjual barang dagangan atau kebutuhan yang diperlukan anggota dan masyarakat.", "Koperasi Sekolah yang menjual buku, pensil, dan makanan."),
        InteractiveCard("🏭 Koperasi Produsen", "🌾", "Penghasil Produk", "Anggotanya adalah pembuat produk yang bekerja sama mengolah bahan baku dan memasarkan hasil produksinya.", "Koperasi Petani Padi, Koperasi Peternak Sapi Perah."),
        InteractiveCard("💰 Koperasi Simpan Pinjam", "💳", "Layanan Keuangan", "Menghimpun simpanan dari anggota dan menyalurkannya kembali dalam bentuk pinjaman modal kerja.", "KSP memberikan pinjaman modal kepada pedagang keliling."),
        InteractiveCard("🚗 Koperasi Jasa", "🔧", "Penyedia Layanan", "Menyediakan layanan jasa non-simpan pinjam bagi anggota dan masyarakat umum.", "Koperasi Angkutan Kota (Kopata/Mikrolet), Koperasi Jasa Kebersihan.")
      ),
      miniQuiz = MiniQuiz(
        question = "Koperasi yang beranggotakan para petani yang mengolah dan menjual hasil panen bersama disebut koperasi...",
        options = listOf("Konsumen", "Produsen", "Simpan Pinjam", "Jasa"),
        correctIndex = 1,
        explanation = "Koperasi Produsen adalah koperasi yang para anggotanya menghasilkan produk/barang sendiri."
      )
    )
  )
}
