package com.kasir.model;

public class ModelTransaksiSmt {
    private ModelLayanan modelLayanan;
    private ModelTransaksiDetail modelTranDet;

    public ModelLayanan getModelLayanan() {
        return modelLayanan;
    }

    public void setModelLayanan(ModelLayanan modelLayanan) {
        this.modelLayanan = modelLayanan;
    }

    public ModelTransaksiDetail getModelTranDet() {
        return modelTranDet;
    }

    public void setModelTranDet(ModelTransaksiDetail modelTranDet) {
        this.modelTranDet = modelTranDet;
    }
}