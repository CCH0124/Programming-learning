package interfacedefault;

public interface MyFun {
    default String getName(){ 
        return "HA";
    }
}