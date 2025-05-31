package algoritmos;

public class HeapSort {
    public static void createHeap(int[] arr){
        int n = arr.length;
        for(int i = 1; i < n; ++i){
            int f = i;
            while(f > 0 && arr[(f-1)/2] < arr[f]){
                int t = arr[(f-1)/2];
                arr[(f-1)/2] = arr[f];
                arr[f] = t;
                f = (f-1)/2;
            }
        }
    }
}
