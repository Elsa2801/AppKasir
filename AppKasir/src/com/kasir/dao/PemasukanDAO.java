package com.kasir.dao;

import com.kasir.konfig.Koneksi;
import com.kasir.model.ModelPemasukan;
import com.kasir.service.ServicePemasukan;
import java.sql.Connection;
import java.sql.Date; // Make sure this is java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PemasukanDAO implements ServicePemasukan {

    private final Connection conn;

    public PemasukanDAO() {
        // It's good practice to handle potential connection errors here.
        // The MainPemasukan class already has a try-catch, but defensive programming is good.
        this.conn = Koneksi.getConnection();
        if (this.conn == null) {
            System.err.println("Database connection failed in Pemasukan DAO. Check konfig.Koneksi.");
            // Optionally, throw a runtime exception if connection is critical and must be established.
            // throw new RuntimeException("Failed to establish database connection.");
        }
    }

    @Override
    public List<ModelPemasukan> tampilData() {
    List<ModelPemasukan> list = new ArrayList<>();
    
    String sql = """
        SELECT 
            detail_transaksi.id_transaksi, 
            detail_transaksi.id_kategori, 
            detail_transaksi.paket, 
            detail_transaksi.jumlah, 
            transaksi.tanggal
        FROM 
            detail_transaksi
        JOIN 
            transaksi ON detail_transaksi.id_transaksi = transaksi.id_transaksi
        ORDER BY 
            transaksi.tanggal DESC
        """;

    try (PreparedStatement st = conn.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        
        while (rs.next()) {
            ModelPemasukan pemasukan = new ModelPemasukan();
            pemasukan.setId_transaksi(rs.getInt("id_transaksi"));
            pemasukan.setid_kategori(rs.getInt("id_kategori")); // Pastikan nama method sesuai
            pemasukan.setPaket(rs.getString("paket"));
            pemasukan.setJumlah(rs.getLong("jumlah"));
            pemasukan.setTanggal(rs.getDate("tanggal")); // Sesuai dengan tipe kolom

            list.add(pemasukan);
        }
    } catch (SQLException e) {
        System.err.println("Error in tampilData (all data): " + e.getMessage());
        e.printStackTrace();
    }

    return list;
}


    @Override
    public List<ModelPemasukan> pencarianData(String keyword) {
        List<ModelPemasukan> list = new ArrayList<>();
        // Query for searching by keyword in 'paket' or 'id_transaksi'.
        // Casting id_transaksi to CHAR for LIKE comparison.
        // Added ORDER BY tanggal DESC for consistency.
        String sql = "SELECT id_transaksi, id_kategori, paket, jumlah, tanggal "
                   + "FROM detail_transaksi "
                   + "WHERE paket LIKE ? OR CAST(id_transaksi AS CHAR) LIKE ? "
                   + "ORDER BY tanggal DESC";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%"); // Search by id_transaksi as string

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ModelPemasukan pemasukan = new ModelPemasukan();
                    pemasukan.setId_transaksi(rs.getInt("id_transaksi"));
                    pemasukan.setid_kategori(rs.getInt("id_kategori"));
                    pemasukan.setPaket(rs.getString("paket"));
                    pemasukan.setJumlah(rs.getLong("jumlah"));
                    pemasukan.setTanggal(rs.getDate("tanggal"));

                    list.add(pemasukan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in pencarianData: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void tambahData(ModelPemasukan model) {
        // This method is for adding new data, not directly related to the "LAPORAN PEMASUKAN" report view.
        // You would implement an INSERT INTO SQL statement here.
        throw new UnsupportedOperationException("Not supported yet. Implement INSERT logic for adding new Pemasukan if this method is used.");
    }

    @Override
    public void perbaruiData(ModelPemasukan model) {
        // This method is for updating existing data, not directly related to the "LAPORAN PEMASUKAN" report view.
        // You would implement an UPDATE SQL statement here.
        throw new UnsupportedOperationException("Not supported yet. Implement UPDATE logic for updating Pemasukan if this method is used.");
    }

    @Override
    public void hapusData(ModelPemasukan model) {
        // This method is for deleting data, not directly related to the "LAPORAN PEMASUKAN" report view.
        // You would implement a DELETE FROM SQL statement here.
        throw new UnsupportedOperationException("Not supported yet. Implement DELETE logic for deleting Pemasukan if this method is used.");
    }

    @Override
    public List<ModelPemasukan> tampilData(Date dari, Date sampai) {
        List<ModelPemasukan> list = new ArrayList<>();
        // This is the crucial method for the date range filter shown in the screenshot.
        // SQL query to filter data between two dates (inclusive).
        String sql = "SELECT id_transaksi, id_kategori, paket, jumlah, tanggal "
                   + "FROM detail_transaksi "
                   + "WHERE tanggal BETWEEN ? AND ? "
                   + "ORDER BY tanggal DESC"; // Order by date, newest first

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, dari);   // Set the 'dari' (from) date parameter
            st.setDate(2, sampai); // Set the 'sampai' (to) date parameter

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ModelPemasukan pemasukan = new ModelPemasukan();
                    pemasukan.setId_transaksi(rs.getInt("id_transaksi"));
                    pemasukan.setid_kategori(rs.getInt("id_kategori"));
                    pemasukan.setPaket(rs.getString("paket"));
                    pemasukan.setJumlah(rs.getLong("jumlah"));
                    pemasukan.setTanggal(rs.getDate("tanggal"));

                    list.add(pemasukan);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in tampilData (date range): " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}