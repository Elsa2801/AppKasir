package com.kasir.tablemodel;

import com.kasir.model.ModelPelanggan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelPelanggan extends AbstractTableModel {
    
    private List<ModelPelanggan> list = new ArrayList<>();
    
    public ModelPelanggan getData(int index){
        return list.get(index);
    }
    
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelPelanggan> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(ModelPelanggan model) {
        list.add(model);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        
        
    }
    
     public void updateData(int row, ModelPelanggan model) {
        list.set(row, model);
        fireTableDataChanged();
        
        
    }
     
     public void deleteData(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        
    }
     
    private final String[] columnNames = {"ID","Nama Pelanggan", "Nomor Telepon", "Alamat"}; 

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
        ModelPelanggan model = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return "      "+ model.getIdPelanggan();
            case 1:
                return model.getNamaPelanggan();
            case 2:
                return model.getTeleponPelanggan();
            case 3:
                return model.getAlamatPelanggan();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
    return columnNames[column];
}
    
    
}
