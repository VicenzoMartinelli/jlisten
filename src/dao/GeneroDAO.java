package dao;

import com.sun.xml.internal.ws.handler.HandlerException;
import entity.Genero;
import entity.Genero;
import entity.Music;
import helpers.ExceptionLogger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GeneroDAO extends AbstractDAO<Genero> {

    private final String INSERIR = "INSERT INTO genero (nome) VALUES (?);";
    private final String SELECT = "SELECT idgenero, nome FROM genero ORDER BY nome;";
    private final String DELETE = "DELETE FROM genero WHERE idgenero = (?);";
    private final String GETONE = "SELECT idgenero, nome FROM genero WHERE idgenero = (?) ;";
    private final String EXISTS = "SELECT idgenero, nome FROM genero WHERE nome = (?) ;";
    private final String INSERTDEFAULT = "INSERT INTO genero (nome) VALUES ('Outros');";
    private final String GETDEFAULT = "SELECT idgenero, nome FROM genero WHERE nome = 'Outros';";

    @Override
    public boolean add(Genero source) {

        try {
            super.preparedStatement = connection.prepareStatement(INSERIR, Statement.RETURN_GENERATED_KEYS);

            super.preparedStatement.setString(1, source.getNome());

            super.preparedStatement.execute();

            super.result = super.preparedStatement.getGeneratedKeys();
            if (super.result.next()) {
                source.setIdGenero(super.result.getInt(1));
            }
        }
        catch (HandlerException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ExceptionLogger.saveLogException(ex);
            return false;
        }

        return true;
    }

    public Genero addDefault(Genero source) {
        source = new Genero();
        try {
            super.preparedStatement = connection.prepareStatement(INSERTDEFAULT, Statement.RETURN_GENERATED_KEYS);

            super.preparedStatement.execute();

            super.result = super.preparedStatement.getGeneratedKeys();
            if (super.result.next()) {
                source.setIdGenero(super.result.getInt(1));
                source.setNome("Outros");
            }
        }
        catch (HandlerException | SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }

        return source;
    }

    @Override
    public ArrayList<Genero> getAll() {
        ArrayList<Genero> lstGenero = new ArrayList();

        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(SELECT);

            this.result = super.preparedStatement.executeQuery(SELECT);
            while (result.next()) {

                lstGenero.add(new Genero(result.getInt("idgenero"), result.getString("nome")));
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

        return lstGenero;
    }

    @Override
    public boolean delete(int id
    ) {
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

    public Genero getGeneroById(int id) {
        super.openConnection();
        Genero genero = null;
        try {

            super.preparedStatement = connection.prepareStatement(GETONE);
            super.preparedStatement.setInt(1, id);

            super.result = preparedStatement.executeQuery();

            while (super.result.next()) {
                genero = new Genero(result.getInt("idgenero"), result.getString("nome"));
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

        return genero;
    }

    public Genero getGeneroDefault() {
        Genero genero = null;
        try {
            super.preparedStatement = connection.prepareStatement(GETDEFAULT);

            super.result = preparedStatement.executeQuery();

            while (super.result.next()) {
                genero = new Genero(result.getInt("idgenero"), result.getString("nome"));
            }
        }
        catch (SQLException ex) {
            ExceptionLogger.saveLogException(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return genero;
    }

    public Genero ifNotExistsCreate(Genero genero) {
        super.openConnection();
        try {
            if (!genero.getNome().isEmpty()) {
                super.preparedStatement = connection.prepareStatement(EXISTS);
                super.preparedStatement.setString(1, genero.getNome());

                super.result = preparedStatement.executeQuery();

                if (super.result.next()) {
                    genero = new Genero(result.getInt("idgenero"), result.getString("nome"));
                }
                else {
                    this.add(genero);
                }
            }
            else {
                genero = getGeneroDefault();
                if (genero == null) {
                    addDefault(genero);
                }
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

        return genero;
    }

    @Override
    public boolean update(Genero source) {
        throw new UnsupportedOperationException("");
    }
}
