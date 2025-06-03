package com.kasir.service;

import com.kasir.model.ModelTransaksiDetail;
import java.util.List;

public interface ServiceTransaksiDetail {
    
    void tambahData (ModelTransaksiDetail model);
   
    List<ModelTransaksiDetail> tampilData(String id);
    List<ModelTransaksiDetail> pencarianData(String id);
    
    String noTransaksi();
}
