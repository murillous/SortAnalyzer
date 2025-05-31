package algoritmos;

public class HeapSort {

    public static void heapsort(int[] arr) {
        int n = arr.length;

        // 1. Constroi o heap (heap máximo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 2. Extrai elementos do heap um a um
        for (int i = n - 1; i > 0; i--) {
            // Move o maior para o final
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Chama heapify na raiz com o heap reduzido
            heapify(arr, i, 0);
        }
    }

    // Mantém a propriedade de heap
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

            // Recursivamente ajusta a subárvore
            heapify(arr, n, maior);
        }
    }

    // Exemplo de uso
    public static void main(String[] args) {
        int[] v = {4, 10, 3, 5, 1};
        heapsort(v);
        for (int num : v) {
            System.out.print(num + " ");
        }
    }
}
