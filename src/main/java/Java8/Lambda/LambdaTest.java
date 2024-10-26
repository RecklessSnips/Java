package Java8.Lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaTest {

    /*
    * Lambda 使用
    *
    * 格式：
    *   左边：形参列表：函数式接口的参数
    *   右边：抽象方法的方法体
    *
    * 使用: 6 种情况
    *
    * 本质：
    *   作为函数接口的实例对象
    * */

    // 1. 无参数，无返回值
    @Test
    public void test0(){
       Runnable thread1 = () -> System.out.println("Thread 1");
       thread1.run();
    }


    // 2. 1个参数，无返回值.
    @Test
    public void test1(){
        Consumer<String> consumer = (String word) -> System.out.println(word);
        consumer.accept("谎言是什么");
    }

    // 3. 数据类型可以省略，称为类型推断, 1个参数的话，小括号也可以省略
    @Test
    public void test2(){
        Consumer<String> consumer = word -> System.out.println(word);
        consumer.accept("谎言是什么");
    }

    // 4. 2个以上参数, 多条语句，可以有返回值
    @Test
    public void test3(){
        Comparator<Integer> comparator = (n1, n2) -> {
            System.out.println(n1);
            System.out.println(n2);
            return n1.compareTo(n2);
        };
        System.out.println(comparator.compare(1, 2));
    }

    // 5. 只有一句话，可以省略 {}, return 可以不写
    @Test
    public void test4(){
        Comparator<Integer> comparator = (n1, n2) -> n1.compareTo(n2);

        System.out.println(comparator.compare(1, 2));

        Consumer<String> consumer = s -> System.out.println(s);

        consumer.accept("Whats up");
    }
}
