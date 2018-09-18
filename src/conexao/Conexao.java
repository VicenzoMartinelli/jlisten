
package conexao;

import helpers.ExceptionLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    private final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private final String URL          = "jdbc:mysql://localhost/aps_poo";
    private final String USER         = "root";
    private final String PASSWORD     = "";
    private Connection connection;
    
    public Conexao()
    {
        try {
            Class.forName(DRIVER_CLASS);
            
            connection = DriverManager.getConnection(URL,USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException ex) {
            ExceptionLogger.saveLogException(ex);
        }
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
}
