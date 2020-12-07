package tree;

import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int i = arr.length-1; i >0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 調整成一個大頂堆
     * 大頂堆的父節點要比子節點大
     * @param arr 調整陣列
     * @param i   表示非葉子節點的索引
     * @param len 表示多少個元素繼續調整
     */
    public static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < len; k = k * 2 + 1) {
            // 左子節點小於右子節點
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                k++;
            }
            // 子節點大於父節點
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int arr[] = { 4, 6, 8, 5, 9 };
        heapSort(arr);
    }
}