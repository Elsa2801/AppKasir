package com.kasir.dao;

import com.kasir.konfig.Koneksi;
import com.kasir.model.ModelKategori;
import com.kasir.service.ServiceKategori;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class KategoriDAO implements ServiceKategori {
    private Connection conn;
    
    public KategoriDAO(){
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(ModelKategori model) {
        System.out.println("Tambah data dipanggil: " + model.getNama_kategori());
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO kategori_layanan (nama_kategori) "
                    + "VALUES (?) ";
            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNama_kategori());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelKategori model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE kategori_layanan SET nama_kategori=? WHERE id_kategori=?";            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNama_kategori());
            st.setInt       (2, model.getId_kategori());
            
            st.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelKategori model) {
        PreparedStatement st = null;
        
        String sql = "DELETE FROM kategori_layanan WHERE id_kategori=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getId_kategori());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelKategori> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM kategori_layanan";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                kategori.setId_kategori(rs.getInt("id_kategori"));
                kategori.setNama_kategori(rs.getString("nama_kategori"));
                
                
                list.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelKategori> pencarianData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM kategori_layanan WHERE nama_kategori LIKE '%"+id+"%' "
                + "OR id_kategori LIKE '%"+id+"%'";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                kategori.setId_kategori(rs.getInt("id_kategori"));
                kategori.setNama_kategori(rs.getString("nama_kategori"));
                
                list.add(kategori);
}   
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
        

    @Override

    public boolean validasiNamaKategori(ModelKategori model) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        boolean valid = false;
        
        String sql = "SELECT nama_kategori FROM kategori_layanan WHERE nama_kategori LIKE BINARY ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, model.getNama_kategori());
            rs = st.executeQuery();
            
            if (rs.next()){
                valid = false;
            } else {
                valid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if (st != null){
                    st.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return valid;
    }

    @Override
    public List<ModelKategori> ambilKategori() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT id_kategori, nama_kategori FROM kategori_layanan";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelKategori kategori = new ModelKategori();
                kategori.setId_kategori(rs.getInt("id_kategori"));
                kategori.setNama_kategori(rs.getString("nama_kategori"));
                
                
                list.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String ambilIDKategori(int id) {
        String namaKategori = null;
        
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "SELECT nama_kategori FROM kategori_layanan WHERE id_kategori =?";
        
        try{
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                namaKategori = rs.getString("nama_kategori");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namaKategori;
    }
}
    

