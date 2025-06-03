package com.kasir.dao;

import com.kasir.konfig.Koneksi;
import com.kasir.model.ModelPengeluaran;
import com.kasir.service.ServicePengeluaran;
import java.sql.Connection;
import java.sql.Date; // Make sure this is java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PengeluaranDAO implements ServicePengeluaran {

    private Date tanggal; // Add this attribute to store the date

    private final Connection conn;

    public PengeluaranDAO() {
        this.conn = Koneksi.getConnection();
        if (this.conn == null) {
            System.err.println("Database connection failed in Pengeluaran DAO. Check konfig.Koneksi.");
        }
    }

    // Change setTanggal to a non-static method
    public void setTanggal(Date date) {
        this.tanggal = date; // Store the date in the instance variable
    }

    @Override
    public List<ModelPengeluaran> tampilData() {
        List<ModelPengeluaran> list = new ArrayList<>();
        String sql = "SELECT id_pengeluaran, nama_pengeluaran, jenis_pengeluaran, jumlah_pengeluaran, tanggal FROM pengeluaran ORDER BY tanggal DESC";

        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                ModelPengeluaran pengeluaran = new ModelPengeluaran();
                pengeluaran.setId_pengeluaran(rs.getInt("id_pengeluaran"));
                pengeluaran.setNama_pengeluaran(rs.getString("nama_pengeluaran"));
                pengeluaran.setJenis_pengeluaran(rs.getString("jenis_pengeluaran"));
                pengeluaran.setJumlah_pengeluaran(rs.getLong("jumlah_pengeluaran"));
                setTanggal(rs.getDate("tanggal")); // Use the non-static method

                // Set the date in the ModelPengeluaran object if needed
                pengeluaran.setTanggal(this.tanggal); // Assuming ModelPengeluaran has a setTanggal method

                list.add(pengeluaran);
            }
        } catch (SQLException e) {
            System.err.println("Error in tampilData (all data): " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ModelPengeluaran> pencarianData(String keyword) {
        List<ModelPengeluaran> list = new ArrayList<>();
        String sql = "SELECT id_pengeluaran, nama_pengeluaran, jenis_pengeluaran, jumlah_pengeluaran, tanggal "
                   + "FROM pengeluaran "
                   + "WHERE jenis_pengeluaran LIKE ? OR CAST(id_pengeluaran AS CHAR) LIKE ? "
                   + "ORDER BY tanggal DESC";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, "%" + keyword + "%");
            st.setString(2, "%" + keyword + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ModelPengeluaran pengeluaran = new ModelPengeluaran();
                    pengeluaran.setId_pengeluaran(rs.getInt("id_pengeluaran"));
                    pengeluaran.setNama_pengeluaran(rs.getString("nama_pengeluaran"));
                    pengeluaran.setJenis_pengeluaran(rs.getString("jenis_pengeluaran"));
                    pengeluaran.setJumlah_pengeluaran(rs.getLong("jumlah_pengeluaran"));
                    setTanggal(rs.getDate("tanggal")); // Use the non-static method

                    // Set the date in the ModelPengeluaran object if needed
                    pengeluaran.setTanggal(this.tanggal); // Assuming ModelPengeluaran has a setTanggal method

                    list.add(pengeluaran);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in pencarianData: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void tambahData(ModelPengeluaran model) {
        throw new UnsupportedOperationException("Not supported yet. Implement INSERT logic for adding new Pengeluaran if this method is used.");
    }

    @Override
    public void perbaruiData(ModelPengeluaran model) {
        throw new UnsupportedOperationException("Not supported yet. Implement UPDATE logic for updating Pengeluaran if this method is used.");
    }

    @Override
    public void hapusData(ModelPengeluaran model) {
        throw new UnsupportedOperationException("Not supported yet. Implement DELETE logic for deleting Pengeluaran if this method is used.");
    }

    @Override
    public List<ModelPengeluaran> tampilData(Date dari, Date sampai) {
        List<ModelPengeluaran> list = new ArrayList<>();
        String sql = "SELECT id_pengeluaran, nama_pengeluaran, jenis_pengeluaran, jumlah_pengeluaran, tanggal "
                   + "FROM pengeluaran "
                   + "WHERE tanggal BETWEEN ? AND ? "
                   + "ORDER BY tanggal DESC";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setDate(1, dari);
            st.setDate(2, sampai);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ModelPengeluaran pengeluaran = new ModelPengeluaran();
                    pengeluaran.setId_pengeluaran(rs.getInt("id_pengeluaran"));
                    pengeluaran.setNama_pengeluaran(rs.getString("nama_pengeluaran"));
                    pengeluaran.setJenis_pengeluaran(rs.getString("jenis_pengeluaran"));
                    pengeluaran.setJumlah_pengeluaran(rs.getLong("jumlah_pengeluaran"));
                    setTanggal(rs.getDate("tanggal")); // Use the non-static method

                    // Set the date in the ModelPengeluaran object if needed
                    pengeluaran.setTanggal(this.tanggal); // Assuming ModelPengeluaran has a setTanggal method

                    list.add(pengeluaran);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in tampilData (date range): " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
