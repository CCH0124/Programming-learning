package streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import company.Employee;
import company.Employee.Status;

public class TerminateStreamTest {
    List<Employee> empList = Arrays.asList(
        new Employee("itachi", 67, 9999.9, Status.FREE), 
        new Employee("madara", 89, 5555.5, Status.VOCATION),
        new Employee("naruto", 26, 3333.3, Status.BUSY), 
        new Employee("yuri", 25, 6666.6, Status.BUSY),
        new Employee("kid", 36, 7777.72, Status.VOCATION));
    
    @Test
    public void test1() {
        boolean b = empList.stream().allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b);
    }

    @Test
    public void test2() {
        boolean b = empList.stream().anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b);
    }

    @Test
    public void test3() {
        boolean b = empList.stream().noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b);
    }

    @Test
    public void test4() {
        Optional<Employee> op = empList.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(op.get());
    }

    @Test
    public void test5() {
        Optional<Employee> op = empList.stream().filter((e) -> e.getStatus().equals(Status.FREE)).findAny();
        System.out.println(op.get());
    }

    @Test
    public void test6() {
        Long count = empList.stream().count();
        System.out.println(count);
    }

    @Test
    public void test7() {
        Optional<Employee> op = empList.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op.get());
    }

    @Test
    public void test8() {
        Optional<Double> op = empList.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(op.get());
    }
}
