package sort;

import java.util.Arrays;

public class RadixSort {
    private static int getNumLen(int n){
        return (int) (Math.log10(n) + 1);
    }
    private static int getMaxValue(int[] arr){
        int max = arr[0];
        for (int i=1; i<arr.length;i++){
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    public static void radixSort(int[] arr){
        int[][] bucket = new int[10][arr.length]; // 這邊的一個桶大小會影響記憶體
        int[] bucketEleCount = new int[10];
        int maxLen = getNumLen(getMaxValue(arr));
        for (int i=0; i<maxLen; i++){
            for (int j = 0; j < arr.length; j++) {
                int digiOfEle = arr[j]/((int)Math.pow(10, i))%10; // 取每個數字對應元素
                bucket[digiOfEle][bucketEleCount[digiOfEle]] = arr[j];
                bucketEleCount[digiOfEle]++;
            }
            int index = 0;
            for (int k = 0; k < bucketEleCount.length; k++) {
                if(bucketEleCount[k] != 0){
                    for (int l = 0; l < bucketEleCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketEleCount[k] = 0;
            }
            System.out.println(Arrays.toString(arr));
        }
        
    }
    public static void main(String[] args) {
        int arr[] = { 623, 40, 83, 22, 5, 128 };
        radixSort(arr);
    }
}