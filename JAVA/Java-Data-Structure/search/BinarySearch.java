package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static int search(int arr[], int left, int right, int target){
        int mid = (left+right)/2;
        int mid_value = arr[mid];
        if (left > right) {
            return -1;
        }
        if(target > mid_value) {
            return search(arr, mid+1, right, target);
        } else if (target < mid_value) {
            return search(arr, left, mid-1, target);
        } else {
            return mid;
        }
    }
    public static List<Integer> search_multi(int arr[], int left, int right, int target){
        int mid = (left+right)/2;
        int mid_value = arr[mid];
        if (left > right) {
            return null;
        }
        if(target > mid_value) {
            return search_multi(arr, mid+1, right, target);
        } else if (target < mid_value) {
            return search_multi(arr, left, mid-1, target);
        } else {
            List<Integer> res = new ArrayList<>();
            res.add(mid);
            int temp = mid - 1;
            while(temp > 0) {
                if (arr[temp] == target){
                    res.add(temp);
                }
                temp--;
            }
            
            temp = mid + 1;
            while(temp < (arr.length-1)) {
                if(arr[temp] == target){
                    res.add(temp);
                }
                temp++;
            }
            return res;
        }
    }
    public static void main(String[] args) {
        int arr [] = {1,9,3,6,7,2};
        int index = search(arr, 0, arr.length-1, 6);
        System.out.println(index);
        System.out.println("------------------------------------------------------------------------");
        int arr1 [] = {1,3,6};
        List<Integer> res = search_multi(arr1, 0, arr1.length-1, 6);
        System.out.println(res);
    }
}