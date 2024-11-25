package Basics.thread.synchornized.communication.prcatice;

public class PubSub {
    /*
    生产者只负责生产，消费者只负责消费。生产20个的时候停止，消费到0的时候停止
    一直运行，只要有可消费/生产的时候就一直工作。
     */

    public static void main(String[] args) {
        Product product = new Product(0);
        Producer producer = new Producer(product);
        Consuemr consuemr = new Consuemr(product);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consuemr);
        thread1.setName("生产者");
        thread2.setName("消费者");
        thread1.start();
        thread2.start();
    }
}

class Product{
    private int products = 0;

    public Product(int num){
        products = num;
    }

    public int getProducts(){
        return this.products;
    }

    public synchronized void produce(){
        while(true) {
            if (products >= 20) {
                try {
                    // 等待消费
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.products++;
                System.out.println(Thread.currentThread().getName() +
                        "正在生产: " +
                        products);
                notify();
            }
        }
    }

    public synchronized void consume(){
        while(true) {
            if (products <= 0) {
                try {
                    // 等待生产
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.products--;
                System.out.println(Thread.currentThread().getName() +
                        "正在消费: " +
                        products);
                notify();
            }
        }
    }
}

class Producer implements Runnable{

    private Product product;

    public Producer(Product product){
        this.product = product;
    }

    @Override
    public void run() {
        System.out.println("开始生产");
        product.produce();
    }
}

class Consuemr implements Runnable{

    private Product product;

    public Consuemr(Product product){
        this.product = product;
    }

    @Override
    public void run() {
        System.out.println("开始消费");
        product.consume();
    }
}
