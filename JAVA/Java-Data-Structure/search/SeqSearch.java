package search;

public class SeqSearch {
    public static int search(int [] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == target){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int arr [] = {1,9,3,6,7,2};
        int index = search(arr, 3);
        System.out.print(index);
    }
}