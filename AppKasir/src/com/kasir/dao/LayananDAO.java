package com.kasir.dao;

import com.kasir.konfig.Koneksi;
import com.kasir.model.ModelLayanan;
import com.kasir.service.ServiceLayanan;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;


public class LayananDAO implements ServiceLayanan {
    
    private Connection conn;
    
    public LayananDAO(){
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(ModelLayanan model) {
        System.out.println("Tambah data dipanggil: " + model.getNama_layanan());
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO layanan (nama_layanan, id_kategori, harga, estimasi_waktu,deskripsi) "
                    + "VALUES (?, ?, ?,?, ?) ";
            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNama_layanan());
            st.setInt       (2, model.getId_kategori());
            st.setLong      (3, model.getHarga());
            st.setString    (4, model.getEstimasi_waktu());
            st.setString    (5, model.getDeskripsi());
            st.executeUpdate();
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelLayanan model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE layanan SET nama_layanan=?, id_kategori=?, harga=?, estimasi_waktu=?,"
                    + "deskripsi=? WHERE id_layanan=?";            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNama_layanan());
            st.setInt       (2, model.getId_kategori());
            st.setLong      (3, model.getHarga());
            st.setString    (4, model.getEstimasi_waktu());
            st.setString    (5, model.getDeskripsi());
            st.setInt       (6, model.getId_layanan());
            
            st.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelLayanan model) {
        PreparedStatement st = null;
        
        String sql = "DELETE FROM layanan WHERE id_layanan=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getId_layanan());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelLayanan> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM layanan";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelLayanan layanan = new ModelLayanan();
                layanan.setId_layanan(rs.getInt("id_layanan"));
                layanan.setNama_layanan(rs.getString("nama_layanan"));
                layanan.setId_kategori(rs.getInt("id_kategori"));
                layanan.setHarga(rs.getLong("harga"));
                layanan.setEstimasi_waktu(rs.getString("estimasi_waktu"));
                layanan.setDeskripsi(rs.getString("deskripsi"));
                
                list.add(layanan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelLayanan> pencarianData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM layanan WHERE nama_layanan LIKE '%"+id+"%' "
                + "OR id_kategori LIKE '%"+id+"%'";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelLayanan layanan = new ModelLayanan();
                layanan.setId_layanan(rs.getInt("id_layanan"));
                layanan.setNama_layanan(rs.getString("nama_layanan"));
                layanan.setId_kategori(rs.getInt("id_kategori"));
                layanan.setHarga(rs.getLong("harga"));
                layanan.setEstimasi_waktu(rs.getString("estimasi_waktu"));
                layanan.setDeskripsi(rs.getString("deskripsi"));
                
                list.add(layanan);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
