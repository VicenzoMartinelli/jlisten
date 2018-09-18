package dao;

import conexao.Conexao;
import entity.Perfil;
import helpers.ExceptionLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public abstract class AbstractDAO<T extends Object>{
    
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet result;
    
    public abstract boolean add(T source);
    public abstract boolean delete(int id);
    public abstract boolean update(T source);
    public abstract ArrayList<T> getAll();
    
    protected void openConnection()
    {
        try {
            if(connection == null || connection.isClosed())
                connection = new Conexao().getConnection();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "\n" + ex.getSQLState());
            ExceptionLogger.saveLogException(ex);
        }
    }
    
    protected void closePreparedStatement()
    {
         try {
            if(preparedStatement != null || !preparedStatement.isClosed())
                preparedStatement.close();
        } catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage() + "\n" + ex.getSQLState());
        }
        
    }
    
    protected void closeConnection()
    {
        try {
            if(connection != null || !connection.isClosed())
                connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "\n" + ex.getSQLState());
            ExceptionLogger.saveLogException(ex);
        }
        
    }
}
