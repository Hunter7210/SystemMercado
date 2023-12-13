package logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegistroSistema {
        private static final String CAMINHO_ARQUIVO = "registro.txt";
        private static File arquivo = new File(CAMINHO_ARQUIVO);

    public RegistroSistema(){
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registroOperacao(String mensagem) {
        try{

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(mensagem);
            bw.newLine();
        
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}