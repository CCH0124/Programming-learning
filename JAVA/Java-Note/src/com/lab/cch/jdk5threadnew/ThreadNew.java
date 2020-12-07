package com.lab.cch.jdk5threadnew;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 建立執行續用 callable
 */
class NumThread implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i=1; i<=100; i++){
            if(i%2==0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
public class ThreadNew {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        FutureTask futureTask = new FutureTask(numThread);
        new Thread(futureTask).start(); // 啟動 thread
        try {
            // get 返回值為 FutureTask 建構方法參數 Callable 實現重寫的 calll 的返回值
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
