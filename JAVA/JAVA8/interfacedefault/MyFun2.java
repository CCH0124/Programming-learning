package interfacedefault;

public interface MyFun2 {
    default String getName(){
        return "HA!HA";
    }

    public static void name() {
        System.out.println("Interface Static Method");
    }
}
