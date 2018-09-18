package model;
// Classe responsável pela manupulação da JTable

import entity.Perfil;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PerfilTabelModel extends AbstractTableModel {

    private ArrayList<Perfil> lstPerfil;
    private String[] columns = new String[]{"Numero", "Nome"};

    public PerfilTabelModel(ArrayList<Perfil> lst) {
        this.lstPerfil = lst;
    }

    @Override
    public int getRowCount() {
        return this.lstPerfil.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Perfil c = lstPerfil.get(rowIndex);
            //c.getClass().getFields()
        switch (columnIndex) {
            case 0:
                return c.getId();
            case 1:
                return c.getNome();
            default:
                break;
        }

        return "Registro Inexistente";
    }

    public void addPerfil(Perfil perfil) {
        lstPerfil.add(perfil);
        int lastIndex = this.getRowCount() - 1;

        fireTableRowsInserted(lastIndex, lastIndex);
    }

    public void deletePerfil(int index) {
        lstPerfil.remove(index);

        fireTableRowsDeleted(index, index);
    }
    
    public void updatePerfil(int indiceLinha, Perfil perfil) {
        this.lstPerfil.set(indiceLinha, perfil);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];

    }
}
