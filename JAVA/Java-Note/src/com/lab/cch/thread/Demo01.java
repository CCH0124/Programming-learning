package com.lab.cch.thread;

class Windows extends Thread {
    private int ticket = 100;
    @Override
    public void run() {
        while (true) {
            if(ticket > 0) {
                System.out.println(getName()+" 賣票, 票號" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}
public class Demo01 {

    public static void main(String[] args) {
	// write your code here
        Windows a = new Windows();
        Windows b = new Windows();
        Windows c = new Windows();
        a.start();
        b.start();
        c.start();
    }
}
