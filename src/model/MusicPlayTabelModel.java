/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Music;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vicen
 */
public class MusicPlayTabelModel extends AbstractTableModel {

    private ArrayList<Music> lstMusic;
    private String[] columns = new String[]{"Artista - Música"};

    public MusicPlayTabelModel(ArrayList<Music> lst) {
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Music c = lstMusic.get(rowIndex);
        //c.getClass().getFields()
        switch (columnIndex) {
            case 0:
                return c.getAutor() + " - " + c.getTitulo();
            default:
                break;
        }

        return "Registro Inexistente";
    }
    
    public Music getMusic(int rowIndex)
    {
        return lstMusic.get(rowIndex);
    }

    public void addMusic(Music music) {
        lstMusic.add(music);
        int lastIndex = this.getRowCount() - 1;

        fireTableRowsInserted(lastIndex, lastIndex);
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];

    }
}
