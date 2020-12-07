package search;

public class InsertSearch {
    public static int search(int arr[], int left, int right, int target) {
        if (left > right || target < arr[0] || target > arr[arr.length - 1])
            return -1;
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
        int mid_value = arr[mid];
        if(target > mid_value){
            search(arr, mid+1, right, target);
        } else if (target < mid_value){
            search(arr, mid, mid-1, target);
        } else {
            return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println(search(arr, 0, arr.length-1, 99));
    }
}