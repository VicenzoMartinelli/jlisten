package Music;

import entity.Genero;
import entity.Music;
import helpers.ExceptionLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MetadataAdmin {

    public Music GetMetaDados(File file, String newPath) {
        Music musica = null;

        try {

            InputStream input = new FileInputStream(file);
            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();

            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            long valueDuracao = Double.valueOf(metadata.get("xmpDM:duration")).longValue();

            String generoMusical = metadata.get("xmpDM:genre");
            String autor = metadata.get("meta:author");
            int nFaixa = metadata.get("xmpDM:trackNumber") == null || metadata.get("xmpDM:trackNumber").isEmpty() ? 0 : Integer.valueOf(metadata.get("xmpDM:trackNumber").split("/")[0]);
            String album = metadata.get("xmpDM:album");
            String titulo = metadata.get("title");
            int ano = metadata.get("xmpDM:releaseDate") != null || metadata.get("xmpDM:releaseDate").isEmpty() ? 0 : Integer.valueOf(metadata.get("xmpDM:releaseDate"));

            musica = new Music(new Genero(generoMusical),autor, album, nFaixa, titulo, ano, (newPath + "\\" + titulo + ".mp3"), valueDuracao);
        }
        catch (IOException | SAXException | TikaException | NumberFormatException | NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Erro ao importar as músicas! \nMsg: " + e.getMessage());
            ExceptionLogger.saveLogException(e);
        }

        return musica;
    }
}
