package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AlgoritmoConstrutor {
    List<Integer> entradas = List.of(10, 100, 1000, 10000, 100000, 1000000,10000000);
    List<String> sorts = List.of("Selection Sort", "Shell Sort", "Heap Sort");

    int entrada;
    String sort;

    public void leitorArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha1 = br.readLine();
            String linha2 = br.readLine();

            if (linha1 != null && linha2 != null) {
                int valorEntrada = Integer.parseInt(linha1.trim());
                String sortSelecionado = linha2.trim();

                if (entradas.contains(valorEntrada) && sorts.contains(sortSelecionado)) {
                    entrada = valorEntrada;
                    sort = sortSelecionado;
                } else {
                    throw new IllegalArgumentException("Entrada ou algoritmo de ordenação inválido.");
                }
            } else {
                throw new IllegalArgumentException("Arquivo com dados incompletos.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }
    }

    public int obterEntrada() {
        return entrada;
    }

    public String obterSort() {
        return sort;
    }
}
