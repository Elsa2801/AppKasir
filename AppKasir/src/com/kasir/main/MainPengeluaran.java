package com.kasir.main;

import com.kasir.dao.PengeluaranDAO;
import com.kasir.model.ModelPengeluaran;
import com.kasir.service.ServicePengeluaran;
import com.kasir.konfig.Koneksi; //Penting untuk memastikan koneksi database
import com.kasir.tablemodel.TableModelPemasukan;
import com.kasir.tablemodel.TableModelPengeluaran;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;

public class MainPengeluaran extends JPanel {

    // Deklarasi komponen JDateChooser dan JTable
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private JButton btnCari;
    private JTable tblData;

    private TableModelPengeluaran tableModelPengeluaran;
    private ServicePengeluaran servicePengeluaran;

    public MainPengeluaran() {
        

        // Inisialisasi servicePengeluaran dengan penanganan error koneksi database
        try {
            servicePengeluaran = new PengeluaranDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menginisialisasi layanan pengeluaran atau koneksi database: " + e.getMessage(), "Error Inisialisasi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        initComponents(); // Inisialisasi komponen GUI
        loadDataToTable(); // Memuat data awal ke tabel
    }

    private void initComponents() {
        this.setLayout(new BorderLayout()); // Mengatur layout untuk JFrame

        // Panel untuk bagian atas (input tanggal dan tombol CARI)
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Layout horizontal dengan jarak

        // Label dan JDateChooser Tanggal Dari
        JLabel lblTanggalDari = new JLabel("Tanggal Dari :");
        dateFrom = new JDateChooser(); // Inisialisasi JDateChooser
        dateFrom.setPreferredSize(new Dimension(120, 25)); // Set ukuran preferensi
        dateFrom.setDateFormatString("yyyy-MM-dd"); // Format tanggal

        // Label dan JDateChooser Tanggal Sampai
        JLabel lblTanggalSampai = new JLabel("Tanggal Sampai :");
        dateTo = new JDateChooser(); // Inisialisasi JDateChooser
        dateTo.setPreferredSize(new Dimension(120, 25)); // Set ukuran preferensi
        dateTo.setDateFormatString("yyyy-MM-dd"); // Format tanggal

        // Tombol CARI
        btnCari = new JButton("CARI");
        btnCari.setBackground(new Color(255, 204, 0));
        btnCari.setForeground(Color.BLACK);
        btnCari.setFocusPainted(false);

        // Menambahkan komponen ke panelTop
        panelTop.add(lblTanggalDari);
        panelTop.add(dateFrom);
        panelTop.add(lblTanggalSampai);
        panelTop.add(dateTo);
        panelTop.add(btnCari);

        // Tabel untuk menampilkan data
        tableModelPengeluaran = new TableModelPengeluaran();
        tblData = new JTable(tableModelPengeluaran);
        tblData.getTableHeader().setReorderingAllowed(false);
        tblData.getTableHeader().setResizingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(tblData);

        // Menambahkan panel dan scrollPane ke JFrame
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listener untuk Tombol CARI
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariDataByTanggal();
            }
        });
    }

    private void loadDataToTable() {
        try {
            // Memuat semua data saat pertama kali dibuka
            List<ModelPengeluaran> data = servicePengeluaran.tampilData();
            tableModelPengeluaran.setData(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data awal: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void cariDataByTanggal() {
        Date tanggalDari = null;
        Date tanggalSampai = null;

        try {
            // Mengambil tanggal dari JDateChooser
            if (dateFrom.getDate() != null) {
                tanggalDari = new Date(dateFrom.getDate().getTime()); // Konversi ke java.sql.Date
            }

            if (dateTo.getDate() != null) {
                tanggalSampai = new Date(dateTo.getDate().getTime()); // Konversi ke java.sql.Date
            }

            if (tanggalDari != null && tanggalSampai != null) {
                // Jika kedua tanggal dipilih, filter berdasarkan rentang tanggal
                List<ModelPengeluaran> data = servicePengeluaran.tampilData(tanggalDari, tanggalSampai);
                tableModelPengeluaran.setData(data);
            } else if (dateFrom.getDate() == null && dateTo.getDate() == null) {
                // Jika kedua JDateChooser kosong, tampilkan semua data
                List<ModelPengeluaran> data = servicePengeluaran.tampilData();
                tableModelPengeluaran.setData(data);
            } else {
                // Jika hanya salah satu tanggal yang diisi, tampilkan peringatan
                JOptionPane.showMessageDialog(this, "Mohon isi kedua Tanggal Dari dan Tanggal Sampai, atau kosongkan keduanya untuk melihat semua data.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                tableModelPengeluaran.setData(new java.util.ArrayList<>());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mencari data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPengeluaran().setVisible(true);
            }
        });
    }
}
