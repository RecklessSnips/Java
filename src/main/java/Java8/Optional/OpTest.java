package Java8.Optional;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OpTest {
    /**
     * Optional<T> 是为了避免代码出现null pointer exception
     * 它是一个容器类，就像数组，collection，保存 T 类型的值，代表它存在，或者仅仅保存null，代表不存在。
     * 如果存在，调用 isPresent() 返回 true，调用 get() 返回该对象
     *
     * Instantiate:
     *
     * static <T> Optional<T> ofNullable(T value), 用来创建Optional的instance，value可以为空也可有值
     * Optional.of(): 创建一个 Optional instance，不能为空
     * Optional.empty(): 创建一个 Optional instance，必须为空
     */

    @Test
    public void testOrElse(){
        //  1. Instantiate: 通过 ofNullable(T value)创建一个optional实例，value可空可不空
        String jedi = "Ahsoka";
        jedi = null;

        Optional<String> option = Optional.ofNullable(jedi);

        /*
            2. 当不确定传入 ofNullable 的值是否是null是，可以准备一个代替值，用orElse来准备：
            orElse(T other): 如果Optional实例内部的 value 不为null，返回value，如果为null，返回这个 other 的值
        */
        String master = "Anakin";
        // 将传入 Optional 的值，通过 Optional 实例拿出来
        String apprentice = option.orElse(master);

        System.out.println(apprentice);

        // 变成空的
        option.isEmpty();

        System.out.println(option.orElseThrow());

    }

    @Test
    public void testInstantiate(){
        Jedi ahsoka = new Jedi("Ahsoka");
        Optional<Jedi> jedi = Optional.of(ahsoka);
        System.out.println(jedi.get());

        Optional<Jedi> jedi1 = Optional.empty();
        System.out.println(jedi1);

        ahsoka = null;
        Optional<Jedi> jedi2 = Optional.ofNullable(ahsoka);
        System.out.println(jedi2);
    }

    @Test
    public void testMethod(){
        // isPresent()
        Optional<Object> opt = Optional.empty();
        if(opt.isPresent()){
            System.out.println(opt.get());
        }else{
            System.out.println("Empty");
        }

        Optional<String> s = Optional.of("What's up");
        if(s.isPresent()){
            System.out.println(s.get());
        }else{
            System.out.println("Empty");
        }
    }

    /*
        void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)
        如果 optional 有值，执行 consumer，如果没有执行 第二个 emptyAction
     */
    @Test
    public void testIsPresentOrElse(){
        Optional<String> ahsoka = Optional.of("Ahsoka");

        /*
            ifPresent:  如果存在，将 Consumer 函数应用到value上, 如果null，do nothing
            这个参数将是 Optional 的 value

            （与 orElse 区别： orElse是返回这个 value，并不采取措施）
         */
        ahsoka.ifPresent(value -> System.out.println("Value is: " + value));

        // ifPresentOrElse
        ahsoka.ifPresentOrElse(
                // System.out::println
                value -> System.out.println(value),
                () -> System.out.println("Optional is null")
        );

    }

    /*
        Or

        Optional<T> or (Supplier< ? extends Optional<? extends T>> supplier)

        如果value非空，返回对应的Optional，如果空，返回一个由指定Supplier提供的Optional
     */
    @Test
    public void testOr(){
        Optional<String> ahsoka = Optional.of("ahsoka");
        // 返回的是一个 Optional
        Optional<String> anakin = ahsoka.or(() -> Optional.of("Anakin"));
        // 返回的是一个 值，不是 Optional 对象
        String anakin1 = ahsoka.orElse("Anakin");
        anakin.ifPresent( System.out::println);
        System.out.println(anakin1);
    }

    /*
        建议转换成 Stream之后在进行这些操作

        跟 Stream API一样，将 Optional 里的值进行转换

        Map: 返回一个包含被执行函数后的结果的Optional   map(Function<? super T, ? extends U> mapper)
            如果值为null，返回空的 Optional

        FlatMap：返回被执行函数后的结果，不是Optional，如果 value 为空返回空的 Optional
     */
    @Test
    public void testMapFlatMap(){
        Optional<String> optional = Optional.of("Hello");
        Optional<Integer> length = optional.map(String::length);
        length.ifPresent(System.out::println); // 输出 5

        //  flatMap方法的功能是将一个包含Optional对象的Optional扁平化，即需要传入一个返回Optional的Lambda表达式。
        List<Integer> list = Arrays.asList(4, 2, 5, 3, 1);
        Optional<List<Integer>> list1 = Optional.of(list);

        // 方法1: 用map将list提取出来
        Optional<Stream<Integer>> integerStream = list1.map(l -> l.stream());
        integerStream.ifPresentOrElse(
                stream -> stream.forEach(System.out::println),
                () -> System.out.println("Optional 的值为 null")
        );

        // 方法2: 用 flatMap直接取 stream(),
        // 这个是将 Optional 直接转换成一个 stream
        Stream<List<Integer>> stream = list1.stream();
//        System.out.println("有几个value：" + stream.count());
        // 因为flapMap将元素内部看成一个个的值，这里就是单纯一个List
        // 相当于：Stream<Integer> stream1 = list.stream();
        Stream<Integer> integerStream1 = stream.flatMap(l -> l.stream());

        integerStream1.forEach(System.out::println);
    }
}
