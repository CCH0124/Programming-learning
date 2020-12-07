package sort;

public class BubbleSort {
    private static int[] sort(int[] arr) {
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int arr[] = { 3, 10, -3, 2, 5, 8 };
        int arr2 [] = sort(arr);
        for (int i : arr2) {
            System.out.print(i + " ");
        }
    }
}