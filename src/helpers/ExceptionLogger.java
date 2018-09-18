package helpers;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ExceptionLogger {

    public static final File log = new File(System.getProperty("user.dir") + "\\src\\logger.txt");
    public static void saveLogException(Exception e) {
        StringBuilder content = new StringBuilder();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        content.append("Exceção Registrada em: ")
               .append(date.format(formatter))
               .append("\nExceção: ")
               .append(e.getClass())
               .append("\nMensagem: ").append(e.getMessage())
               .append("\nDetalhes: ").append(e.getLocalizedMessage())
               .append("\n==================================================================================\n");
        
        IOAdmin.writeFile(log, content.toString());
    }
    
    
}
