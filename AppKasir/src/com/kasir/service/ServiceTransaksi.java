package com.kasir.service;

import com.kasir.model.ModelTransaksi;
import java.util.List;

public interface ServiceTransaksi {
    
    void tambahData (ModelTransaksi model);
   
    List<ModelTransaksi> tampilData();
    List<ModelTransaksi> pencarianData(String id);
    
    String noTransaksi();
}
