package com.kasir.service;

import com.kasir.model.ModelPengeluaran;
import java.sql.Date; // Penting: import java.sql.Date jika ingin menggunakan Date di interface
import java.util.List;

public interface ServicePengeluaran {

    void tambahData (ModelPengeluaran model);
    void perbaruiData (ModelPengeluaran model);
    void hapusData (ModelPengeluaran model);

    List<ModelPengeluaran> tampilData(); // Metode untuk menampilkan semua data
    List<ModelPengeluaran> tampilData(Date dari, Date sampai); // Metode untuk menampilkan data berdasarkan rentang tanggal
    List<ModelPengeluaran> pencarianData(String keyword);

}
