package Java8.Stream;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPIOperation {

    /**
     *  中间操作，分为三部分：
     *  1. 筛选，切片
     */

    // Stream<T> filter(Predicate p)  从stream里过滤满足 predicate p 的元素
    @Test
    public void testFilter(){
        List<Jedi> jediList = Jedi.getListJedi();
        // 过滤出（挑选出）年龄大于15的 Jedi
        Stream<Jedi> stream = jediList.stream();
        stream.filter(jedi -> jedi.getAge() > 15).forEach(j -> System.out.println(
                j.toString() + " : " + j.getAge()));
        // 一旦执行了forEach等终止操作，stream就关闭了，要重新instantiate
    }

    // distinct()   筛选，通过stream生成的元素的 hashCode 和 equals 去除重复元素
    @Test
    public void testDistinct(){
        // TODO: 需要重写equals 和 hashCode方法
        List<Jedi> jediList = Jedi.getListJedi();
        jediList.add(new Jedi("Ahsoka", 17));
        jediList.add(new Jedi("Anakin", 21));
        jediList.stream().distinct().forEach(System.out::println);
    }

    // limit(long maxSize)  截断stream，不超过 maxSize 数量
    @Test
    public void testLimit(){
        // 选出前三个Jedi
        List<Jedi> jediList = Jedi.getListJedi();
        System.out.println(jediList);
        Stream<Jedi> stream = jediList.stream();
        stream.limit(3).forEach(System.out::println);
    }

    // skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的stream。若stream中不足 n 个元素，则返回空stream
    @Test
    public void testSkip(){
        // 跳过前三个Jedi
        List<Jedi> jediList = Jedi.getListJedi();
        Stream<Jedi> stream = jediList.stream();
        stream.skip(3).forEach(System.out::println);
    }


    /**
     *  中间操作，分为三部分：
     *  2. 映射
     */

    // <R> Stream<R> map(Function<T,R> f) 接受一个函数作为参数，该函数被应用到每一个元素上，
    // 将元素转换成其他的形式或者提取信息，(也就是接受 Function 的返回值 R) 并映射成新的元素stream
    @Test
    public void testMap(){
        List<String> list = Arrays.asList("aa", "bb", "cc");
        // str -> str.toUpperCase()
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        // 取出所有Jedi的名字
        List<Jedi> jediList = Jedi.getListJedi();
        // 将所有的元素应用这个 Jedi::getName 方法去，并给他们包含成Stream返回
        Stream<String> names = jediList.stream().map(Jedi::getName);
        names.forEach(System.out::println);
    }

    /*
        flatMap 会将原本 stream 内部每个元素转换为Stream<T>，
        然后将这些流“扁平化”成一个单一的Stream<T>，即将多个小流合并成一个大流。
     */
    @Test
    public void testFlatMap(){
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("carrot", "date"),
                Arrays.asList("eggplant", "fig")
        );
        System.out.println(listOfLists);

        Stream<List<String>> stream = listOfLists.stream();
        Stream<String> stringStream = stream.flatMap(l -> l.stream());
        stringStream.forEach(System.out::println);

        // 只能转换成新的stream，而不是直接返回
        Stream<Stream<String>> streamStream = listOfLists.stream().map(l -> l.stream());
        streamStream.forEach(
                s -> s.forEach(System.out::println)
        );
    }

    @Test
    public void testMap2(){
        // 取出所有名字长度大于4的Jedi 名字
        Jedi.getListJedi()
                // 这里的stream得到的是 Stream<Jedi>
                .stream()
                // 先 map （建议）, 拿到所有的名字
                .map(Jedi::getName)
                // 过滤
                .filter(name -> name.length() > 4)
                .forEach(System.out::println);
    }


    /**
     *  中间操作，分为三部分：
     *  3. 排序
     *  sorted():               形成一个新的stream，按照自然排序
     *  sorted(Comparator com): 形成一个新的stream，按照 Comparator 排序
     */
    // Sort
    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(45, 23, 12, 99, 67, 32);
        list.stream().sorted().forEach(System.out::println);
    }

    //
    @Test
    public void test4(){
        List<Jedi> jediList = Jedi.getListJedi();
        // 按照年龄从小到大排序
        jediList.stream()
                .sorted((j1, j2) -> Integer.compare(j1.getAge(), j2.getAge()))
                .forEach(System.out::println);
    }
}
