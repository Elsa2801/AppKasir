package com.kasir.main;

import com.kasir.dao.PemasukanDAO;
import com.kasir.model.ModelPemasukan;
import com.kasir.service.ServicePemasukan;
import com.kasir.konfig.Koneksi; //Penting untuk memastikan koneksi database
import com.kasir.tablemodel.TableModelPemasukan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import com.toedter.calendar.JDateChooser;

public class MainPemasukan extends JPanel {

    // --- Deklarasi komponen JDateChooser dan JTable yang konsisten ---
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private JButton btnCari;
    private JTable tblData;

    private TableModelPemasukan tableModelPemasukan;
    private ServicePemasukan servicePemasukan;

    public MainPemasukan() {
         // Center the window

        // Inisialisasi servicePemasukan dengan penanganan error koneksi database
        try {
            servicePemasukan = new PemasukanDAO ();
            // Optional: If servicePemasukan is null due to Koneksi.getConnection() failure
            // within Pemasukan constructor, you might want to handle it here.
            // However, the Pemasukan DAO itself should ideally throw an exception
            // or the MainPemasukan constructor should rely on the Pemasukan constructor
            // to throw an exception if connection fails.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menginisialisasi layanan pemasukan atau koneksi database: " + e.getMessage(), "Error Inisialisasi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            // Exit the application if essential services cannot be initialized
            System.exit(1);
        }


        initComponents(); // Inisialisasi komponen GUI
        loadDataToTable(); // Memuat data awal ke tabel
    }

    private void initComponents() {
        this.setLayout(new BorderLayout()); // Mengatur layout untuk JFrame

        // --- Panel untuk bagian atas (input tanggal dan tombol CARI) ---
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Layout horizontal dengan jarak

        // Label dan JDateChooser Tanggal Dari
        JLabel lblTanggalDari = new JLabel("Tanggal Dari :");
        dateFrom = new JDateChooser(); // Inisialisasi JDateChooser
        dateFrom.setPreferredSize(new Dimension(120, 25)); // Set ukuran preferensi
        dateFrom.setDateFormatString("yyyy-MM-dd"); // Gunakan .setDateFormatString()

        // Label dan JDateChooser Tanggal Sampai
        JLabel lblTanggalSampai = new JLabel("Tanggal Sampai :");
        dateTo = new JDateChooser(); // Inisialisasi JDateChooser
        dateTo.setPreferredSize(new Dimension(120, 25)); // Set ukuran preferensi
        dateTo.setDateFormatString("yyyy-MM-dd"); // Gunakan .setDateFormatString()

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

        // --- Tabel untuk menampilkan data ---
        tableModelPemasukan = new TableModelPemasukan();
        tblData = new JTable(tableModelPemasukan);

        tblData.getTableHeader().setReorderingAllowed(false);
        tblData.getTableHeader().setResizingAllowed(false);
        // Optional: Set default column widths or preferred sizes for better appearance
        // tblData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // tblData.getColumnModel().getColumn(0).setPreferredWidth(50); // Example for first column

        JScrollPane scrollPane = new JScrollPane(tblData);

        // --- Menambahkan panel dan scrollPane ke JFrame ---
        add(panelTop, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // --- Action Listener untuk Tombol CARI ---
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
            // This implicitly calls servicePemasukan.tampilData() without date parameters
            List<ModelPemasukan> data = servicePemasukan.tampilData();
            tableModelPemasukan.setData(data);
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
                // If both dates are selected, filter by date range
                List<ModelPemasukan> data = servicePemasukan.tampilData(tanggalDari, tanggalSampai);
                tableModelPemasukan.setData(data);
            } else if (dateFrom.getDate() == null && dateTo.getDate() == null) {
                // Jika kedua JDateChooser kosong, tampilkan semua data
                List<ModelPemasukan> data = servicePemasukan.tampilData();
                tableModelPemasukan.setData(data);
            } else {
                // Jika hanya salah satu tanggal yang diisi, prompt the user
                JOptionPane.showMessageDialog(this, "Mohon isi kedua Tanggal Dari dan Tanggal Sampai, atau kosongkan keduanya untuk melihat semua data.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                // Optionally, clear the table if only one date is filled and the message is shown
                tableModelPemasukan.setData(new java.util.ArrayList<>());
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
                new MainPemasukan().setVisible(true);
            }
        });
    }
}