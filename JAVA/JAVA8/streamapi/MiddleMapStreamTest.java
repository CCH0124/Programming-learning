package streamapi;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import company.Employee;

public class MiddleMapStreamTest {
    List<Employee> empList = Arrays.asList(new Employee("itachi", 67, 9999.9), new Employee("madara", 89, 5555.5),
            new Employee("naruto", 26, 3333.3), new Employee("naruto", 26, 3333.3), new Employee("yuri", 25, 6666.6),
            new Employee("kid", 36, 7777.72), new Employee("kid", 36, 7777.72));

    /**
     * 映射
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void test2() {
        empList.stream().distinct().map(Employee::getName).forEach(System.out::println);
    }
}
