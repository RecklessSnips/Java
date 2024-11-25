package Basics.thread.synchornized.communication;

public class SynComunication implements Runnable{

    /*
    实现2个线程交替打印，1-100的数字。那么需要一线程打印完之后等待，通知别的线程去打印

    wait(),notify(),notifyAll(), 都是需要通过同步监视器来调用
    wait(): 将当前线程放进waiting queue. 并释放它持有的锁
    notify(): 随机唤醒一个 waiting queue 中的线程，放入 ready queue
    notify(): 取出所有的 waiting threads

    以上只能放在 synchronized block/method 中执行
     */

    private int number = 0;

    private Object mockLock = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (mockLock){
                if(number >= 100){
                    /*
                    当 number >= 100 时，线程会 break 退出循环。
                    但此时，另一个线程可能正处于 wait() 状态，没有机会被唤醒
                    确保已经唤醒所有等待线程
                     */
                    mockLock.notify();
                    break;
                }
                mockLock.notify();
                number++;
                System.out.println(Thread.currentThread().getName()
                        + " Num: " + number);

                try {
                    mockLock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        SynComunication synComunication = new SynComunication();
        new Thread(synComunication).start();
        new Thread(synComunication).start();
    }
}
