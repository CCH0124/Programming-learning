package com.cch.juc.day02;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class LockDemo {
    private static Logger log = Logger.getLogger(LockDemo.class.getName());
    public static void main(String[] args) {
        // caseOne();
        // caseTwo();
        // caseThree();
        // caseFour();
        // caseFive();
        // caseSix();
        // caseSeven();
        caseEight();
        log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
    }

    public static void caseOne() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMail();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }

    public static void caseTwo() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }

    public static void caseThree() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.hello();
        }, "hello").start();
    }

    public static void caseFour() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseTwo();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMS();
        }, "phone2").start();
    }

    public static void caseFive() {
        Phone phone = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMSCaseFive();
        }, "sms").start();
    }

    public static void caseSix() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMSCaseFive();
        }, "phone2").start();
    }
    public static void caseSeven() {
        Phone phone = new Phone();


        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "mail").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendSMS();
        }, "sms").start();
    }
    public static void caseEight() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone.sendMailCaseFive();
        }, "phone1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            log.info(String.format("Thread Name: %s", Thread.currentThread().getName()));
            phone2.sendSMS();
        }, "phone2").start();
    }
}

class Phone {
    private static Logger log = Logger.getLogger(Phone.class.getName());
    public synchronized void sendMail() {
        System.out.println("Send Mail");
    }

    public synchronized void sendSMS() {
        System.out.println("Send SMS");
    }

    public synchronized void sendMailCaseTwo() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Send Mail");
    }

    public void hello() {
        System.out.println("hello");
    }

    public static synchronized void sendMailCaseFive() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("Send Mail");
    }

    public static synchronized void sendSMSCaseFive() {
        log.info("Send SMS");
    }
}
