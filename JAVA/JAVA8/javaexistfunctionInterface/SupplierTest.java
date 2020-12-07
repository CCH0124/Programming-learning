package javaexistfunctionInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;
/**
 * Supplier
 * 用來產生一個可定義物件
 */
public class SupplierTest {
    @Test
    public void test1() {
        List<Integer> list = getNumList(10, () -> (int)(Math.random()*20));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
    @Test
    public void test2() {
        List<Integer> list = getNumList(10, () -> (int)(Math.random()*20));
        list.forEach(System.out::println);
    }
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
}
