package algorithm.dijkstra;

import java.util.Arrays;

class VisitedVertex {
    public int[] alerady_arr;
    public int[] pre_visited;
    public int[] dis;

    /**
     * 
     * @param length 表示頂點的個數
     * @param index  初始頂點索引
     */
    public VisitedVertex(int length, int index) {
        this.alerady_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        this.alerady_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 是否被訪問過
     * 
     * @param index
     * @return
     */
    public boolean in(int index) {
        return alerady_arr[index] == 1;
    }

    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新 pre 頂點的前驅節點為 index
     * 
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 頂點到 index 節點的距離
     * 
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alerady_arr.length; i++) {
            if (alerady_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alerady_arr[index] = 1;
        return index;
    }
    public void show(){
        System.out.println("----------------------------------------------------------------------------------");
        for (int i : alerady_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void graphPrint() {
        for (int[] is : matrix) {
            System.out.println(Arrays.toString(is));
        }
    }

    public void runDijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();
            update(index);
        }
    }

    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            len = vv.getDis(index) + matrix[index][i];
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updateDis(i, len);

            }
        }
    }
    public void showDijkstra(){
        vv.show();
    }
    
}

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertexs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int matrix[][] = new int[vertexs.length][vertexs.length];
        final int N = 65535;
        matrix[0] = new int[] { N, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, N, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, N, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, N, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, N, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, N, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, N };
        Graph graph = new Graph(vertexs, matrix);
        // graph.graphPrint();
        graph.runDijkstra(6);
        graph.showDijkstra();
    }
}