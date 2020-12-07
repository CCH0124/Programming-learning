package interfacedefault;

public class SubClass2 implements MyFun, MyFun2{
    @Override
    public String getName(){
        return MyFun.super.getName();
    }
}
