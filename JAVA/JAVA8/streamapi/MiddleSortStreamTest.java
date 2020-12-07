package streamapi;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import company.Employee;

public class MiddleSortStreamTest {
    List<Employee> empList = Arrays.asList(new Employee("itachi", 67, 9999.9), new Employee("madara", 89, 5555.5),
            new Employee("naruto", 26, 3333.3), new Employee("naruto", 26, 3333.3), new Employee("yuri", 25, 6666.6),
            new Employee("kid", 36, 7777.72), new Employee("kid", 36, 7777.72));

    @Test
    public void test1() {
        empList.stream().map(Employee::getAge).sorted().forEach(System.out::println);
    }

    @Test
    public void test2() {
        empList.stream().sorted((e1, e2) -> {
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }
}