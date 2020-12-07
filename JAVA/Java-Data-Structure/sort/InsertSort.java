package sort;

import java.util.Arrays;

public class InsertSort {
    private static int[] insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int up = arr[i];
            int j = i; // If we get smaller value than valueToSort then , we stop at that index.
            while (j > 0 && arr[j - 1] > up) {
                arr[j] = arr[j - 1];
                j--;
            }

            // We will put valueToSort at that index
            arr[j] = up;

            System.out.print((i) + ": ");
            System.out.println(Arrays.toString(arr));
        }

        return arr;
    }

    public static void main(String[] args) {
        int arr[] = { 3, 10, -3, 2, 5, 8 };
        insertionSort(arr);
    }
}