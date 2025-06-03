package com.kasir.model;

public class ModelLayanan {
    private int id_layanan;
    private String nama_layanan;
    private int id_kategori;
    private String jenis_id;
    private Long harga;
    private String estimasi_waktu;
    private String deskripsi;

    public int getId_layanan() {
        return id_layanan;
    }

    public void setId_layanan(int id_layanan) {
        this.id_layanan = id_layanan;
    }

    public String getNama_layanan() {
        return nama_layanan;
    }

    public void setNama_layanan(String nama_layanan) {
        this.nama_layanan = nama_layanan;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getJenis_id() {
        return jenis_id;
    }

    public void setJenis_id(String jenis_id) {
        this.jenis_id = jenis_id;
    }

    public Long getHarga() {
        return harga;
    }

    public void setHarga(Long harga) {
        this.harga = harga;
    }

    public String getEstimasi_waktu() {
        return estimasi_waktu;
    }

    public void setEstimasi_waktu(String estimasi_waktu) {
        this.estimasi_waktu = estimasi_waktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
}
