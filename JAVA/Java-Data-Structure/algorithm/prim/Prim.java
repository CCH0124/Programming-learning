package algorithm.prim;

import java.util.Arrays;

class MGraph {
    int verx;
    char[] data;
    int[][] weight;

    public MGraph(int verx) {
        this.verx = verx;
        data = new char[verx];
        weight = new int[verx][verx];
    }
}

class MinTree {
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void prim(MGraph graph, int v) {
        int visited[] = new int[graph.verx];
        visited[v] = 1;

        int h1 = -1;
        int h2 = -1;

        int minWeight = 10000;
        for (int k = 1; k < graph.verx; k++) {
            for (int i = 0; i < graph.verx; i++) {
                for (int j = 0; j < graph.verx; j++) {
                    if(visited[i] == 1 && visited[j]==0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("Edge<"+ graph.data[h1] + "," + graph.data[h2] + "> Weight: " + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

public class Prim {
    public static void main(String[] args) {
        char[] data = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int verxs = data.length;

        int[][] weight = new int[][] { { 10000, 5, 7, 10000, 10000, 10000, 2 }, { 5, 10000, 10000, 9, 10000, 10000, 3 },
                { 7, 10000, 10000,10000, 8, 10000, 10000 }, { 10000, 9, 10000, 10000, 10000, 4, 10000 },
                { 10000, 10000, 8, 10000, 10000, 5, 4 }, { 10000, 10000, 10000, 4, 5, 10000, 6 },
                { 2, 3, 10000, 10000, 4, 6, 10000 } };
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.prim(mGraph, 0);
    }
}
