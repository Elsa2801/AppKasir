package com.kasir.service;

import com.kasir.model.ModelLayanan;
import java.util.List;

public interface ServiceLayanan {
    
    void tambahData (ModelLayanan model);
    void perbaruiData (ModelLayanan model);
    void hapusData (ModelLayanan model);
    
    List<ModelLayanan> tampilData();
    List<ModelLayanan> pencarianData(String id);
    
}
