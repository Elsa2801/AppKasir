package com.kasir.transaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public final class slide7_transaksi1 extends javax.swing.JFrame {
    private ArrayList<ite> daftarItem = new ArrayList<>();
    private int idPelanggan;
    private int id;
    private PreparedStatement stat;
    private ResultSet rs;
    private String note = "";
    private String metode = "";
    Koneksi K = new Koneksi();

    public slide7_transaksi1() {
        initComponents();
        K.connect();
        value();
        kategori();
    }

    public slide7_transaksi1(int idPelanggan) {
        initComponents();
        K.connect();
        this.idPelanggan = idPelanggan;
        this.id = idPelanggan;
        value();
        kategori();
    }

    private void value() {
        System.out.println("ID Pelanggan: " + id);
        try {
            this.stat = K.getCon().prepareStatement("SELECT nama_paket FROM paket");
            this.rs = this.stat.executeQuery();

            DefaultComboBoxModel<String> isi = new DefaultComboBoxModel<>();
            System.out.println("Mengambil data paket dari database:");

            while (rs.next()) {
                String paket = rs.getString("nama_paket");
                System.out.println(paket);
                isi.addElement(paket);
            }

            if (isi.getSize() == 0) {
                System.out.println("Tidak ada data paket ditemukan.");
            }

            jenis_layanan.setModel(isi);
            System.out.println("ComboBox jenis_layanan diperbarui.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saat mengambil data paket: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stat != null) stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void kategori() {
        try {
            String selectedPaket = (String) jenis_layanan.getSelectedItem();
            if (selectedPaket == null || selectedPaket.isEmpty()) {
                kategori.removeAllItems();
                return;
            }

            String query = "SELECT kategori.nama_kategori " +
                          "FROM kategori " +
                          "INNER JOIN paket ON kategori.paket_id = paket.id_paket " +
                          "WHERE paket.nama_paket = ?";
            stat = K.getCon().prepareStatement(query);
            stat.setString(1, selectedPaket);
            rs = stat.executeQuery();

            kategori.removeAllItems();
            while (rs.next()) {
                kategori.addItem(rs.getString("nama_kategori"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saat memuat kategori: " + e.getMessage(), "Kesalahan Database", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stat != null) stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void input() {
        String paket = (String) jenis_layanan.getSelectedItem();
        String layanan = (String) kategori.getSelectedItem();
        String jumlahText = banyak.getText().trim();
        note = catatan1.getText() != null ? catatan1.getText() : "";
        metode = (String) pengerjaan.getSelectedItem();

        // Validasi input
        if (paket == null || paket.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih jenis layanan terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (layanan == null || layanan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (jumlahText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kuantitas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int jumlah;
        try {
            jumlah = Integer.parseInt(jumlahText);
            if (jumlah <= 0) {
                JOptionPane.showMessageDialog(this, "Kuantitas harus lebih dari 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kuantitas harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (metode == null || metode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih metode pengiriman.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ite newItem = new ite(paket, layanan, jumlah, note, metode);
        daftarItem.add(newItem);

        JOptionPane.showMessageDialog(this, "Item ditambahkan ke daftar.");
        tabel();
    }

    private void tabel() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Paket", "Layanan", "Jumlah"}
        );
        keranjang.setModel(model);

        for (ite item : daftarItem) {
            model.addRow(new Object[]{
                item.getPaket(),
                item.getLayanan(),
                item.getJumlah()
            });
        }
    }

    public static class ite {
        String paket;
        String kategori;
        int jumlah;
        String catatan;
        String metode;

        public ite(String paket, String kategori, int jumlah, String catatan, String metode) {
            this.paket = paket;
            this.kategori = kategori;
            this.jumlah = jumlah;
            this.catatan = catatan;
            this.metode = metode;
        }

        public String getPaket() {
            return paket;
        }

        public String getLayanan() {
            return kategori;
        }

        public int getJumlah() {
            return jumlah;
        }

        public String getNote() {
            return catatan;
        }

        public String getMetode() {
            return metode;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typing_pengerjaan = new javax.swing.JLabel();
        simpan1 = new javax.swing.JButton();
        show_satuan = new javax.swing.JTextField();
        pengerjaan = new javax.swing.JComboBox<>();
        typing_satuan = new javax.swing.JLabel();
        kategori = new javax.swing.JComboBox<>();
        jenis_layanan = new javax.swing.JComboBox<>();
        simpan = new javax.swing.JButton();
        typing_alamat = new javax.swing.JLabel();
        typing_email = new javax.swing.JLabel();
        catatan = new javax.swing.JScrollPane();
        catatan1 = new javax.swing.JTextArea();
        typing_nohp = new javax.swing.JLabel();
        banyak = new javax.swing.JTextField();
        typing_nama = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        sementara = new javax.swing.JScrollPane();
        keranjang = new javax.swing.JTable();
        icon_dashboard = new javax.swing.JLabel();
        icon_pesanan = new javax.swing.JLabel();
        icon_transaksi = new javax.swing.JLabel();
        icon_notifikasi = new javax.swing.JLabel();
        icon_keluar = new javax.swing.JLabel();
        icon_laporan = new javax.swing.JLabel();
        icon_karyawan = new javax.swing.JLabel();
        profil = new javax.swing.JLabel();
        welcome = new javax.swing.JLabel();
        typing_dashboard = new javax.swing.JLabel();
        typing_pesanan = new javax.swing.JLabel();
        typing_transaksi = new javax.swing.JLabel();
        typing_laporan = new javax.swing.JLabel();
        typing_karyawan = new javax.swing.JLabel();
        typing_nootif = new javax.swing.JLabel();
        typing_keluar = new javax.swing.JLabel();
        background_putih = new javax.swing.JLabel();
        background_kuning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typing_pengerjaan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_pengerjaan.setForeground(new java.awt.Color(0, 102, 102));
        typing_pengerjaan.setText("Metode Pengiriman");
        getContentPane().add(typing_pengerjaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, 180, 30));

        simpan1.setBackground(new java.awt.Color(239, 196, 9));
        simpan1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        simpan1.setForeground(new java.awt.Color(0, 102, 102));
        simpan1.setText("Lanjut");
        simpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpan1ActionPerformed(evt);
            }
        });
        getContentPane().add(simpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 710, 140, 40));

        show_satuan.setText("jTextField1");
        getContentPane().add(show_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 170, 40));

        pengerjaan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Di antar", "Di  ambil", " " }));
        pengerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengerjaanActionPerformed(evt);
            }
        });
        getContentPane().add(pengerjaan, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 350, 500, 40));

        typing_satuan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_satuan.setForeground(new java.awt.Color(0, 102, 102));
        typing_satuan.setText("Satuan");
        getContentPane().add(typing_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, 110, 30));

        kategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kategori.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kategoriItemStateChanged(evt);
            }
        });
        kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kategoriActionPerformed(evt);
            }
        });
        getContentPane().add(kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 500, 40));

        jenis_layanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jenis_layanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jenis_layananItemStateChanged(evt);
            }
        });
        jenis_layanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_layananActionPerformed(evt);
            }
        });
        getContentPane().add(jenis_layanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 500, 40));

        simpan.setBackground(new java.awt.Color(239, 196, 9));
        simpan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        simpan.setForeground(new java.awt.Color(0, 102, 102));
        simpan.setText("Lanjut");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 710, 140, 40));

        typing_alamat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_alamat.setForeground(new java.awt.Color(0, 102, 102));
        typing_alamat.setText("Kuantitas");
        getContentPane().add(typing_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 110, 30));

        typing_email.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_email.setForeground(new java.awt.Color(0, 102, 102));
        typing_email.setText("Kategori");
        getContentPane().add(typing_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, -1, 30));

        catatan1.setColumns(20);
        catatan1.setRows(5);
        catatan.setViewportView(catatan1);

        getContentPane().add(catatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 180, 510, 140));

        typing_nohp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_nohp.setForeground(new java.awt.Color(0, 102, 102));
        typing_nohp.setText("Jenis Layanan");
        getContentPane().add(typing_nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, 30));
        getContentPane().add(banyak, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 320, 40));

        typing_nama.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        typing_nama.setForeground(new java.awt.Color(0, 102, 102));
        typing_nama.setText("Catatan");
        getContentPane().add(typing_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 150, -1, 30));

        dashboard.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        dashboard.setForeground(new java.awt.Color(0, 102, 102));
        dashboard.setText("Transaksi");
        getContentPane().add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 530, 60));

        keranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        sementara.setViewportView(keranjang);

        getContentPane().add(sementara, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, -1, -1));

        icon_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_dashboard.png"))); // NOI18N
        getContentPane().add(icon_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, -200, -1, -1));

        icon_pesanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_pesanan.png"))); // NOI18N
        icon_pesanan.setText("jLabel2");
        getContentPane().add(icon_pesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -140, -1, -1));

        icon_transaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_transaksi.png"))); // NOI18N
        getContentPane().add(icon_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -140, -1, -1));

        icon_notifikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_notif.png"))); // NOI18N
        getContentPane().add(icon_notifikasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -170, -1, -1));

        icon_keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_keluar.png"))); // NOI18N
        getContentPane().add(icon_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -180, -1, -1));

        icon_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_laporan.png"))); // NOI18N
        getContentPane().add(icon_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -150, -1, -1));

        icon_karyawan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/icon_karyawan.png"))); // NOI18N
        getContentPane().add(icon_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -160, -1, -1));

        profil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/profil.png"))); // NOI18N
        getContentPane().add(profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        welcome.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        welcome.setForeground(new java.awt.Color(242, 242, 242));
        welcome.setText("Welcome, Elsa");
        getContentPane().add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        typing_dashboard.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_dashboard.setForeground(new java.awt.Color(242, 242, 242));
        typing_dashboard.setText("Dashboard");
        getContentPane().add(typing_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 252, -1, 30));

        typing_pesanan.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_pesanan.setForeground(new java.awt.Color(242, 242, 242));
        typing_pesanan.setText("Pesanan");
        getContentPane().add(typing_pesanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, -1, 30));

        typing_transaksi.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_transaksi.setForeground(new java.awt.Color(242, 242, 242));
        typing_transaksi.setText("Transaksi");
        getContentPane().add(typing_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, -1, 30));

        typing_laporan.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_laporan.setForeground(new java.awt.Color(242, 242, 242));
        typing_laporan.setText("Laporan");
        getContentPane().add(typing_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, -1, 30));

        typing_karyawan.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_karyawan.setForeground(new java.awt.Color(242, 242, 242));
        typing_karyawan.setText("Karyawan");
        getContentPane().add(typing_karyawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 580, -1, 30));

        typing_nootif.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_nootif.setForeground(new java.awt.Color(242, 242, 242));
        typing_nootif.setText("Notifikasi");
        getContentPane().add(typing_nootif, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 650, -1, 40));

        typing_keluar.setFont(new java.awt.Font("Consolas", 1, 22)); // NOI18N
        typing_keluar.setForeground(new java.awt.Color(242, 242, 242));
        typing_keluar.setText("Keluar");
        getContentPane().add(typing_keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 740, -1, 30));

        background_putih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide3_dashboard/background putih.png"))); // NOI18N
        getContentPane().add(background_putih, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        background_kuning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/slide1_login/background kuning.png"))); // NOI18N
        getContentPane().add(background_kuning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 1030));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jenis_layananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_layananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenis_layananActionPerformed

    private void kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kategoriActionPerformed

    private void pengerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengerjaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengerjaanActionPerformed

    private void jenis_layananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jenis_layananItemStateChanged
       if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        kategori();
    }
    }//GEN-LAST:event_jenis_layananItemStateChanged

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        input();
    }//GEN-LAST:event_simpanActionPerformed

    private void kategoriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kategoriItemStateChanged
        String satuan = (String) kategori.getSelectedItem();
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED){
             try {
                 String sql = "SELECT satuan from kategori WHERE nama_kategori=?";
                  PreparedStatement stat = K.getCon().prepareStatement(sql);
                  stat.setString(1, satuan );
                           ResultSet hasil = stat.executeQuery();
                           if (hasil.next()) {
                               show_satuan.setText(hasil.getString("satuan"));
                     
                 }
             } catch (Exception e) {
             }
         }
    }//GEN-LAST:event_kategoriItemStateChanged

    private void simpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpan1ActionPerformed
     
      //  int id_karyawan = Session.getIdKaryawan();
        if (daftarItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Daftar item kosong. Tambahkan item terlebih dahulu.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Log untuk debugging
        System.out.println("Mengirim daftarItem: " + daftarItem.size() + " item");
        for (ite item : daftarItem) {
            System.out.println("Paket: " + item.getPaket() + ", Layanan: " + item.getLayanan() + ", Jumlah: " + item.getJumlah() +
                               ", Note: " + item.getNote() + ", Metode: " + item.getMetode());
        }

        slide8_transaksi2 pembayaran = new slide8_transaksi2(daftarItem, id, note, metode);
        pembayaran.setVisible(true);
        dispose();
    }//GEN-LAST:event_simpan1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(slide7_transaksi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(slide7_transaksi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(slide7_transaksi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(slide7_transaksi1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new slide7_transaksi1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_kuning;
    private javax.swing.JLabel background_putih;
    private javax.swing.JTextField banyak;
    private javax.swing.JScrollPane catatan;
    private javax.swing.JTextArea catatan1;
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel icon_dashboard;
    private javax.swing.JLabel icon_karyawan;
    private javax.swing.JLabel icon_keluar;
    private javax.swing.JLabel icon_laporan;
    private javax.swing.JLabel icon_notifikasi;
    private javax.swing.JLabel icon_pesanan;
    private javax.swing.JLabel icon_transaksi;
    private javax.swing.JComboBox<String> jenis_layanan;
    private javax.swing.JComboBox<String> kategori;
    private javax.swing.JTable keranjang;
    private javax.swing.JComboBox<String> pengerjaan;
    private javax.swing.JLabel profil;
    private javax.swing.JScrollPane sementara;
    private javax.swing.JTextField show_satuan;
    private javax.swing.JButton simpan;
    private javax.swing.JButton simpan1;
    private javax.swing.JLabel typing_alamat;
    private javax.swing.JLabel typing_dashboard;
    private javax.swing.JLabel typing_email;
    private javax.swing.JLabel typing_karyawan;
    private javax.swing.JLabel typing_keluar;
    private javax.swing.JLabel typing_laporan;
    private javax.swing.JLabel typing_nama;
    private javax.swing.JLabel typing_nohp;
    private javax.swing.JLabel typing_nootif;
    private javax.swing.JLabel typing_pengerjaan;
    private javax.swing.JLabel typing_pesanan;
    private javax.swing.JLabel typing_satuan;
    private javax.swing.JLabel typing_transaksi;
    private javax.swing.JLabel welcome;
    // End of variables declaration//GEN-END:variables
}
