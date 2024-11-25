package Basics.thread.pool;

import java.util.concurrent.*;

public class ThreadPool {

    public static void main(String[] args) {
        // 1. 提供指定数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 2. 执行指定线程的操作，提供 Runnable 或者 Callable 的实现类
        service.execute(() -> {
            for (int i = 0; i <= 100; i++) {
                if(i % 2 == 0){
                    System.out.println("Even: " + i);
                }
            }
        });

        Future<Integer> future = service.submit(() -> {
            int sum = 0;
            for (int i = 0; i <= 100; i++) {
                if (i % 2 == 0) {
                    sum += i;
                }
            }
            return sum;
        });


        // 3. 关闭连接池
        try {
            // 从 Future 获取任务的返回结果
            Integer result = future.get(); // 阻塞直到任务完成
            System.out.println("Callable 任务结果：" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            service.shutdown();
        }
    }
}
