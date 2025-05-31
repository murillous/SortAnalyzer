import algoritmos.Algoritmos;
import utils.AlgoritmoConstrutor;

public class Main{
    public static void main(String[] args) {
        String caminhoArquivo = "src/resource.txt";
        AlgoritmoConstrutor algoritmoConstrutor = new AlgoritmoConstrutor();
        algoritmoConstrutor.leitorArquivo(caminhoArquivo);

        Algoritmos algoritmos = new Algoritmos(algoritmoConstrutor);
        algoritmos.executar();
    }
}