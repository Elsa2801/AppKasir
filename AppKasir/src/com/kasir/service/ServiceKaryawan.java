package com.kasir.service;

import com.kasir.model.ModelKaryawan;
import java.util.List;

public interface ServiceKaryawan {
    void tambahData (ModelKaryawan model);
    void perbaruiData (ModelKaryawan model);
    void hapusData (ModelKaryawan model);
    
    List<ModelKaryawan> tampilData();
    List<ModelKaryawan> pencarianData(String id);
    
    String generateSHA256(String password);
    ModelKaryawan prosesLogin(ModelKaryawan model);
}
