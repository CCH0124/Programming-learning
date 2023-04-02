package com.cch.juc.day02;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class SaleTicketDemo {
    private static Logger log = Logger.getLogger(SaleTicketDemo.class.getName());
    public static void main(String[] args) {
        // sell();
        sellFair();
        log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
    }

    public static void sell() {
        Ticket ticket = new Ticket();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleA").start();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleB").start();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleC").start();
    }

    public static void sellFair() {
        TicketFair ticket = new TicketFair();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleA").start();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleB").start();
    
            new Thread(() -> {
                for (int i = 0; i < 55; i++) {
                    ticket.sale();
                }
            }, "SaleC").start();
    }
}


class Ticket {
    private static Logger log = Logger.getLogger(Ticket.class.getName());
    private int number = 50;
    ReentrantLock reentrantLock = new ReentrantLock(); // 非公平
    public void sale () {
        reentrantLock.lock();
        try {
            if (number > 0) {
                log.info(String.format("%s 賣出第 %d 張，還剩下票 %d 張票 ", Thread.currentThread().getName(), (number--), number));
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}

class TicketFair {
    private static Logger log = Logger.getLogger(TicketFair.class.getName());
    private int number = 50;
    ReentrantLock reentrantLock = new ReentrantLock(true);
    public void sale () {
        reentrantLock.lock();
        try {
            if (number > 0) {
                log.info(String.format("%s 賣出第 %d 張，還剩下票 %d 張票 ", Thread.currentThread().getName(), (number--), number));
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}