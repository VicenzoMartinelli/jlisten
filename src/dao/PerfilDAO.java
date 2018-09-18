package dao;

import com.sun.xml.internal.ws.handler.HandlerException;
import entity.Genero;
import entity.Perfil;
import helpers.ExceptionLogger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PerfilDAO extends AbstractDAO<Perfil> {

    private final String INSERIR = "INSERT INTO perfil (nome,descricao, idgenero1, idgenero2, idgenero3 ) VALUES (?, ?, ?, ?, ?)";
    private final String SELECT = "SELECT * FROM PERFIL";
    private final String DELETE = "DELETE FROM PERFIL WHERE idPerfil = (?)";
    private final String UPDATE = "UPDATE PERFIL "
                                    + "set nome = ?, "
                                    + "descricao = ?, "
                                    + "idgenero1 = ?, "
                                    + "idgenero2 = ?, "
                                    + "idgenero3 = ? "
                                    + "WHERE idPerfil = ? ;";

    @Override
    public boolean add(Perfil source) {
        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(INSERIR, Statement.RETURN_GENERATED_KEYS);

            super.preparedStatement.setString(1, source.getNome());
            super.preparedStatement.setString(2, source.getDescricao());
            super.preparedStatement.setInt(3, source.getGeneroUm().getIdGenero());
            super.preparedStatement.setInt(4, source.getGeneroDois().getIdGenero());
            super.preparedStatement.setInt(5, source.getGeneroTres().getIdGenero());

            super.preparedStatement.execute();

            try {
                super.result = super.preparedStatement.getGeneratedKeys();
                if (super.result.next()) {
                    source.setId(super.result.getInt(1));
                }
            }
            catch (HandlerException | SQLException ex) {
                ExceptionLogger.saveLogException(ex);
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            super.closePreparedStatement();
            super.closeConnection();
        }

        return true;
    }

    //Pegar todos os registros do banco em lista
    @Override
    public ArrayList<Perfil> getAll() {
        ArrayList<Perfil> lstQuery = new ArrayList();

        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(SELECT);

            this.result = super.preparedStatement.executeQuery(SELECT);
            while (result.next()) {
                Perfil c = new Perfil(
                        result.getInt("idPerfil"),
                        result.getString("nome"),
                        result.getString("descricao")
                );

                lstQuery.add(c);
            }
        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            super.closePreparedStatement();
            super.closeConnection();
        }

        return lstQuery;
    }

    @Override
    public boolean delete(int id) {
        boolean c = true;
        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(DELETE);

            super.preparedStatement.setInt(1, id);

            super.preparedStatement.execute();

        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            c = false;
        }
        finally {
            super.closePreparedStatement();
            super.closeConnection();
        }
        return c;
    }

    public Perfil getPerfilById(int id) {
        super.openConnection();
        Perfil c = null;
        try {

            super.preparedStatement = connection.prepareStatement("SELECT p.idPerfil, p.nome, p.descricao, "
                    + "g1.idgenero, g1.nome, g2.idgenero, g2.nome, g3.idgenero, g3.nome "
                    + "FROM PERFIL p join genero g1 on p.idgenero1 = g1.idgenero "
                    + "inner join genero g2 on g2.idgenero = p.idgenero2 "
                    + "inner join genero g3 on g3.idgenero = p.idgenero3 "
                    + "WHERE idPerfil = ? ;");
            super.preparedStatement.setInt(1, id);

            super.result = preparedStatement.executeQuery();

            while (super.result.next()) {
                c = new Perfil(
                        result.getInt("p.idPerfil"),
                        result.getString("p.nome"),
                        result.getString("p.descricao"),
                        new Genero(result.getInt("g1.idgenero"), result.getString("g1.nome")),
                        new Genero(result.getInt("g2.idgenero"), result.getString("g2.nome")),
                        new Genero(result.getInt("g3.idgenero"), result.getString("g3.nome"))
                );
            }
        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        finally {
            super.closePreparedStatement();
            super.closeConnection();
        }

        return c;
    }

    @Override
    public boolean update(Perfil source) {
        boolean c = true;
        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(UPDATE);

            super.preparedStatement.setString(1, source.getNome());

            super.preparedStatement.setString(2, source.getDescricao());
            
            super.preparedStatement.setInt(3, source.getGeneroUm().getIdGenero());
            
            super.preparedStatement.setInt(4, source.getGeneroDois().getIdGenero());
            
            super.preparedStatement.setInt(5, source.getGeneroTres().getIdGenero());

            super.preparedStatement.setInt(6, source.getId());

            super.preparedStatement.execute();

        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            c = false;
        }
        finally {
            super.closePreparedStatement();
            super.closeConnection();
        }
        return c;
    }
}
