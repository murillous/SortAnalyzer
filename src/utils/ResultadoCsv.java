package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ResultadoCsv {

    public static void salvar(String caminho, int entrada, String algoritmo, double tempoSegundos, double memoriaMB) {
        try {
            File arquivo = new File(caminho);
            boolean arquivoNovo = !arquivo.exists() || arquivo.length() == 0;

            try (FileWriter fw = new FileWriter(arquivo, true);
                 PrintWriter pw = new PrintWriter(fw)) {

                if (arquivoNovo) {
                    pw.println("Entrada,Algoritmo,Tempo(s),Memoria(MB)");
                }

                pw.printf(Locale.US, "%d,%s,%.6f,%.2f%n", entrada, algoritmo, tempoSegundos, memoriaMB);
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar resultado no CSV", e);
        }
    }
}
