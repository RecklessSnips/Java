package Java8.Stream;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPICreate {
    /*
    * Stream 关注数据的计算，面向CPU
    *
    * 使用说明
    * 1. 自己不会储存元素
    * 2. 不会改变原对象，会返回一个有结果的新stream
    * 3. 是延迟进行，意味着他们会等到需要结果的时候才会进行，一旦执行终止操作，就执行中间操作链，并产生结果
    * 4. 一旦执行终止操作，不能再调用其他的中间操作或者终止操作
    *
    * 执行流程
    * 1. Stream实例化
    * 2. 一系列中间操作
    * 3. 终止操作（只有一次）
    * */

    // 创建 Stream：Collection
    @Test
    public void testCollection(){
        List<Jedi> jedi = Jedi.getListJedi();
        Stream<Jedi> stream = jedi.stream();
        // 顺序stream， 按照原顺序
        System.out.println(stream);
        // 并行 stream, 将数据分成多个子部分，并使用多线程同时处理这些部分
        // (顺序可能不固定，因为是并行执行的)
        System.out.println(jedi.parallelStream());
    }

    // 通过数组
    @Test
    public void testArray(){
        // 通过Arrays类的static的 stream 返回
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> stream = Arrays.stream(arr);

        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        IntStream stream1 = Arrays.stream(arr1);
    }

    // 通过 of
    @Test
    public void testOf(){
        // 适用于将若干元素转换成 Stream 对象，或者将数组转换成 Stream 对象
        Stream<String> aa = Stream.of("AA", "BB", "CC", "DD", "EE");

        // 使用 Stream.of() 创建包含数组的流
        String[] fruitArray = {"Date", "Elderberry", "Fig"};
        Stream<String> arrayStream = Stream.of(fruitArray);
    }
}
