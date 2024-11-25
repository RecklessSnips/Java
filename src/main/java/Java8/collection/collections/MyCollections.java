package Java8.collection.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyCollections {
    /*
        Collections：操作 Collection 和 Map 的工具类

        常用方法：
            reverse(List) 反转元素
            shuffle(List) 随机排序元素
            sort(List) 自然排序，升序
            sort(List, Comparator) 自定义排序
            swap(List, int, int) 将list中 i 处和 j 处元素交换

            Object max(Collection) 根据自然排序，返回最大
            Object max(Collection, Comparator) 根据定制排序
            Object min(Collection) 自然排序的最小
            Object min(Collection, Comparator) 定制排序

            int frequency(Collection, Object) 返回元素的出现次数
            void copy(List dest, List src) 将 src 复制 到 dest 中
            boolean replaceAll(List ist, Object oldVal, Object newVal)
            用新值替换 List 对象的所有旧值
     */
    @Test
    public void test0(){
        List list = new ArrayList();
        list.add(123);
        list.add(123);
        list.add(456);
        list.add(432);
        list.add(321);
        list.add(789);

        Collections.shuffle(list);

        System.out.println(list);

        Collections.swap(list, 1, 2);

        System.out.println(list);

        System.out.println(Collections.max(list));

        System.out.println(Collections.frequency(list, 123));

        // 注意size要大于 src 的 size，否则会有异常
//        List list1 = new ArrayList();
        // 正确做法
        List<Object> list1 = Arrays.asList(new Object[list.size()]);
        Collections.copy(list1, list);
        System.out.println(list1);
    }

    @Test
    public void test1(){
        /*
            Collections 提供了多个 synchronizedXxx() 方法，可以使指定集合
            包装成线程安全的集合，解决多线程 concurrent 访问集合的线程安全问题
         */
        List list = new ArrayList();
        list.add(123);
        list.add(123);
        list.add(456);
        list.add(432);
        list.add(321);
        list.add(789);

        List list1 = Collections.synchronizedList(list);
    }
}
