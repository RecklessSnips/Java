package Basics.thread.synchornized;

import java.util.concurrent.locks.ReentrantLock;

public class MyLock implements Runnable{

    /*
    让这个类开启两个线程，共同数到100，每次打印结果
     */

    private int num = 0;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run(){

        while (true) {
            try{
                lock.lock();
                if (num >= 100) {
                    break;
                }
                num++;
                System.out.println("线程" + Thread.currentThread().getName()
                        + "正在计数：" + num);
            }finally{
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        MyLock myLock = new MyLock();

        new Thread(myLock).start();
        new Thread(myLock).start();
    }
}
