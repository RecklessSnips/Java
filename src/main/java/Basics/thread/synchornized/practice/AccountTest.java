package Basics.thread.synchornized.practice;

import java.util.concurrent.locks.ReentrantLock;

public class AccountTest{
    /*
        银行有一个账户，两个储户分别向一个账户总共存3000，每次1000，总共三次
        打印每次存储完的结果，也就是1000，2000 ... 6000
     */

    public static void main(String[] args) {
        Account account = new Account(0);
        Customer customer = new Customer(account);
        new Thread(customer).start();
        new Thread(customer).start();
    }
}


class Account{
    private int balance;

    private final ReentrantLock LOCK = new ReentrantLock();

    public Account(int balance){
        this.balance = balance;
    }

    public void deposit(int amount){
        try{
            LOCK.lock();
            this.balance += amount;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() +
                    "正在存款. 账户余额: " + getBalance());
        }finally{
            LOCK.unlock();
        }
    }

    public int getBalance(){
        return this.balance;
    }

}

class Customer implements Runnable{

    private Account account;

    public Customer(Account account){
        this.account = account;
    }

    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {
            account.deposit(1000);
        }
    }
}