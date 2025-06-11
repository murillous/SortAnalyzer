package algoritmos;

import utils.AlgoritmoConstrutor;
import utils.ResultadoCsv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Algoritmos {

    int entrada;
    String sort;

    public Algoritmos(AlgoritmoConstrutor algoritmoConstrutor) {
        entrada = algoritmoConstrutor.obterEntrada();
        sort = algoritmoConstrutor.obterSort();
    }

    // ====================PARA DIFERENTES CASOS=================
    public int[] melhorCaso(){
        int[] arr = new int[entrada];
        for(int i = 0; i < entrada; i++){
            arr[i] = i;
        }
        return arr;
    }

    public int[] piorCaso() {
        int[] arr = new int[entrada];
        int n = entrada;
        for(int i = 0; i < entrada; i++){
            arr[i] = n--;
        }
        return arr;
    }

    public int[] casoMedio(){
        int[] arr = new int[entrada];
        Random random = new Random();
        for(int i = 0; i < entrada; i++){
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }

    public int[] melhorCasoHeapSort(){
        int[] arr = new int[entrada];
        for(int i = 0; i < entrada; i++){
            arr[i] = 5;
        }
        return arr;
    }

    public void executar() throws InterruptedException {
        switch (sort) {
            case "selection sort":
                executarSort();
                break;
            case "shell sort":
                executarSort();
                break;
            case "heap sort":
                executarHeapSort();
                break;
            default:
                throw new IllegalArgumentException("Algoritmo inválido: " + sort);
        }
    }

    public void executarHeapSort() throws  InterruptedException{
        int[] arrMelhorCaso = melhorCasoHeapSort();
        int[] arrPiorCaso = melhorCaso();

        Runtime runtime = Runtime.getRuntime();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        executarEMedir(runtime, formatter, arrMelhorCaso, "melhor caso");

        executarEMedir(runtime, formatter, arrPiorCaso, "pior caso");

        executarEMedirCasoMedio(runtime, formatter);
    }

    public void executarSort() throws InterruptedException {
        int[] arrMelhorCaso = melhorCaso();
        int[] arrPiorCaso = piorCaso();

        Runtime runtime = Runtime.getRuntime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        executarEMedir(runtime, formatter, arrMelhorCaso, "melhor caso");

        executarEMedir(runtime, formatter, arrPiorCaso, "pior caso");

        executarEMedirCasoMedio(runtime, formatter);
    }

    public void executarEMedir(Runtime runtime, SimpleDateFormat formatter, int[] arr, String caso)
            throws InterruptedException {

        for(int i = 0; i < 3; i++){
            runtime.gc();
            Thread.sleep(200);
        }

        runtime.gc();
        String data = formatter.format(new Date());

        double memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
        double inicio = System.nanoTime();

        executarAlgoritmo(arr.clone());

        double memoriaDepois = runtime.totalMemory() - runtime.freeMemory();
        double fim = System.nanoTime();

        double tempoExecucao = fim - inicio;
        double memoriaUsada = memoriaDepois - memoriaAntes;

        double tempoSegundos = tempoExecucao/1_000_000_000;
        double memoriaMB = memoriaUsada/(1024 * 1024);

        ResultadoCsv.salvar("resultado.csv",data, sort, entrada, caso, tempoSegundos, memoriaMB);
    }

    public void executarEMedirCasoMedio(Runtime runtime, SimpleDateFormat formatter) throws InterruptedException {

        for(int i = 0; i < 3; i++){
            runtime.gc();
            Thread.sleep(200);
        }

        String data = formatter.format(new Date());
        double mediaMemoria = 0;
        double mediaExecucao = 0;

        for(int i = 0; i < 5; i++){
            int[] arrCasoMedio = casoMedio();
            for(int j = 0; j < 3; j++){
                runtime.gc();
                Thread.sleep(200);
            }

            double memoriaAntes = runtime.totalMemory() - runtime.freeMemory();
            double inicio = System.nanoTime();

            executarAlgoritmo(arrCasoMedio.clone());

            double memoriaDepois = runtime.totalMemory() - runtime.freeMemory();
            double fim = System.nanoTime();

            double tempoExecucao = (fim - inicio)/1_000_000_000;
            double memoriaUsada = (memoriaDepois - memoriaAntes)/(1024 * 1024);

            mediaExecucao += tempoExecucao;
            mediaMemoria += memoriaUsada;
        }
        ResultadoCsv.salvar("resultado.csv", data ,sort, entrada, "caso médio", (double) mediaExecucao /5, (double) mediaMemoria /5);
    }

    public void executarAlgoritmo(int[] arr) {
        switch (sort) {
            case "selection sort":
               selectionSort(arr);
                break;
            case "shell sort":
               shellSort(arr);
                break;
            case "heap sort":
              heapsort(arr);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo inválido: " + sort);
        }
    }

    // ==============SORTS================
    public void selectionSort(int[] arr){
        int n = arr.length; // executa uma vez
        for(int i = 0; i < n -1; i++){ // executa n-1 vezes
            int minIndex = i; // executa n-1 vezes
            for(int j = i + 1; j < n; j++){// executa n(n-1)/2 vezes
                if(arr[j] < arr[minIndex]){ // executa n(n-1)/2
                    minIndex = j; // executa n(n-1)/2 vezes ou 0 para melhor caso
                }
            }
            if(minIndex != i){ // executa n-1 vezes
                int temp = arr[i]; // executa n-1 vezes ou 0 para melhor caso
                arr[i] = arr[minIndex]; // executa n-1 vezes ou 0 para melhor caso
                arr[minIndex] = temp; // executa n-1 vezes ou 0 para melhor caso
            }
        }
        // Para melhor caso:
        // T(n) = 1 + (n-1) + (n-1) + (n(n-1)/2) + (n(n-1)/2) + (n-1)
        // T(n) = n² + 2n - 2
    }

    public void shellSort(int[] array) {
        int n = array.length; // executa uma vez
        double k = Math.floor((Math.log(n+1)/Math.log(3))); // executa uma vez
        int gap = (int) ((Math.pow(3,k) - 1)/2); // executa uma vez
        for(; gap > 0; gap = (gap-1)/3){
            for(int i = gap; i < n; i++){
                int key = array[i];
                int j = i-gap;
                while(j >= 0 && array[j] > key) {
                    array[j+gap] = array[j];
                    j = j - gap;
                }
                array[j + gap] = key;
            }
        }
    }

    public void heapsort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && array[esq] > array[maior]) {
            maior = esq;
        }

        if (dir < n && array[dir] > array[maior]) {
            maior = dir;
        }

        if (maior != i) {
            int troca = array[i];
            array[i] = array[maior];
            array[maior] = troca;

            heapify(array, n, maior);
        }
    }
}
