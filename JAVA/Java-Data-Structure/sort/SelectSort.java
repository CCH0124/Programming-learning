package sort;

import java.util.Arrays;

public class SelectSort {
    private static int[] selectionSort(int [] arr){
		for(int i=0; i<arr.length-1; i++){
			int index = i; // 初始從 i
			// 找到列表中的最小元素
			for(int j=i+1; j<arr.length; j++)
				if(arr[j] < arr[index]) // 搜尋比當前數值小的值 
					index = j;
			// swap
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
			System.out.print((i+1)+": ");
			System.out.println(Arrays.toString(arr));
		}
		return arr;
		 
	}
    public static void main(String[] args) {
        int arr[] = { 3, 10, -3, 2, 5, 8 };
        selectionSort(arr);
    }
}