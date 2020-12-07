package funint;

public class Test {
    public static Integer operation(Integer num, MyFun fun){
        return fun.getValue(num);
    }
    public static void main(String[] args) {
        System.out.println(operation(100, (x) -> x * x));
        System.out.println(operation(200, (y) -> y + 200));
        System.out.println(operation(100, (x) -> x / 100));
    }
}