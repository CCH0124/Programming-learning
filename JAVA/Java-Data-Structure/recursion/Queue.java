package recursion;
public class Queue {
    private static final int MAX = 8;
    private int [] array = new int[MAX];
    
    /**
     * 
     * @param n 第 n 個皇后
     * @return
     */
    private boolean judge(int n) {
        for(int i=0; i<n; i++) {
            // 是否同行和對角線 
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false; 
            }
        }
        return true;
    }

    private void check(int n) {
        if( n == MAX) {
            printMap();
            return;
        }

        for (int i=0; i<MAX; i++) {
            array[n] = i;
            if(judge(n)) {
                check(n+1);
            }
        }        
    }

    private void printMap(){
        for (int i : array) {
            System.out.print(array[i]+ " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.check(0);
    }
}