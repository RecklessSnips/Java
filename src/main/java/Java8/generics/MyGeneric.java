package Java8.generics;

import Basics.Array.Array;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyGeneric {
    /*
        泛型是为了规定一个Collection在设计/声明阶段的数据类型为某一种
        而不是Object，因为Object包含所有的类型，会导致后续操作不变
        比如：Collection里同时含有 Integer，String，Person类，不利于操作。

        注意：
            1. Comparable实现泛型时，要比哪个类，泛型就是哪个类
            2. 静态方法中不能使用类的泛型，因为泛型是在对象创建的时候声明，而static比类加载的早
            3. 异常类型不能是泛型
            4. 继承父类的泛型，可以允许以下情况:
                class Father<T1, T2>{}
                1. 没有类型：class Son extends Father {} ---> 等价于 class Son extends Father<Object, Object>{}
                2. 具体类型：class Son extends father<Integer, Integer>{}
                3. 全部保留：class Son<T1, T2> extends Father<T1, T2>{}
                4. 部分保留：class Son<T2, A, B> extends Father<Integer, T2>{}
            5. 范型方法：
            方法中出现了泛型结构，但是跟泛型类的泛型参数无关。见 Son
     */
    public static void main(String[] args) {
        Son<Integer> son = new Son<>("Son", 21);
        System.out.println(son);

        System.out.println(son.returnAnyType("Ahsoka"));
        System.out.println(son.returnAnyType(123));
        System.out.println(Son.returnAnyType2(123));
    }

    @Test
    public void test0(){
        /*
            泛型在继承方面：
                虽然 A 是 B 的父类，但是 G<A>, G<B> 不构成子父类关系，他们是并列
                但是: A<G>, B<G>，构成子父类关系
         */
        Object[] arr = null;
        Array[] arr1 = null;
        // 这样是可以的，子类给父类，体现多态
        arr = arr1;

        List<Object> list = null;
        List<Integer> list1 = null;
        // 不行
        // list = list1;
        ArrayList<Object> list2 = null;
        list = list2;
        // 必须满足泛型也是
        // list1 = list2;
        ArrayList<Integer> list3 = null;
        list1 = list3;
    }

    @Test
    public void test1(){
        /*
            通配符：?
                类 A 是 B 的父类，G<A>, G<B>是没有关系的，二者
                共同父类是 G<?>

            T 用于 定义泛型。当一个类需要对某种特定类型的对象进行操作时
            ? 用于 泛型使用处，表示不关心，不知道所操作的类型，可以是任何
                只能在方法的参数或返回值类型，而不能用在泛型的定义中

            1. 每次使用时，? 都可以代表不同的类型。
            2. 更适合处理不关心具体类型，但只需要读取或检查类型的场景
            3. 正确用法是用于定义方法参数或返回值类型，而不是方法的泛型类型参数

            只关心读取：
            public void printLists(List<?> list1, List<?> list2) {
                Object item1 = list1.get(0); // 类型是 Object
                Object item2 = list2.get(0); // 类型也是 Object
            }

            注意，T 泛型是一个需要明确制定的泛型参数，跟 通配符不一样

            限制性：
                ?：任意类型。
                ? extends T：T 类型及其子类。
                ? super T：T 类型及其父类

            注意：不能向 List<?> 中添加元素，除了 null
         */
        List<Object> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        // compile error
//        l1 = l2;
        // 允许任意类型，且 List<? extends Object> 和 List<?> 等价
        List<?> l3 = new ArrayList<>();
        l3 = l2;

        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<String> strList = Arrays.asList("A", "B", "C");

        printList(intList); // 输出：1 2 3
        printList(strList); // 输出：A B C

    }

    public void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<Number> doubleList1 = Arrays.asList(1.1, 2.2, 3.3);

        printNumbers(intList);   // 输出：1 2 3
        printNumbers(doubleList); // 输出：1.1 2.2 3.3
        printList(doubleList1);

        // 不能向 List<? extends T> 中添加任何非 null 的元素，因为具体类型不确定
    }

    /*
        上界通配符（? extends T）
            表示类型必须是 T 或 T 的子类
        使用场景：
            当你只需要 读取 泛型中的数据，不向其中添加数据时。
            上界通配符保证了类型的 安全读取


        List<? extends Number> list = new ArrayList<>();
        list.add(1);     // 编译错误    可能实际类型是 List<Double>
        list.add(1.0);   // 编译错误    可能实际类型是 List<Integer>
        list.add(null);  // 可以
        为什么会编译错误？

        list 的实际类型可能是 List<Integer>，但你尝试添加一个 Double。
        编译器无法验证类型的安全性，因此不允许添加任何具体的非 null 值

     */
    public void printNumbers(List<? extends Number> list) {
        for (Number num : list) {
            System.out.println(num);
        }
    }

    @Test
    public void test3(){
        List<Number> numList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        addNumbers(numList);
        addNumbers(objList);
        addNumbers(intList);

        System.out.println(numList); // 输出：[1, 2, 3]
        System.out.println(objList); // 输出：[1, 2, 3]
        System.out.println(intList);
    }

    /*
        下界通配符（? super T）
            表示类型必须是 T 或 T 的父类。
        使用场景：
            当你只需要向泛型中 写入 数据，而不需要读取具体类型时。
            下界通配符保证了类型的 安全写入

        注意:
            可以向 List<? super T> 中添加 T 类型及其子类型的对象。
            读取时只能保证是 Object 类型，因为具体类型未知

        List<? super Integer> list = new ArrayList<>();
        list.add(1);      // 合法，Integer 是 Integer 的本身
        list.add(2);      // 合法，Integer 是 Integer 的本身
        // list.add(1.0); // 编译错误，Double 不是 Integer 或其子类
        在使用 ? super T 时，编译器并不知道集合的具体类型，只知道它是某种 T 的父类。
        由于父类可能比 T 范围更大，添加父类类型的元素会导致潜在的类型安全问题

        使用下界通配符时：
            可以安全地向集合中添加 T 或其子类的元素。
            但读取时，返回值只能被视为 Object 类型，因为无法确定实际类型

     */
    public void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }
}
