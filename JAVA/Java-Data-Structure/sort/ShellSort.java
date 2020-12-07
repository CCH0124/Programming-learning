package sort;

import java.util.Arrays;

public class ShellSort {
    /**
     * 交換
     * 
     * @param arr
     */
    private static void shellSort(int arr[]) {
        int temp = 0;
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (arr[k] > arr[k + i]) {
                        temp = arr[k];
                        arr[k] = arr[k + i];
                        arr[k + i] = temp;
                    }
                }
            }
            System.out.print("gap -->" + (i) + ": ");
            System.out.println(Arrays.toString(arr));
        }

    }

    /**
     * 移位
     * 
     * @param arr
     */
    private static void shellSort2(int arr[]) {
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                int k = j;
                int temp = arr[k];
                if (arr[k] < arr[k - i]) {
                    while (k - i >= 0 && temp < arr[k - i]) {
                        arr[k] = arr[k - i];
                        k -= i;
                    }
                    arr[k] = temp;
                }
            }
            System.out.print("gap -->" + (i) + ": ");
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int arr[] = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        shellSort(arr);
        System.out.println("------------------------------------------------>");
        shellSort2(arr);
    }
}