package recursion;
public class Labyrinth {
    private int [][] map;
    /**
     * @param map
     */
    public Labyrinth(int[][] map) {
        this.map = map;
    }

    public void setAroundWall() {
        // row 
        for(int i=0; i<7; i++){
            map[0][i] = 1;
            map[map.length-1][i] = 1;
        }
        // col
        for(int i=0; i<8; i++) {
            map[i][0] = 1;
            map[i][map[0].length-1] = 1;
        }
    }
    public void setOtherWall(int row, int col) {
        map[row][col] = 1;
    }
    
    public void printMap() {
        for(int i=0; i<map.length; i++ ) {
            for(int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    /**
     * 0 表示未走過
     * 1 表示牆
     * 2 表示通路可以走
     * 3 表示位置已走過，但不通
     * 策略 下 右 上 左，不同策略效果不一樣
     * @param row 起始位置 row
     * @param col 起始位置 col
     * @return 如果有路返回 true，否則 false
     */
    private boolean setWay(int row, int col) {
        int end = map[map.length-2][map[0].length-2];
        if (end == 2) {
            return true;
        } else {
            if(map[row][col] == 0){
                map[row][col] = 2;
                
                if(setWay(row+1, col)) { // down
                    return true;
                } else if (setWay(row, col+1)) {// right
                    return true;
                } else if (setWay(row-1, col)) { // up
                    return true;
                } else if (setWay(row, col-1)){ // left
                    return true;
                } else {
                    map[row][col] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public void start(int row, int col){
        if (map[row][col] == 1 || map[row][col] == 2) {
            throw new RuntimeException("start set eror");
        }
        setWay(row, col);
        
        for(int i=0; i<map.length; i++ ) {
            for(int j=0; j<map[0].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // int[][] map = new int[8][7];
        Labyrinth labyrinth = new Labyrinth(new int[8][7]);
        labyrinth.setAroundWall();
        labyrinth.setOtherWall(2, 3);
        labyrinth.setOtherWall(3, 3);
        labyrinth.setOtherWall(4, 3);
        labyrinth.setOtherWall(5, 3);
        labyrinth.setOtherWall(5, 4);
        labyrinth.setOtherWall(6, 4);
        labyrinth.printMap();
        labyrinth.start(3, 2);


    }

    

    
}