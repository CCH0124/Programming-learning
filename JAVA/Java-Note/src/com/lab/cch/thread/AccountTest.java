package com.lab.cch.thread;

/**
 * 兩個儲值帳戶分別向同一個帳戶存 3000 元，每次存 1000，存三次。每次儲存玩打印餘額
 */
class Account {
    private  double balance;
    public synchronized void deposit(double amt){
        if(amt > 0){
            balance += amt;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "成功，餘額："+ balance);

        }
    }
}

class Customer extends Thread {
    private  Account account;
    public Customer(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0; i<3; i++){
            account.deposit(1000);
        }
    }
}
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();
        Customer c1 = new Customer(account);
        Customer c2 = new Customer(account);

        c1.setName("c1");
        c2.setName("c2");

        c1.start();
        c2.start();
    }
}
