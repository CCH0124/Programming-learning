package com.cch.juc.day01;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.logging.Logger;

public class FutureTaskTest {

    private static Logger log = Logger.getLogger(FutureTaskTest.class.getName());
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> task = new FutureTask<>(
            new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    // TODO Auto-generated method stub
                    log.info("running...");
                    Thread.sleep(1000); // 影響 get() 取值時間
 
                    return 100;
                }
            }
        );
        Thread t = new Thread(task, "t5");
        t.start();
        log.info(String.format("Get: %s", task.get()));
    }    
}
