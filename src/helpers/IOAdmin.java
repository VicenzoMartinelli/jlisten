/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class IOAdmin {

    public static final String PROJECTPATH = System.getProperty("user.dir");

    public static void copy(File origem, File destino) throws IOException {
        try {
            InputStream in = new FileInputStream(origem);
            OutputStream out = new FileOutputStream(destino);

            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght = in.read(buffer)) > 0) {
                out.write(buffer, 0, lenght);
            }
            in.close();
            out.close();
        }
        catch (IOException ex) {
            ExceptionLogger.saveLogException(ex);
            throw ex;
        }
    }

    public static void writeFile(File file, String content) {
        
        String old = readFile(file);
        
        try (FileWriter write = new FileWriter(file)) {
            try (PrintWriter print = new PrintWriter(write)) {
                
                print.write(old + content);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar a Logs de Exceções \nMsg: " + ex.getMessage());
        }
    }

    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        
        try (FileReader fileToRead = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileToRead);
            String linha = reader.readLine();

            while (linha != null) {
                sb.append(linha).append("\n");
                linha = reader.readLine();
            }

        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler os arquivos\nMsg: " + ex.getMessage());
        }
        finally{
            return sb.toString();
        }
    }
}
