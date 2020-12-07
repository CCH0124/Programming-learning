package streamapi;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;

import company.Employee;
import company.Employee.Status;

public class CollectStreamTest {
    List<Employee> empList = Arrays.asList(
        new Employee("itachi", 67, 9999.9, Status.FREE), 
        new Employee("madara", 89, 5555.5, Status.VOCATION),
        new Employee("naruto", 26, 3333.3, Status.BUSY), 
        new Employee("yuri", 25, 6666.6, Status.BUSY),
        new Employee("kid", 36, 7777.72, Status.VOCATION));

    @Test
    public void test1() {
        empList.stream().map(Employee::getName).collect(Collectors.toList()).forEach(System.out::println);
    }
    @Test
    public void test2() {
        Long c = empList.stream().collect(Collectors.counting());
        System.out.println(c);
    }

    @Test
    public void test3() {
        Double d = empList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(d);
    }
    @Test
    public void test4() {
        Double d = empList.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(d);
    }

    @Test
    public void test5() {
        Map<Status, List<Employee>> map = empList.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    @Test
    public void test6() {
        DoubleSummaryStatistics dss = empList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getAverage());
        System.out.println(dss.getMax());
    }
}
