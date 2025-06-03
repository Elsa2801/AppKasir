package com.kasir.model;

import java.sql.Date; // Import java.sql.Date - This is correct and necessary

public class ModelPengeluaran {
    private int id_pengeluaran;
    private String nama_pengeluaran;
    private String jenis_pengeluaran;
    private Long jumlah_pengeluaran;
    private Date tanggal; // This correctly uses java.sql.Date

    public int getId_pengeluaran() {
        return id_pengeluaran;
    }

    public void setId_pengeluaran(int id_pengeluaran) {
        this.id_pengeluaran = id_pengeluaran;
    }

    public String getNama_pengeluaran() {
        return nama_pengeluaran;
    }

    public void setNama_pengeluaran(String nama_pengeluaran) {
        this.nama_pengeluaran = nama_pengeluaran;
    }

    public String getJenis_pengeluaran() {
        return jenis_pengeluaran;
    }

    public void setJenis_pengeluaran(String jenis_pengeluaran) {
        this.jenis_pengeluaran = jenis_pengeluaran;
    }

    public Long getJumlah_pengeluaran() {
        return jumlah_pengeluaran;
    }

    public void setJumlah_pengeluaran(Long jumlah_pengeluaran) {
        this.jumlah_pengeluaran = jumlah_pengeluaran;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
    