package Basics.thread.runnable;

public class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println("Extend 并 Override Thread 类的 run 来实现线程");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        // 但最后调用的时候都需要放入到Thread类里调用 start 方法
        MyThread2 myThread2 = new MyThread2();
        new Thread(myThread2).start();
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run(){
        System.out.println("Override runnable 接口的 run 来实现线程");
    }
}
