package algorithm.dac;

public class Hanoitower {
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1個盤從：" + a + "->" + c);
        } else {
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num +"個盤從：" + a + "->" + c);
            hanoiTower(num - 1, b, c, a);
        }
    }

    public static void main(String[] args) {
        Hanoitower.hanoiTower(5, 'A', 'B', 'C');
    }
}