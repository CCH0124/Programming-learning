package com.lab.cch.thread;

class Window implements Runnable {
    private int ticket = 0;
    @Override
    public void run() {
        while (true) {
            if(ticket < 100) {
                /**
                 * 操作未完成時，有其他執行續參與，也跟著操作車票
                 * 有錯票問題，在 sleep 時會被阻塞，當 sleep 到的時候，該阻塞的執行續將被執行，因此會出現錯誤
                 * 解決：操作 ticket 時，其他執行續不該參與，直到該執行續執行完成
                 *
                 * 透過同步機制，解決執行續安全問題
                 */
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++ticket;
                System.out.println(Thread.currentThread().getName() +" 賣票, 票號" + ticket);
            }else {
                break;
            }
        }
    }
}
public class Demo02 {
    public static void main(String[] args) {
        Window w = new Window();
        Thread w1 = new Thread((w));
        Thread w2 = new Thread((w));
        Thread w3 = new Thread((w));
        w1.setName("Windows 1");
        w2.setName("Windows 2");
        w3.setName("Windows 3");
        w1.start();
        w2.start();
        w3.start();
    }
}
