package streamapi;

import java.util.Arrays;

import org.junit.Test;

public class OtherStreamTest {
    @Test
    public void test1() {
        Integer [] nums = new Integer[]{1,2,3,4,5};
        Arrays.stream(nums).map((x) -> x*x).forEach(System.out::println);
    }
}
