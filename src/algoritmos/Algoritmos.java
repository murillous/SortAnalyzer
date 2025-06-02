package algoritmos;

import utils.AlgoritmoConstrutor;
import utils.ResultadoCsv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Algoritmos {

    int entrada;
    String sort;
    int[] arr;

    public Algoritmos(AlgoritmoConstrutor algoritmoConstrutor) {
        entrada = algoritmoConstrutor.obterEntrada();
        sort = algoritmoConstrutor.obterSort();
        arr = new int[entrada];
    }

    public void executar() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();

        Date date = new Date();
        String dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        long inicio = System.nanoTime();

        switch (sort) {
            case "Selection Sort":
               selectionSort(arr);
                break;
            case "Shell Sort":
               shellSort(arr);
                break;
            case "Heap Sort":
              heapsort(arr);
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

        ResultadoCsv.salvar("resultados.csv", dataFormatada, sort, entrada, tempoSegundos, memoriaMB);
    }

    // ==============SORTS================
    public static void selectionSort(int[] arr){
        for(int i = 0; i < arr.length -1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    public static void shellSort(int[] array) {
        int n = array.length;
        double k = Math.floor((Math.log(n+1)/Math.log(3)));
        int gap = (int) ((Math.pow(3,k) - 1)/2);
        while(gap > 0){
            for(int i = gap; i < n; i++){
                int key = array[i];
                int j = i-gap;
                while(j >= 0 && array[j] > key) {
                    array[j+gap] = array[j];
                    j = j - gap;
                }
                array[j + gap] = key;
            }
            gap = (gap - 1)/3;
        }
    }

    public static void heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && arr[esq] > arr[maior]) {
            maior = esq;
        }

        if (dir < n && arr[dir] > arr[maior]) {
            maior = dir;
        }

        if (maior != i) {
            int troca = arr[i];
            arr[i] = arr[maior];
            arr[maior] = troca;

            heapify(arr, n, maior);
        }
    }
}
