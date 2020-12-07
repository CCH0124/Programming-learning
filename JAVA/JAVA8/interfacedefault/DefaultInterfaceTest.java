package interfacedefault;

public class DefaultInterfaceTest {
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        System.out.println(sc.getName());
        SubClass2 sc2 = new SubClass2();
        System.out.println(sc2.getName());
        MyFun2.name();
    }
}
