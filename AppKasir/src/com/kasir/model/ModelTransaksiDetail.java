package com.kasir.model;

public class ModelTransaksiDetail {
    private ModelTransaksi modelTransaksi;
    private ModelLayanan modelLayanan;
    private ModelKategori modelKategori;
    private int jumlah;
    private String status;

    public ModelTransaksi getModelTransaksi() {
        return modelTransaksi;
    }

    public void setModelTransaksi(ModelTransaksi modelTransaksi) {
        this.modelTransaksi = modelTransaksi;
    }

    public ModelLayanan getModelLayanan() {
        return modelLayanan;
    }

    public void setModelLayanan(ModelLayanan modelLayanan) {
        this.modelLayanan = modelLayanan;
    }

    public ModelKategori getModelKategori() {
        return modelKategori;
    }

    public void setModelKategori(ModelKategori modelKategori) {
        this.modelKategori = modelKategori;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
