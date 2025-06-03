import algoritmos.Algoritmos;
import utils.AlgoritmoConstrutor;

import java.text.ParseException;

public class Main{
    public static void main(String[] args) throws ParseException, InterruptedException {
        String caminhoArquivo = "resource.txt";
        AlgoritmoConstrutor algoritmoConstrutor = new AlgoritmoConstrutor();
        algoritmoConstrutor.leitorArquivo(caminhoArquivo);

        Algoritmos algoritmos = new Algoritmos(algoritmoConstrutor);
        algoritmos.executar();

    }
}