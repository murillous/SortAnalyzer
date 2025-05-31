package algoritmos;

public class ShellSort {
    public static void insertionSort(int[] array) {
        int n = array.length;
        for(int i = 1; i < n; i++){
            int key = array[i];
            int j = i-1;
            while(j >= 0 && array[j] > key) {
                array[j+1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
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
}