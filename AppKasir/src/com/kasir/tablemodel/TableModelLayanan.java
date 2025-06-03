package com.kasir.tablemodel;

import com.kasir.model.ModelLayanan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelLayanan extends AbstractTableModel {
    
    private List<ModelLayanan> list = new ArrayList<>();
    
    public ModelLayanan getData(int index) {
        return list.get(index);
    }
    
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelLayanan> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(ModelLayanan layanan) {
        list.add(layanan);
        fireTableRowsInserted(list.size()-1, list.size()-1);
       
        
    }
    
     public void updateData(int row, ModelLayanan layanan) {
        list.set(row, layanan);
        fireTableDataChanged();
        
        
    }
     
     public void deleteData(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        
    }
    private final String[] columnNames = {"ID", "Layanan", "ID Kategori", "Harga", "Estimasi Waktu", "Deskripsi"};

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
        ModelLayanan layanan = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return layanan.getId_layanan();
            case 1:
                return layanan.getNama_layanan();
            case 2:
                return layanan.getId_kategori();
              
            case 3:
                return layanan.getHarga();    
            case 4:
                return layanan.getEstimasi_waktu();    
            case 5:
                return layanan.getDeskripsi();    
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
    return columnNames[column];
}
}
