package com.kasir.model;

import java.sql.Date; // Import java.sql.Date - This is correct and necessary

public class ModelPemasukan {
    private int id_transaksi;
    private int id_kategori;
    private String paket;
    private Long jumlah;
    private Date tanggal; // This correctly uses java.sql.Date

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getid_kategori() {
        return id_kategori;
    }

    public void setid_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    // Getters and Setters for 'tanggal'
    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) { // Parameter is correctly java.sql.Date
        this.tanggal = tanggal;
    }
}