package com.kasir.dao;

import com.kasir.konfig.Koneksi;
import com.kasir.model.ModelPelanggan;
import com.kasir.service.ServicePelanggan;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PelangganDAO implements ServicePelanggan {
    private Connection conn;
    
    public PelangganDAO(){
        conn = Koneksi.getConnection();
    }

    @Override
    public void tambahData(ModelPelanggan model) {
        System.out.println("Tambah data dipanggil: " + model.getNamaPelanggan());
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO pelanggan (namaPelanggan, teleponPelanggan, alamatPelanggan) "
                    + "VALUES (?,?,?) ";
            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNamaPelanggan());
            st.setString    (2, model.getTeleponPelanggan());
            st.setString    (3, model.getAlamatPelanggan());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void perbaruiData(ModelPelanggan model) {
        PreparedStatement st = null;
        try {
            String sql = "UPDATE pelanggan SET namaPelanggan=?, teleponPelanggan=?, alamatPelanggan=? WHERE idPelanggan=?";            
            st = conn.prepareStatement(sql);
            st.setString    (1, model.getNamaPelanggan());
            st.setString    (2, model.getTeleponPelanggan());
            st.setString    (3, model.getAlamatPelanggan());
            st.setInt       (4, model.getIdPelanggan());
            
            st.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void hapusData(ModelPelanggan model) {
        PreparedStatement st = null;
        
        String sql = "DELETE FROM pelanggan WHERE idPelanggan=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, model.getIdPelanggan());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ModelPelanggan> tampilData() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM pelanggan";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPelanggan model = new ModelPelanggan();
                model.setIdPelanggan(rs.getInt("idPelanggan"));
                model.setNamaPelanggan(rs.getString("namaPelanggan"));
                model.setTeleponPelanggan(rs.getString("teleponPelanggan"));
                model.setAlamatPelanggan(rs.getString("alamatPelanggan"));
                
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelPelanggan> pencarianData(String id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List list = new ArrayList();
        String sql = "SELECT * FROM pelanggan WHERE idPelanggan LIKE '%"+id+"%' "
                + "OR namaPelanggan LIKE '%"+id+"%'"
                + "OR teleponPelanggan LIKE '%"+id+"%'"
                + "OR alamatPelanggan LIKE '%"+id+"%'";
        
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ModelPelanggan model = new ModelPelanggan();
                model.setIdPelanggan(rs.getInt("idPelanggan"));
                model.setNamaPelanggan(rs.getString("namaPelanggan"));
                model.setTeleponPelanggan(rs.getString("teleponPelanggan"));
                model.setAlamatPelanggan(rs.getString("alamatPelanggan"));
                
                list.add(model);
}   
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
        

    
}
    

