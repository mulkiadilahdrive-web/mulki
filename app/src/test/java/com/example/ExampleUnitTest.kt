package com.example

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testSHUCalculationFormula() {
    val totalSHU = 10000000.0
    val persentaseModal = 40.0
    val persentaseUsaha = 30.0
    val simpananAnggota = 2000000.0
    val totalSimpanan = 20000000.0
    val transaksiAnggota = 5000000.0
    val totalTransaksi = 40000000.0

    val jasaModal = totalSHU * (persentaseModal / 100.0) * (simpananAnggota / totalSimpanan)
    val jasaUsaha = totalSHU * (persentaseUsaha / 100.0) * (transaksiAnggota / totalTransaksi)
    val totalSHUAnggota = jasaModal + jasaUsaha

    assertEquals(400000.0, jasaModal, 0.001)
    assertEquals(375000.0, jasaUsaha, 0.001)
    assertEquals(775000.0, totalSHUAnggota, 0.001)
  }
}
