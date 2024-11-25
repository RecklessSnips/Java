package Basics.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable {

    /*
        比 Runnable 更强大的
        1. 可以有返回值
        2. 可以抛出异常
        3. 支持 Generic 返回值
        4. 需要借助 FutureTask 类来执行
     */

    @Override
    public Object call(){
        /*
        打印 100 以内偶数总和
         */
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask<>(myCallable);
        new Thread(futureTask).start();

        // 如果想要拿到 call 的返回值：
        try{
            Object call = futureTask.get();
            System.out.println("Sum: " + call);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }catch (ExecutionException ex){
            ex.printStackTrace();
        }
    }
}
