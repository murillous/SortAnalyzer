package algoritmos;

import utils.AlgoritmoConstrutor;
import utils.ResultadoCsv;

import java.util.Random;

public class Algoritmos {

    int entrada;
    String sort;
    int[] arr;

    public Algoritmos(AlgoritmoConstrutor algoritmoConstrutor) {
        entrada = algoritmoConstrutor.obterEntrada();
        sort = algoritmoConstrutor.obterSort();

        Random rand = new Random();
        arr = new int[entrada];
        for (int i = 0; i < entrada; i++) {
            arr[i] = rand.nextInt(10000);
        }
    }

    public void executar() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        long inicio = System.nanoTime();

        switch (sort) {
            case "Selection Sort":
                SelectionSort.selectionSort(arr);
                break;
            case "Shell Sort":
                ShellSort.shellSort(arr);
                break;
            case "Heap Sort":
                HeapSort.heapsort(arr);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo inválido: " + sort);
        }

        long fim = System.nanoTime();
        long memoriaDepois = runtime.totalMemory() - runtime.freeMemory();

        long tempoExecucao = fim - inicio;
        long memoriaUsada = memoriaDepois - memoriaAntes;



        double tempoSegundos = tempoExecucao / 1_000_000_000.0;
        double memoriaMB = memoriaUsada / (1024.0 * 1024.0);

        ResultadoCsv.salvar("resultados.csv", entrada, sort, tempoSegundos, memoriaMB);
    }
}
