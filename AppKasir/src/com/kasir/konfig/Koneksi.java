package com.kasir.konfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    // URL koneksi ke database MySQL
    private static final String url = "jdbc:mysql://localhost/AppsKasirTest";
    private static final String username = "root";
    private static final String password = "";
    private static Connection con;

    // Method static untuk mendapatkan koneksi
    public static Connection getConnection() {
        if (con == null) { // jika koneksi belum dibuat
            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Koneksi berhasil");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
                con = null;
            }
        }
        return con;
    }
}
