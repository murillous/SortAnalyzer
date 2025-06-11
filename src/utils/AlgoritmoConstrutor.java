package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AlgoritmoConstrutor {
    List<Integer> entradas = List.of(100, 1000, 10000, 100000, 1000000,10000000);
    List<String> sorts = List.of("selection sort", "shell sort", "heap sort");

    int entrada;
    String sort;
    public void leitorArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha1 = br.readLine();
            String linha2 = br.readLine();

            if (!(linha1 != null && linha2 != null)) {
                System.out.println("Arquivo de entrada incompleto!");
                System.exit(0);
            }

            int valorEntrada = Integer.parseInt(linha1.trim());
            String sortSelecionado = linha2.trim();

            if (!(entradas.contains(valorEntrada) && sorts.contains(sortSelecionado.toLowerCase()))) {
                System.out.println("Entrada ou Sorts inválidos!");
                System.out.println("Entradas válidas: ");

                for(Integer num: entradas){
                    System.out.printf("%d, ",num);
                }

                System.out.println("\nSorts válidos: ");
                for(String sort: sorts){
                    System.out.printf("%s, ",sort);
                }
                System.exit(0);
            }

            entrada = valorEntrada;
            sort = sortSelecionado;
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public int obterEntrada() {
        return entrada;
    }

    public String obterSort() {
        return sort;
    }
}
