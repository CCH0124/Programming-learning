package com.example.cch.day03;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TwoPhaseTermination")
public class TwoPhaseTermination {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTerminate t = new TwoPhaseTerminate();
        log.debug("TwoPhaseTerminate Test Start ....");
        t.start();

        Thread.sleep(3500);

        t.stop();
    }
}

@Slf4j(topic = "c.TwoPhaseTerminate")
class TwoPhaseTerminate {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while(true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.info("Exist... 優雅的");
                    break;
                }

                try {
                    Thread.sleep(1000); // case 1
                    log.info("monitor..."); // case 2
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    currentThread.interrupt(); // 重新設置標記
                }
            }
        }, "t1");

        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}