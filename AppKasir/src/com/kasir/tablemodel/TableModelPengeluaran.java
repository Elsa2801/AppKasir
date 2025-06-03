package com.kasir.tablemodel;

import com.kasir.model.ModelPengeluaran;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelPengeluaran extends AbstractTableModel {

    private List<ModelPengeluaran> list = new ArrayList<>();

    private final String[] columnNames = {"Id_pengeluaran", "nama_pengeluaran", "jenis_pengeluaran", "jumlah_pengeluaran", "tanggal"};

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
        ModelPengeluaran pengeluaran = list.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return pengeluaran.getId_pengeluaran();
            case 1: 
                return pengeluaran.getNama_pengeluaran();
            case 2: 
                return pengeluaran.getJenis_pengeluaran();
            case 3: 
                return pengeluaran.getJumlah_pengeluaran();
            case 4: 
                return pengeluaran.getTanggal();
            default: 
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setData(List<ModelPengeluaran> data) {
    list.clear();
    list.addAll(data);
    fireTableDataChanged();
}
    }
