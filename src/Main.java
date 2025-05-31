import utils.AlgoritmoConstrutor;
//import static algoritmos.ShellSort.shellSort;

public class Main{
    public static void main(String[] args) {
        String caminhoArquivo = "src/resource.txt";
        AlgoritmoConstrutor algoritmoConstrutor = new AlgoritmoConstrutor();
        algoritmoConstrutor.leitorArquivo(caminhoArquivo);


        System.out.println(algoritmoConstrutor.obterEntrada());
        System.out.println(algoritmoConstrutor.obterSort());
    }
}