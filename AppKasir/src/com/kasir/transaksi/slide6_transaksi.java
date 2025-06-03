package com.kasir.transaksi;
import com.kasir.konfig.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JPanel;

public class slide6_transaksi extends JPanel {

private PreparedStatement stat;
private ResultSet rs;
private Connection conn;
  

public slide6_transaksi() {
        initComponents();
        conn = Koneksi.getConnection();
    }
private int lanjut() {
    // Ambil data dari input pengguna
    String nama_pelanggan = txtnama.getText();
    String no_hp = txtnohp.getText();
    String gmail = txtemail.getText();
    String alamat = txtalamat.getText(); 

    if (nama_pelanggan.isEmpty() || no_hp.isEmpty() || gmail.isEmpty() || alamat.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return -1;
    }

    try {
        String sql = "INSERT INTO pelanggan (nama_pelanggan, no_telp, email, alamat) VALUES (?, ?, ?, ?)";
        PreparedStatement stat = Koneksi.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stat.setString(1, nama_pelanggan);
        stat.setString(2, no_hp);
        stat.setString(3, gmail);
        stat.setString(4, alamat);

        int affectedRows = stat.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Gagal menambahkan pelanggan, no rows affected.");
        }

        try (ResultSet rs = stat.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // Kembalikan id pelanggan
            } else {
                throw new SQLException("Gagal mendapatkan ID pelanggan.");
            }
        }
        
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saat menyimpan pelanggan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return -1;
    }
}



private int cari() {
    // Ambil data dari input pengguna
    String nama_pelanggan = txtnama.getText();
    String no_hp = txtnohp.getText(); 

    if (nama_pelanggan.isEmpty() || no_hp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return -1;
    }

    try {
        // Perbaiki query SQL, gunakan 'FROM' bukan 'form'
        String sql = "SELECT id_pelanggan FROM pelanggan WHERE nama_pelanggan = ? AND no_telp = ?";
        PreparedStatement stat = Koneksi.getConnection().prepareStatement(sql);
        stat.setString(1, nama_pelanggan);
        stat.setString(2, no_hp);
        
        // Gunakan executeQuery() untuk SELECT
        ResultSet rs = stat.executeQuery();
        
        // Cek apakah ada hasil yang ditemukan
        if (rs.next()) {
            return rs.getInt("id_pelanggan");  // Mengambil id_pelanggan dari hasil query
        } else {
            JOptionPane.showMessageDialog(this, "Pelanggan tidak ditemukan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return -1; // Jika tidak ditemukan
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saat mencari pelanggan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return -1;
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lanjut = new javax.swing.JButton();
        email = new javax.swing.JLabel();
        nohp = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txtnohp = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        alamat = new javax.swing.JLabel();
        TitleTran = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lanjut.setBackground(new java.awt.Color(239, 196, 9));
        lanjut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lanjut.setForeground(new java.awt.Color(0, 102, 102));
        lanjut.setText("Lanjut");
        lanjut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lanjutActionPerformed(evt);
            }
        });
        getContentPane().add(lanjut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 710, 140, 40));

        email.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        email.setForeground(new java.awt.Color(0, 102, 102));
        email.setText("Email");
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 520, 110, 30));

        nohp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nohp.setForeground(new java.awt.Color(0, 102, 102));
        nohp.setText("No. Hp");
        getContentPane().add(nohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, -1, 30));

        nama.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nama.setForeground(new java.awt.Color(0, 102, 102));
        nama.setText("Nama Lengkap");
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, -1, 30));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 550, 500, 40));
        getContentPane().add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 390, 500, 200));
        getContentPane().add(txtnohp, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 500, 40));
        getContentPane().add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 500, 40));

        alamat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        alamat.setForeground(new java.awt.Color(0, 102, 102));
        alamat.setText("Alamat");
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 360, -1, 30));

        TitleTran.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        TitleTran.setForeground(new java.awt.Color(0, 102, 102));
        TitleTran.setText("Transaksi");
        getContentPane().add(TitleTran, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 530, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lanjutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lanjutActionPerformed
                                      
    // Panggil fungsi cari untuk memeriksa apakah pelanggan ada di database
    int idPelanggan = cari();
    
    if (idPelanggan != -1) {
        // Pelanggan ditemukan, jangan lanjutkan eksekusi
        JOptionPane.showMessageDialog(this, "Pelanggan sudah terdaftar.", "Info", JOptionPane.INFORMATION_MESSAGE);
            slide7_transaksi1 slide7 = new slide7_transaksi1(idPelanggan);
                slide7.setVisible(true);
                dispose();
    } else {
        // Jika pelanggan tidak ditemukan, konfirmasi dulu
        int jawab = JOptionPane.showConfirmDialog(
            this,
            "Pelanggan tidak ditemukan. Apakah Anda ingin lanjut mendaftarkan pelaggan baru?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );
        
        if (jawab == JOptionPane.YES_OPTION) {
            // User memilih "Ya", lanjutkan proses
            idPelanggan = lanjut();
            
            if (idPelanggan != -1) {
                // Mengirim ID pelanggan ke slide8_transaksi tanpa membuka slide 8 secara langsung
                slide7_transaksi1 slide7 = new slide7_transaksi1(idPelanggan);
                slide7.setVisible(true);
                dispose();
            }
        } else {
            // User memilih "Tidak", batal lanjut
            JOptionPane.showMessageDialog(this, "Proses dibatalkan.", "Batal", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    }//GEN-LAST:event_lanjutActionPerformed

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
            java.util.logging.Logger.getLogger(slide6_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(slide6_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(slide6_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(slide6_transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new slide6_transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleTran;
    private javax.swing.JLabel alamat;
    private javax.swing.JLabel email;
    private javax.swing.JButton lanjut;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nohp;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnohp;
    // End of variables declaration//GEN-END:variables
}
