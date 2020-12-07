package algorithm.kpm;

import java.util.Arrays;

public class CompareString {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";

        int[] next = kmpNext(s2);
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(s1, s2, next);
        System.out.println(index);
    }

    public static int kmpSearch(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
    /**
     * ABCDABD
     * ji       next[1] = 0
     *   i      next[2] = 0 
     *    i     next[3] = 0 
     *     i    next[4] = 1
     *  j   i   next[5] = 2
     *        i next[6] = 0
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}