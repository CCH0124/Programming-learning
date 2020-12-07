package javaexistfunctionInterface;

import java.util.function.Consumer;

import org.junit.Test;

public class ConsumerTest {
    @Test
    public void test1(){
        consumerUse(500.3, System.out::println);
    }
    @Test
    public void test2(){
        consumerUse(500.3, (m) -> System.out.println("消費："+m));
    }
    public void consumerUse(double money, Consumer<Double> con){
        con.accept(money);
    }
}
