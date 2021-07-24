package com.example.cch.day05;

import lombok.extern.slf4j.Slf4j;

public class PhilosophersDiningTest {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");

        new PhilosophersDining("蘇格拉底", c1, c2).start();
        new PhilosophersDining("柏拉圖", c2, c3).start();
        new PhilosophersDining("亞里斯多德", c3, c4).start();
        new PhilosophersDining("赫拉克利特", c4, c5).start();
        new PhilosophersDining("阿基米德", c5, c1).start();
    }
}

@Slf4j(topic = "c.PhilosophersDining")
class PhilosophersDining extends Thread {
    Chopstick left;
    Chopstick right;

    public PhilosophersDining(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            synchronized(left) {
                synchronized(right) {
                    try {
                        eat();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void eat() throws InterruptedException {
        log.info("Eating...");
        Thread.sleep(1000);
    }
}


class Chopstick {
    private String name;

    public Chopstick(String name) {
        this.name = name;
    }
}