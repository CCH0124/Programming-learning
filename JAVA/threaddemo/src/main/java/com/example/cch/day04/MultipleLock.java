package com.example.cch.day04;

import lombok.extern.slf4j.Slf4j;

public class MultipleLock {
    public static void main(String[] args) {
        Room r = new Room();
        new Thread(() -> {
            r.study();
        }, "Wang").start();

        new Thread(() -> {
            r.sleep();
        }, "Chen").start();;
    }
}

@Slf4j(topic = "c.Room")
class Room {
    private final Object studyRoom = new Object();
    private final Object badRoom = new Object();
    public void sleep() {
        synchronized (badRoom) {
            log.debug("sleeping two hours...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void study() {
        synchronized (studyRoom) {
            log.debug("study one hour...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
