package algoritmos;

public class HeapSort {

    public static void heapsort(int[] arr) {
        int n = arr.length;

        // Constroi o heap (heap máximo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        //Extrai elementos do heap um a um
        for (int i = n - 1; i > 0; i--) {
            // Move o maior para o final
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Chama heapify na raiz com o heap reduzido
            heapify(arr, i, 0);
        }
    }

    // heapficação
    private static void heapify(int[] arr, int n, int i) {
        int maior = i;           // raiz
        int esq = 2 * i + 1;     // filho esquerdo
        int dir = 2 * i + 2;     // filho direito

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
