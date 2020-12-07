package javaexistfunctionInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicateTest {
    @Test
    public void test1() {
        List<String> list = Arrays.asList("Itachi", "Naruto", "Madara", "sasuke", "no");
        filterStr(list, (s) -> s.length() > 3).forEach(System.out::println);
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }
}
