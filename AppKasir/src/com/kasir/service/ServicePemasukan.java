package com.kasir.service;

import com.kasir.model.ModelPemasukan;
import java.sql.Date; // Penting: import java.sql.Date jika ingin menggunakan Date di interface
import java.util.List;

public interface ServicePemasukan {

    void tambahData (ModelPemasukan model);
    void perbaruiData (ModelPemasukan model);
    void hapusData (ModelPemasukan model);

    List<ModelPemasukan> tampilData(); // Metode untuk menampilkan semua data
    List<ModelPemasukan> tampilData(Date dari, Date sampai); // Metode untuk menampilkan data berdasarkan rentang tanggal
    List<ModelPemasukan> pencarianData(String keyword);

}
