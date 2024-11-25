package Basics.thread.synchornized;

public class SyncThread implements Runnable{
    /*
    让这个类开启两个线程，共同数到100，每次打印结果
     */

    private int num = 0;

    @Override
    public void run(){

        while (true) {
            synchronized (this) {
                if (num >= 100) {
                    break; // 超过100时退出循环
                }
//                try {
//                    // 模拟其他操作的耗时
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                num++;
                System.out.println("线程" + Thread.currentThread().getName()
                        + "正在计数：" + num);
            }
        }

    }

    public static void main(String[] args) {
        SyncThread thread1 = new SyncThread();
        // 对于同一个类，开启两个线程
        new Thread(thread1).start();
        new Thread(thread1).start();
    }
}
