package Java8.Lambda.MethodReference;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MTest {

    /*
     *
     * 方法引用
     *
     * 在满足使用Lambda的条件下，有时候可以使用方法引用来代替
     *
     * 左边跟Lambda样子一样：Consumer<T> consumer =
     * 右边需要满足以下情况:
     *
     * 情况1: 对象 :: 实例方法 (非static)
     * 需要：函数接口的抽象方法 A，和调用的对象的某个方法 B 的参数列表和返回类型一样, 那么用 { 对象 :: B } 来代替 A
     * 注意：这是实例方法，需要实例instance来调用
     * 例子： 看样子，看左右括号里东西是不是一样，再看右边方法返回值是不是跟左边一样
     * s -> System.out.println(s);    () -> ahsoka.getName();
     *
     * 情况2: 类 :: static 方法
     * 需要：函数接口的抽象方法 A，和调用的对象的 类 的某个 static 方法
     *      的参数列表和返回类型匹配(可以是Integer, int), 那么用 { 类 :: B } 来代替 A
     * 注意：这是 static 方法，需要 Class 来调用
     * 例子：看左右括号里东西是不是一样， 返回值
     *  Comparator<Integer> comparator = (n1, n2) -> Integer.compare(n1, n2);
     *  Function<Double, Long> function = num -> Math.round(num);
     *
     * 情况3: 类 :: 实例方法 (非static)
     * 需要：右边比左边少一个参数，且左边第一个参数作为右边的方法调用者，且返回值一样
     * 窍门：用左边第一个参数调用右边方法即可
     * 例子：
     *  Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
     *  BiPredicate<String, String> biPredicate = (t1, t2) -> t1.equals(t2);
     *  Function<Jedi, String> function = jedi -> jedi.getName();
     */

    /**
     * 情况1:
     * Consumer: void accept(T t)
     * PrintStream: void println(T t)
     */
    @Test
    public void test0(){
        System.out.println("Lambda: ");
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("我想干乔雨辰");

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        Consumer<String> consumer1 = System.out :: println;
        consumer1.accept("我想干乔雨辰");
    }

    /**
     * 情况1:
     * Supplier: T get()
     * Jedi: String getName()
     */
    @Test
    public void test1(){
        System.out.println("Lambda: ");
        Jedi ahsoka = new Jedi("Ahsoka");
        Supplier<String> supplier = () -> ahsoka.getName();
        System.out.println(supplier.get());

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        Supplier<String> supplier1 = ahsoka :: getName;
        System.out.println(supplier1.get());
    }

    /**
     * 情况2:
     * Comparator: int compare(T t1, T t2)
     * Integer: int compare(T t1, T t2)
     */
    @Test
    public void test2(){
        System.out.println("Lambda: ");
        Comparator<Integer> comparator = (n1, n2) -> Integer.compare(n1, n2);
        System.out.println(comparator.compare(1, 2));

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        Comparator<Integer> comparator1 = Integer :: compare;
        System.out.println(comparator1.compare(1, 2));
    }

    /**
     * 情况2:
     * Function: R apply(T t)
     * Math: Long round(Double d)
     */
    @Test
    public void test3(){
        System.out.println("Lambda: ");
        Function<Double, Long> function = num -> Math.round(num);
        System.out.println(function.apply(3.14));

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        Function<Double, Long> function1 = Math :: round;
        System.out.println(function1.apply(5.1));
    }

    /**
     * 情况3:
     * Comparator: int compare(T n1, T n2)
     * String: int n1.compareTo(n2)
     */
    @Test
    public void test4(){
        System.out.println("Lambda: ");
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator.compare("abc", "abd"));

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        // 用左边第一个参数来调用方法
        Comparator<String> comparator1 = String::compareTo;
        System.out.println(comparator1.compare("abc", "abd"));
    }

    /**
     * 情况3:
     * BiPredicate: boolean test(T t1, T t2)
     * String: boolean t1.equals(t2)
     */
    @Test
    public void test5(){
        System.out.println("Lambda: ");
        BiPredicate<String, String> biPredicate = (t1, t2) -> t1.equals(t2);
        System.out.println(biPredicate.test("Ahsoka", "Ahsoka"));

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        // 用左边第一个参数来调用方法
        BiPredicate<String, String> biPredicate1 = String :: equals;
        System.out.println(biPredicate1.test("Anain", "Anakin"));
    }

    /**
     * 情况3:
     * Function: R apply(T t)
     * Jedi: String getName()
     */
    @Test
    public void test6(){
        System.out.println("Lambda: ");
        Jedi ahsoka = new Jedi("Ahsoka");
        Function<Jedi, String> function = jedi -> jedi.getName();
        System.out.println(function.apply(ahsoka));

        System.out.println("**********************************");
        System.out.println("方法引用: ");
        // 用左边第一个参数来调用方法
        Function<Jedi, String> function1 = Jedi :: getName;
        System.out.println(function1.apply(ahsoka));
    }
}
