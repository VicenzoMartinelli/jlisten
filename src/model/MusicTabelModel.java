package model;
// Classe responsável pela manupulação da JTable

import entity.Music;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MusicTabelModel extends AbstractTableModel {

    private ArrayList<Music> lstMusic;
    private String[] columns = new String[]{"Título", "Album", "Gênero", "Autor"};

    public MusicTabelModel(ArrayList<Music> lst) {
        this.lstMusic = lst;
    }

    @Override
    public int getRowCount() {
        return this.lstMusic.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public Music getMusic(int rowIndex) {
        return lstMusic.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Music c = lstMusic.get(rowIndex);
        //c.getClass().getFields()
        switch (columnIndex) {
            case 0:
                return c.getTitulo();
            case 1:
                return c.getAlbum();
            case 2:
                return c.getGenero().getNome();
            case 3:
                return c.getAutor();
            default:
                break;
        }

        return "Registro Inexistente";
    }

    public void addMusic(Music music) {
        lstMusic.add(music);
        int lastIndex = this.getRowCount() - 1;

        fireTableRowsInserted(lastIndex, lastIndex);
    }

    public void deleteMusic(int index) {
        lstMusic.remove(index);

        fireTableRowsDeleted(index, index);
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];

    }
}
