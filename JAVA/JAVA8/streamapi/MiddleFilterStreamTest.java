package streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import company.Employee;

public class MiddleFilterStreamTest {
    List<Employee> empList = Arrays.asList(new Employee("itachi", 67, 9999.9), new Employee("madara", 89, 5555.5),
            new Employee("naruto", 26, 3333.3), new Employee("naruto", 26, 3333.3), new Employee("yuri", 25, 6666.6),
            new Employee("kid", 36, 7777.72), new Employee("kid", 36, 7777.72));

    /**
     * 篩選與切片
     */
    @Test
    public void test1() {
        Stream<Employee> s = empList.stream().filter((e) -> e.getAge() > 35); // 中間操作
        s.forEach(System.out::println); // 終止操作
    }

    @Test
    public void test2() {
        empList.stream().filter((e) -> e.getSalary() > 5000).limit(2).forEach(System.out::println);
    }

    @Test
    public void test3() {
        empList.stream().filter((e) -> e.getSalary() > 5000).skip(2).forEach(System.out::println);
    }

    @Test
    public void test4() {
        empList.stream().filter((e) -> e.getSalary() > 5000).skip(2).distinct().forEach(System.out::println);
    }
}
