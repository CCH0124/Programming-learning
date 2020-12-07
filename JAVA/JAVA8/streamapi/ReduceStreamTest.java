package streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import company.Employee;
import company.Employee.Status;

public class ReduceStreamTest {
    List<Employee> empList = Arrays.asList(
        new Employee("itachi", 67, 9999.9, Status.FREE), 
        new Employee("madara", 89, 5555.5, Status.VOCATION),
        new Employee("naruto", 26, 3333.3, Status.BUSY), 
        new Employee("yuri", 25, 6666.6, Status.BUSY),
        new Employee("kid", 36, 7777.72, Status.VOCATION));

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y); // x 起始值為 0
        System.out.println(sum);

        Optional<Double> op = empList.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());
    }
}
