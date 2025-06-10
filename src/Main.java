import algoritmos.Algoritmos;
import utils.AlgoritmoConstrutor;

import java.text.ParseException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws ParseException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Digite o caminho para o arquivo .txt");
        System.out.print("-> ");
        String caminhoArquivo = scan.next();
        AlgoritmoConstrutor algoritmoConstrutor = new AlgoritmoConstrutor();
        algoritmoConstrutor.leitorArquivo(caminhoArquivo);

        Algoritmos algoritmos = new Algoritmos(algoritmoConstrutor);
        algoritmos.executar();

    }
}