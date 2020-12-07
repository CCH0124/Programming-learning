package streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import company.Employee;

public class CreateStreamTest {
    @Test
    public void test1(){
        // Create Stream
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
    }

    @Test
    public void test2(){
        // Create Stream
        Employee[] emps = new Employee[10];
        Arrays.stream(emps);
    }

    @Test
    public void test3(){
        // Create Stream
        Stream<String> stream = Stream.of("a", "b", "c");
    }

    @Test
    public void test4(){
        // 迭代建立無限流
        Stream<Integer> stream = Stream.iterate(0, (x) -> x+2);
        stream.forEach(System.out::println);
    }

    @Test
    public void test5(){
        // 迭代建立無限流，由 limit 限制
        Stream<Integer> stream = Stream.iterate(0, (x) -> x+2);
        stream.limit(10).forEach(System.out::println);
    }

    @Test
    public void test6() {
        // 生成流
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }
}