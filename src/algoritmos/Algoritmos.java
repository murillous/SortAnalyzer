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
        int n = arr.length;                     // executa 1 vez
        for(int i = 0; i < n -1; i++){          // executa n-1 vezes
            int minIndex = i;                   // executa n-1 vezes
            for(int j = i + 1; j < n; j++){     // executa n(n-1)/2 vezes
                if(arr[j] < arr[minIndex]){     // executa n(n-1)/2
                    minIndex = j;               // executa n(n-1)/2 vezes ou 0 para melhor caso
                }
            }
            if(minIndex != i){                  // executa n-1 vezes
                int temp = arr[i];              // executa n-1 vezes ou 0 para melhor caso
                arr[i] = arr[minIndex];         // executa n-1 vezes ou 0 para melhor caso
                arr[minIndex] = temp;           // executa n-1 vezes ou 0 para melhor caso
            }
        }
        // Para o melhor caso:
        // T(n) = 1 + (n-1) + (n-1) + (n(n-1)/2) + (n(n-1)/2) + (n-1)
        // T(n) = n² + 2n - 2
        // O(n²)

        // Para pior caso
        // T(n) = [Termos do melhor caso] + n(n-1)/2 + (n-1) + (n-1) + (n-1)
        // T(n) = 1 + 6(n-1) + 3(n(n-1)/2)
        // T(n) = 1 + 6n - 6 + 1.5n²-1.5n
        // T(n) = 1.5n² + 4.5n - 5
        // O(n²)
    }

    public void shellSort(int[] array) {
        int n = array.length;                               // executa 1 vez
        double k = Math.floor((Math.log(n+1)/Math.log(3))); // executa 1 vez
        int gap = (int) ((Math.pow(3,k) - 1)/2);            // executa 1 vez
        for(; gap > 0; gap = (gap-1)/3){                    // executa G (G ≈ log₃ n) vezes
            for(int i = gap; i < n; i++){                   // executa (n-gap) vezes por gap
                int key = array[i];                         // executa (n-gap) vezes por gap
                int j = i-gap;                              // executa (n-gap) vezes por gap
                while(j >= 0 && array[j] > key) {           // executa W (varia por caso)
                    array[j+gap] = array[j];                // executa W
                    j = j - gap;                            // executa W
                }
                array[j + gap] = key;                       // executa (n-gap) vezes por gap
            }
        }
        // DEFINIÇÕES:
        // G = número de gaps da sequência de Knuth ≈ log₃(n)
        // gap_i = valor do i-ésimo gap (ex: para n=10, gaps: 4, 1)
        // W = número total de execuções do while (soma de todos os gaps)
        // Σ gap_i = soma de todos os valores de gap (ex: 4 + 1 = 5)

        // Para o melhor caso:
        // T_melhor(n) = 3 + G + Σ(i=1 até G) [4×(n-gap_i) + 1×(n-gap_i)]
        // T_melhor(n) = 3 + G + 5×Σ(n-gap_i)
        // T_melhor(n) = 3 + G + 5×(G×n - Σ gap_i)
        // T_melhor(n) = 3 + log₃(n) + 5×(log₃(n)×n - 1.5n)
        // T_melhor(n) = 3 + log₃(n) + 5n×log₃(n) - 7.5n
        // T_melhor(n) ≈ 5n×log₃(n) - 7.5n + log₃(n) + 3
        // Complexidade: O(n log n)

        // Para o pior caso:
        // while executa máximo de vezes, elementos percorrem maior distância
        // W_por_gap_i ≈ Σ(j=gap_i até n-1) (j/gap_i) ≈ (n-gap_i)²/(2×gap_i)
        // W_total = Σ(i=1 até G) W_por_gap_i
        // T_pior(n) = 3 + G + Σ(i=1 até G) [4×(n-gap_i) + 1×(n-gap_i) + 3×W_por_gap_i]
        // T_pior(n) = 3 + G + Σ(i=1 até G) [5×(n-gap_i) + 3×(n-gap_i)²/(2×gap_i)]
        // T_pior(n) ≈ 5n×log₃(n) + C×n^1.5
        // Complexidade: O(n^1.5)
    }

    public void heapsort(int[] array) {
        int n = array.length;                    // executa 1 vez

        for (int i = n / 2 - 1; i >= 0; i--) {   // executa n/2 vezes
            heapify(array, n, i);                // chama n/2 vezes
        }

        for (int i = n - 1; i > 0; i--) {        // executa n-1 vezes
            int temp = array[0];                 // executa n-1 vezes
            array[0] = array[i];                 // executa n-1 vezes
            array[i] = temp;                     // executa n-1 vezes
            heapify(array, i, 0);            // chama n-1 vezes
        }
    }

    private void heapify(int[] array, int n, int i) {
        int maior = i;          // executa 1 vez
        int esq = 2 * i + 1;    // executa 1 vez
        int dir = 2 * i + 2;    // executa 1 vez

        if (esq < n && array[esq] > array[maior]) { // executa 1 vez
            maior = esq;                            // executa 1 vez ou 0 para o melhor caso
        }

        if (dir < n && array[dir] > array[maior]) { // executa 1 vez
            maior = dir;                            // executa 1 vez ou 0 para o melhor caso
        }

        if (maior != i) {               // executa 1 vez
            int troca = array[i];       // executa 1 vez ou 0 para o melhor caso
            array[i] = array[maior];    // executa 1 vez ou 0 para o melhor caso
            array[maior] = troca;       // executa 1 vez ou 0 para o melhor caso

            heapify(array, n, maior);   // chama recursivamente 1 vez ou 0 para o melhor caso
        }
    }

    // Para o melhor Caso:
    // T_melhor(n) =  1 + (n/2) + 6(n/2) + 4(n-1) + 6(n-1)
    // T_melhor(n) = 1 + 7(n/2) + 10(n-1)
    // T_melhor(n) = 3.5n + 10n - 9
    // T_melhor(n) = 13.5n - 9
    // O(n)

    // Para o Pior Caso:
    // T_pior(n) = 1 + (n/2) + 4n + 4(n-1) + 9×Σ(i=1 até n-1) log(i)
    //
    // Σ(i=1 até n-1) log(i) ≈ (n-1)×log(n-1) - (n-1)/ln(2) ≈ n×log(n)
    //
    // T_pior(n) ≈ 1 + 0.5n + 4n + 4n - 4 + 9n×log(n)
    // T_pior(n) ≈ 9n×log(n) + 8.5n - 3
    // O(n log n)
}
