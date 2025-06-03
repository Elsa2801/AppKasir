package com.kasir.tablemodel;

import com.kasir.model.ModelPemasukan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelPemasukan extends AbstractTableModel {

    private List<ModelPemasukan> list = new ArrayList<>();

    private final String[] columnNames = {"Id Transaksi", "Id Kategori", "Paket", "Jumlah", "Tanggal"};

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ModelPemasukan pemasukan = list.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return pemasukan.getId_transaksi();
            case 1: 
                return pemasukan.getid_kategori();
            case 2: 
                return pemasukan.getPaket();
            case 3: 
                return pemasukan.getJumlah();
            case 4: 
                return pemasukan.getTanggal();
            default: 
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setData(List<ModelPemasukan> data) {
    list.clear();
    list.addAll(data);
    fireTableDataChanged();
}
    }
