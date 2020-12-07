package MethodRef;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

import company.Employee;

public class ObjectTest {
    @Test
    public void test(){
        PrintStream ps = System.out;
        Consumer<String> con = ps::println;
        con.accept("Hello");
    }

    @Test
    public void test2() {
        Employee emp = new Employee("Itachi", 28, 45000);

        Supplier<Integer> sup = emp::getAge;
        System.out.println(sup.get());
    }
}
