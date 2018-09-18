package dao;

import com.sun.xml.internal.ws.handler.HandlerException;
import entity.Genero;
import entity.Music;
import entity.Perfil;
import helpers.ExceptionLogger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MusicDAO extends AbstractDAO<Music> {

    private final String INSERIR = "INSERT INTO musica (idgenero, autor, album, numFaixa, titulo, ano, caminho, duracao) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private final String SELECT = "SELECT m.idmusica, m.autor, m.album, m.numFaixa, m.titulo, m.ano, m.caminho, m.duracao, g.idgenero, g.nome"
            + " FROM musica m natural join genero g ORDER BY m.autor, m.album, m.numFaixa";

    private final String SELECT_BY_TEXT_FILTRER = "SELECT m.idmusica, m.autor, m.album, m.numFaixa, m.titulo, m.ano, m.caminho, m.duracao, g.idgenero, g.nome "
            + "FROM musica m natural join genero g "
            + "WHERE (LOWER(m.titulo) like ? ) OR ( LOWER(m.album) like ? ) OR ( LOWER(m.autor) like ?) OR ( LOWER(g.nome) like ? )"
            + "ORDER BY m.autor, m.album, m.numFaixa;";

    private final String SELECT_BY_GENERO = "SELECT m.idmusica, m.autor, m.album, m.numFaixa, m.titulo, m.ano, m.caminho, m.duracao "
            + "FROM musica m "
            + "NATURAL JOIN genero g "
            + "WHERE exists (SELECT * from perfil where ((idgenero1 = m.idgenero) OR (idgenero2 = m.idgenero) OR (idgenero3 = m.idgenero)) AND idperfil = ? ) "
            + "ORDER BY g.nome, m.autor, m.album, m.numFaixa;";

    private final String SELECT_BY_GENERO_AND_TEXT = "SELECT m.idmusica, m.autor, m.album, m.numFaixa, m.titulo, m.ano, m.caminho, m.duracao "
            + "FROM musica m "
            + "NATURAL JOIN genero g "
            + "WHERE ((LOWER(m.titulo) like ? ) OR ( LOWER(m.album) like ? ) OR ( LOWER(m.autor) like ?)) "
            + "AND exists (SELECT * from perfil where ((idgenero1 = m.idgenero) OR (idgenero2 = m.idgenero) OR (idgenero3 = m.idgenero)) AND idperfil = ? ) "
            + "ORDER BY g.nome, m.autor, m.album, m.numFaixa;";

    private final String DELETE = "DELETE FROM MUSICA WHERE idmusica = (?)";

    private final String GETONE = "SELECT m.idmusica, m.autor, m.album, m.numFaixa, m.titulo, m.duracao, m.ano, g.idgenero, g.nome "
            + "FROM musica m natural join genero g "
            + "WHERE m.idmusica = ? ;";

    private final GeneroDAO generoDAO = new GeneroDAO();

    @Override
    public boolean add(Music source) {

        source.setGenero(generoDAO.ifNotExistsCreate(source.getGenero()));

        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(INSERIR, Statement.RETURN_GENERATED_KEYS);

            super.preparedStatement.setInt(1, source.getGenero().getIdGenero());
            super.preparedStatement.setString(2, source.getAutor());
            super.preparedStatement.setString(3, source.getAlbum());
            super.preparedStatement.setInt(4, source.getnumFaixa());
            super.preparedStatement.setString(5, source.getTitulo());
            super.preparedStatement.setInt(6, source.getAno());
            super.preparedStatement.setString(7, source.getCaminho());
            super.preparedStatement.setLong(8, source.getDuracao());

            super.preparedStatement.execute();

            try {
                super.result = super.preparedStatement.getGeneratedKeys();
                if (super.result.next()) {
                    source.setIdMusica(super.result.getInt(1));
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

    @Override
    public ArrayList<Music> getAll() {
        ArrayList<Music> lstMusic = new ArrayList();

        super.openConnection();
        try {
            super.preparedStatement = connection.prepareStatement(SELECT);

            this.result = super.preparedStatement.executeQuery();
            while (result.next()) {

                lstMusic.add(new Music(
                        result.getInt("m.idmusica"),
                        new Genero(result.getInt("g.idgenero"), result.getString("g.nome")),
                        result.getString("m.autor"),
                        result.getString("m.album"),
                        result.getInt("m.numFaixa"),
                        result.getString("m.titulo"),
                        result.getInt("m.ano"),
                        result.getString("m.caminho"),
                        result.getLong("m.duracao")
                ));
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

        return lstMusic;
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

    public Music getMusicById(int id) {
        super.openConnection();
        Music music = null;
        try {

            super.preparedStatement = connection.prepareStatement(GETONE);
            super.preparedStatement.setInt(1, id);

            super.result = preparedStatement.executeQuery();

            while (super.result.next()) {
                music = new Music(
                        result.getInt("m.idmusica"),
                        new Genero(result.getInt("g.idgenero"), result.getString("g.nome")),
                        result.getString("m.autor"),
                        result.getString("m.album"),
                        result.getInt("m.numFaixa"),
                        result.getString("m.titulo"),
                        result.getInt("m.ano"),
                        result.getString("m.caminho"),
                        result.getLong("m.duracao")
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

        return music;
    }

    @Override
    public boolean update(Music source) {
        throw new UnsupportedOperationException("");
    }

    public ArrayList<Music> getAllByFiltrer(String text) {
        ArrayList<Music> lstMusic = new ArrayList();

        super.openConnection();
        try {

            super.preparedStatement = connection.prepareStatement(SELECT_BY_TEXT_FILTRER);

            String filtrer = "%" + text + "%";

            super.preparedStatement.setString(1, filtrer);
            super.preparedStatement.setString(2, filtrer);
            super.preparedStatement.setString(3, filtrer);
            super.preparedStatement.setString(4, filtrer);

            this.result = super.preparedStatement.executeQuery();
            while (result.next()) {

                lstMusic.add(new Music(
                        result.getInt("m.idmusica"),
                        new Genero(result.getInt("g.idgenero"), result.getString("g.nome")),
                        result.getString("m.autor"),
                        result.getString("m.album"),
                        result.getInt("m.numFaixa"),
                        result.getString("m.titulo"),
                        result.getInt("m.ano"),
                        result.getString("m.caminho"),
                        result.getLong("m.duracao")
                ));
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

        return lstMusic;
    }

    public ArrayList<Music> getAllByGenero(Perfil perfil, String text) {
        ArrayList<Music> lstMusic = new ArrayList();

        super.openConnection();
        try {
            String filtrer = "%" + text + "%";
            if (perfil == null) {
                super.preparedStatement = connection.prepareStatement(SELECT_BY_TEXT_FILTRER);
                super.preparedStatement.setString(4, filtrer);
                super.preparedStatement.setString(1, filtrer);
                super.preparedStatement.setString(2, filtrer);
                super.preparedStatement.setString(3, filtrer);
            }
            else if (text.isEmpty()) {
                super.preparedStatement = connection.prepareStatement(SELECT_BY_GENERO);
                super.preparedStatement.setInt(1, perfil.getId());

            }
            else {
                super.preparedStatement = connection.prepareStatement(SELECT_BY_GENERO_AND_TEXT);

                super.preparedStatement.setString(1, filtrer);
                super.preparedStatement.setString(2, filtrer);
                super.preparedStatement.setString(3, filtrer);
                super.preparedStatement.setInt(4, perfil.getId());
            }

            this.result = super.preparedStatement.executeQuery();
            while (result.next()) {

                lstMusic.add(new Music(
                        result.getInt("m.idmusica"),
                        null,
                        result.getString("m.autor"),
                        result.getString("m.album"),
                        result.getInt("m.numFaixa"),
                        result.getString("m.titulo"),
                        result.getInt("m.ano"),
                        result.getString("m.caminho"),
                        result.getLong("m.duracao")
                ));
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

        return lstMusic;
    }
}
