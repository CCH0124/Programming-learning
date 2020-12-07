import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

public class LambdaTest {
    /**
     * 無參數無返回值
     */
    @Test
    public void test1() {
        Runnable r1 = () -> System.out.println("Hello");
        r1.run();
    }   
    /**
     * 一個參數無返回值
     * 括號可不寫
     */
    @Test
    public void tes2(){
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("Hello Itachi");
    }
    /**
     * 有兩個以上參數，多條語句，因為語句多條要使用大括號
     * 一條語句，可省略大括號
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("Compare");
            return Integer.compare(x, y);
        };
    }
    /**
     * 
     */
}