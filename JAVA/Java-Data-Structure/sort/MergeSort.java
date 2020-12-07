package sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 
     * @param arr   排序該陣列
     * @param left  左邊初始索引
     * @param mid   中間索引
     * @param right 右邊索引
     * @param temp
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 左邊初始索引
        int j = mid + 1; // 右邊初始索引
        int temp_index = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[temp_index] = arr[i];
                temp_index++;
                i++;
            } else {
                temp[temp_index] = arr[j];
                temp_index++;
                j++;
            }
        }
        // 還有剩餘元素
        while (i <= mid) {
            temp[temp_index] = arr[i];
            temp_index++;
            i++;
        }
        while (j <= right) {
            temp[temp_index] = arr[j];
            temp_index++;
            j++;
        }

        temp_index = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[temp_index];
            temp_index++;
            tempLeft++;
        }
    }

    public static void main(String[] args) {
        int arr[] = { 3, 10, -3, 2, 5, 8 };
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }
}