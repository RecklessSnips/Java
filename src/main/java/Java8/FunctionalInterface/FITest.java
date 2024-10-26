package Java8.FunctionalInterface;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FITest {

    /*
    *
    * 如果一个接口里只有一个抽象方法（待实现），则为函数式接口
    *
    * 四大核心
*                                返回类型                    用途                        方法
    *   1. Consumer<T>:             无               对 T 类型的对象应用操作              accept
    *   2. Supplier<T>              T               返回类型为 T 的对象                  T get
    *   3. Function<T, R>           R               对 T 应用，返回R类型对象            R apply(T t)
    *   4. BiFunction<T, U, R>      R               接受两个参数T，U，返回 R         R apply(T, t, U, u)
    *   5. Predicate<T>          boolean            判断 T 类型是否满足约束           boolean test(T t)
    *
    * 注意：Lambda接口，就是一个匿名的函数式接口的实现对象，所以可以当作参数传递
    */

    @Test
    public void testConsumer(){
        // lambda方法的实现过程！
        happyTime(2500, money -> System.out.println("买了个电脑好开心，花了: " + money));
    }

    // 传入一个consumer的实例对象, 并且运行它的方法
    public void happyTime(int money, Consumer<Integer> consumer){
        // lambda对象的调用过程
        consumer.accept(money);
    }

    @Test
    public void testSupplier(){
        Jedi ahsoka = new Jedi("Ahsoka");
        System.out.println(getJediName(ahsoka, () -> "Trained Jedi is: " + ahsoka.getName()));

        // 方法引用
        Jedi anakin = new Jedi("Anakin");
        System.out.println(getJediName(anakin, anakin :: getName));
    }

    public String getJediName(Jedi jedi, Supplier<String> temple){
        return temple.get();
    }

    @Test
    public void testFunction(){
        String name = "Anakin";
        System.out.println(trainJedi(name, jediName -> new Jedi(jediName)));
    }

    public Jedi trainJedi(String name, Function<String, Jedi> function){
        // String是输入，Jedi是输出
        return function.apply(name);
    }

    @Test
    public void testPredicate(){
        // 这里的 age -> age > 15, 就是lambda对象的实现过程，在这里写哈哈
        List<Jedi> jedi = filterJedi(Jedi.getListJedi(), age -> age > 15);
        System.out.println(jedi);
    }

    // 给一个集合，根据某种predicate规则来过滤数据，规则由predicate的方法决定
    public List<Jedi> filterJedi(List<Jedi> jedi, Predicate<Integer> predicate){
        List<Jedi> jedis = new ArrayList<>();
        for(Jedi j: jedi){
            // 这里的 j.getAge()充当了参数，这里是可以直接调用lambda对象的方法：test的
            // 调用过程
            if(predicate.test(j.getAge())){
                jedis.add(j);
            }
        }
        return jedis;
    }
}
