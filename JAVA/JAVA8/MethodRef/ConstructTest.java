package MethodRef;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

import company.Employee;

public class ConstructTest {
    @Test
    public void test(){
        Supplier<Employee> sup = Employee::new;
        Employee emp = sup.get();

        Function<String, Employee> fun = Employee::new;
        BiFunction<String, Integer, Employee> bp = Employee::new;
        
    }
}
