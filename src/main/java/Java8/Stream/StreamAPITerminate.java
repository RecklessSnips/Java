package Java8.Stream;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamAPITerminate {

    private List<Jedi> jedis = Jedi.getListJedi();

    /*
        boolean allMatch(Predicate p):  检查是否匹配所有元素
     */
    @Test
    public void testAllMatch(){
        // 年龄都大于14？
        boolean allMatch = jedis.stream().allMatch(jedi -> jedi.getAge() > 14);
        System.out.println("年龄都大于14岁吗：" + allMatch);
    }

    /*
        boolean any(Predicate p):  检查至少匹配一个元素
     */
    @Test
    public void testAnyMatch(){
        // 至少一个（是否存在）年龄大于500？
        boolean anyMatch = jedis.stream().anyMatch(jedi -> jedi.getAge() > 500);
        System.out.println("至少一个 Jedi 年龄都大于500岁吗：" + anyMatch);
    }

    /*
        boolean noneMatch(Predicate p):  检查是否没有匹配的元素
     */
    @Test
    public void testNoneMatch(){
        // 是否存在年龄大于1000？看看是否没有这个人？
        boolean noneMatch = jedis.stream().noneMatch(jedi -> jedi.getAge() > 1000);
        System.out.println("是否没有大于1000的：" + noneMatch);
    }

    /*
        Optional<T> findFirst()： 返回 stream 第一个元素
     */
    @Test
    public void testFindFirst(){
        Optional<Jedi> first = jedis.stream().findFirst();
        System.out.println(first.get());
    }

    /*
        Optional<T> findAny():  返回当前stream中任意一个元素
     */
    @Test
    public void testFindAny(){
        // 可能会一直是一个
        Optional<Jedi> any = jedis.parallelStream().findAny();
        System.out.println(any.get());
    }

    /*
        long count(): 返回stream中总个数
     */
    @Test
    public void testCount(){
        long count = jedis.stream().filter(jedi -> jedi.getAge() > 20).count();

        System.out.println("年龄大于20岁的：" + count);
    }

    /*
        Optional<T> max(Comparator c): 返回stream中最大值
     */
    @Test
    public void testMax(){
        // 返回年龄最大
        Optional<Jedi> maxJedi = jedis.stream().max((j1, j2) -> Integer.compare(j1.getAge(), j2.getAge()));
        System.out.println("年龄最大: " + maxJedi.get());
    }

    /*
        Optional<T> min(Comparator c): 返回stream中最小值
     */
    @Test
    public void testMin(){
        // 返回年龄最小
        Optional<Jedi> minJedi = jedis.stream().min(Comparator.comparingInt(Jedi::getAge));
        System.out.println("年龄最小: " + minJedi.get());
    }

    /*
        Reduce

        reduce(T identity, BinaryOperator): 可以将stream中元素反复结合起来，得到一个值并返回

        reduce(BinaryOperator): 可以将stream中元素反复结合起来，得到一个值，返回 Optional<T>
     */
    @Test
    public void testReduce(){
        // 计算 1-10的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 从初始值0开始
        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    @Test
    public void testReduce2(){
        // 计算 Jedi 年龄的和
        //                                                      (age1, age2) -> age1 + age2
        Optional<Integer> ageSum = jedis.stream().map(Jedi::getAge).reduce(Integer::sum);
        System.out.println("年龄总和： " + ageSum.get());

        // 验证：
        int sum = 0;
        for(Jedi jedi: jedis){
            sum += jedi.getAge();
        }
        System.out.println(sum);
    }

    /*
        Collect

        collect(Collector c): 将stream转换成其他的形式。接受一个 Collector 接口的实现，用于给stream中元素做汇总的方法
        collector接口中的实现方法决定将 stream 收集到哪里（List，Set，Map）
     */

    @Test
    public void testCollect(){
        // 将年龄小于25的Jedi放到List里，temple.
        List<Jedi> temple = jedis.stream().filter(jedi -> jedi.getAge() < 25).collect(Collectors.toList());
        temple.forEach(System.out::println);

        Set<Jedi> templeSet = jedis.stream().filter(jedi -> jedi.getAge() < 25).collect(Collectors.toSet());
        templeSet.forEach(System.out::println);
    }
}
