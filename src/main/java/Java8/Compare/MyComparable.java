package Java8.Compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MyComparable{

    /*

    Comparable 使用：
    注意：是调用对象和被比较对象来比
    需要被排序的类本身来实现Comparable接口，再通过sort方法来调用
        1. 像String，包装类实现了Comparable接口，重写了 compareTo 方法
        2. this.compareTo(Object obj)
            return 1 this > obj
            return -1 this < obj
            return 0 this = obj
        3. 默认从小到大顺序排列
        4. 自定义类需要重写compareTo方法实现自定义比较
     */

    @Test
    public void test1(){
        // 排序以下array
        String[] array = new String[]{"aa", "mm", "dd", "jj", "ll", "kk", "cc", "zz", "bb"};

        Arrays.sort(array);

        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test2(){
        Product[] products = new Product[5];
        products[0] = new Product("GPro", 34);
        products[1] = new Product("Logitech", 43);
        products[2] = new Product("Razer", 12);
        products[3] = new Product("HyperX", 65);
        products[4] = new Product("GProX", 90);

        // 在Product实现了 Comparable 之后可以调用Arrays.sort()

        Arrays.sort(products);

        System.out.println(Arrays.toString(products));
    }
}
