package com.kasir.tablemodel;

import com.kasir.model.ModelKaryawan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelKaryawan extends AbstractTableModel {
    
    private List<ModelKaryawan> list = new ArrayList<>();

    
    public ModelKaryawan getData(int index){
        return list.get(index);
    }
    
    public void clear() {
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<ModelKaryawan> list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void insertData(ModelKaryawan model) {
        list.add(model);
        fireTableRowsInserted(list.size()-1, list.size()-1);
        
        
    }
    
     public void updateData(int row, ModelKaryawan model) {
        list.set(row, model);
        fireTableDataChanged();
        
        
    }
     
     public void deleteData(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
        JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        
    }
     
    private final String[] columnNames = {"ID","Nama Karyawan", "Username","Nomor Telepon", "Alamat", "Role"}; 

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
        ModelKaryawan model = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return "      "+ model.getId_karyawan();
            case 1:
                return           model.getNama_karyawan();
            case 2:
                return           model.getUsername();
            case 3:
                return           model.getTelepon();
            case 4:
                return           model.getAlamat();
            case 5:
                return           model.getRole();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column) {
    return columnNames[column];
}
    
    
}
