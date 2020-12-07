package sort;

import java.util.Arrays;

public class QuickSort {
    private static void quickSort(int arr[], int left, int right) {
        int l = left;
        int r = right;
        int middle = (left + right) / 2;
        int temp = 0;
        while (l < r) {
            while (arr[l] < arr[middle]) {
                l++;
            }
            while (arr[r] > arr[middle]) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == arr[middle]) {
                r--;
            }
            if (arr[r] == arr[middle]) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 3, 10, -3, 2, 5, 8 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}