package tree;

class BinaryTreeArr {
    private int[] arr;

    public BinaryTreeArr(int arr[]) {
        this.arr = arr;
    }

    public void infixOrder() {
        this.infixOrder(0);
    }
    public void preOrder() {
        this.preOrder(0);
    }
    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 
     * @param index 對應陣列 index
     */
    public void preOrder(int index) {
        if (arr.length == 0 || arr == null) {
            throw new RuntimeException("Not empty");
        }
        System.out.println(arr[index]);
        // 左遍歷
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 右遍歷
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
    public void infixOrder(int index) {
        if (arr.length == 0 || arr == null) {
            throw new RuntimeException("Not empty");
        }
        
        // 左遍歷
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        // 右遍歷
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void postOrder(int index) {
        if (arr.length == 0 || arr == null) {
            throw new RuntimeException("Not empty");
        }
        
        // 左遍歷
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 右遍歷
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}

public class ArrayBinaryTree {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        BinaryTreeArr binaryTreeArr = new BinaryTreeArr(arr);
        binaryTreeArr.preOrder();
        System.out.println("==================================================================");
        binaryTreeArr.infixOrder();
        System.out.println("==================================================================");
        binaryTreeArr.postOrder();;
    }

}
