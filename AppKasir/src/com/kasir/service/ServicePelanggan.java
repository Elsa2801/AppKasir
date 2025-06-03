package com.kasir.service;

import com.kasir.model.ModelPelanggan;
import java.util.List;

public interface ServicePelanggan {
    void tambahData (ModelPelanggan model);
    void perbaruiData (ModelPelanggan model);
    void hapusData (ModelPelanggan model);
    
    List<ModelPelanggan> tampilData();
    List<ModelPelanggan> pencarianData(String id);
    
    
    
}
