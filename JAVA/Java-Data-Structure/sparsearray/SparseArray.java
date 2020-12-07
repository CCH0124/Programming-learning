package sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SparseArray {
    public static void printChess(int chess[][]) {
        for (int[] row : chess) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public static int existDataOnChess(int chess[][]) {
        int sum = 0;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static int[][] chessToSparseArray(int chess[][]) {
        int chessSize = chess.length;
        int onChess = existDataOnChess(chess);
        int sparseArray[][] = new int[onChess + 1][3];

        sparseArray[0][0] = chessSize;
        sparseArray[0][1] = chessSize;
        sparseArray[0][2] = onChess;

        int count = 0;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[1].length; j++) {
                if (chess[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chess[i][j];
                }
            }
        }
        return sparseArray;
    }

    public static void printSparseArray(int sparseArray[][]) {
        System.out.println("sparse Array");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();
    }

    public static int[][] restoreChess(int sparseArray[][]) {
        int chessRestore[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            chessRestore[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return chessRestore;
    }

    public static void writeFile(int[][] sparseArray) {
        // https://www.baeldung.com/java-write-to-file
        try {
            // false : override file
            FileWriter fileWriter = new FileWriter("output.txt", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < sparseArray.length; i++) {
                printWriter.printf("%d\t%d\t%d\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
            }
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] restoreFileToChess() throws IOException {
        File file = new File("output.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String chessSize[] = br.readLine().trim().split("\t");
        int chess[][] = new int[Integer.parseInt(chessSize[0])][Integer.parseInt(chessSize[1])];
        String row;
        while ((row = br.readLine()) != null) {
            String[] chessRow = row.trim().split("\t");
            for (int i = 0; i < chessRow.length; i++) {
                chess[Integer.parseInt(chessRow[0])][Integer.parseInt(chessRow[1])] = Integer.parseInt(chessRow[2]);
            }
        }
        br.close();
        return chess;

    }

    public static void main(String[] args) throws IOException {
        int chess[][] = new int[11][11];
        chess[1][2] = 1;
        chess[2][3] = 2;
        chess[4][5] = 2;
        chess[0][0] = 1;

        printChess(chess);
        int sum = existDataOnChess(chess); // none zero data
        System.out.println("sum = " + sum);
        int a [][] = chessToSparseArray(chess);
        writeFile(a);
        int sparseArray[][] = restoreFileToChess();
        printChess(sparseArray);
    }
}