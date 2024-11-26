package Java8.collection.set;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class MySet {
    /*
        Set接口:
            无序：不等于随机性，每次遍历的顺序是一样的，不是添加顺序
                存储的数据在底层使用array，但是按照hashCode方法提供的
                hash 值来决定在哪个位置
            不可重复:
                保证添加元素按照equals判断时，不能返回true

            特点:
                1. Set接口中直接继承Collection中定义过的

        HashSet: 线程不安全，可以有null值
            LinkedHashSet: 作为HashSet的子类实现，让遍历时可以按照添加顺序遍历，但不是有序！
        TreeSet: 使用红黑树储存，必须属于同一个类，并按照这个类的属性排序，
                所以这个类需要实现Comparable,Comparator接口
                1. 但他不用 equals 来判断，而是 根据 Comparable 接口的 compare
                    如果为0则视为相同，不插入
                2. 如果使用Comparator，可以实现定制排序
     */
    @Test
    public void test0(){
        /*
        HashSet 添加元素的流程
            当调用 HashSet.add(E e) 时，HashSet 的底层会通过以下步骤来判断元素是否重复：

            步骤 1：计算 hashCode
                HashSet 调用元素的 hashCode() 方法，生成一个整数值（哈希值）。
                哈希值用来快速找到该元素可能存储的“存储桶”（bucket）。
            步骤 2：检查存储桶是否有冲突
                如果当前存储桶为空（没有其他元素存在），直接插入该元素，add() 返回 true。
                如果当前存储桶中已经有其他元素（哈希冲突），HashSet 会进一步调用 equals() 方法。
            步骤 3：调用 equals 方法进行比较
                如果 hashCode 相同，HashSet 会调用存储桶中每个元素的 equals() 方法，与待添加的元素逐一比较。
                如果 equals() 返回 true，表示元素重复，不插入，add() 返回 false。
                如果 equals() 返回 false，表示元素不同，允许插入

        判断重复的关键点
            HashSet 判断元素是否重复依赖以下两个方法：

            (1) hashCode() 方法
                hashCode 的作用是将对象映射到一个整数(hash value)，用于快速查找存储位置。
                    如果两个对象的 hashCode 不同，它们一定不相等，直接存储在不同的存储桶中。
                    如果两个对象的 hashCode 相同，它们可能相等（哈希冲突），需要进一步用 equals 方法确认。
            (2) equals() 方法
                当 hashCode 相同（哈希冲突）时，HashSet 会调用 equals 方法。
                    如果 equals 返回 true，表示对象重复，不会插入。
                    如果 equals 返回 false，表示对象不同，允许插入
         */
        Set set = new HashSet();
        set.add(123);
        set.add(456);
        // 会自动排除重复的
        set.add(456);
        set.add("AA");
        // 会自动排除重复的
        set.add("AA");
        set.add("CC");
        // 这里不会排除重复的,因为地址值不一样，除非重写equals和hashCode
        set.add(new User("Ahsoka"));
        set.add(new User("Ahsoka"));
        System.out.println(set);
    }

    @Test
    public void test1(){
        /*
            LinkedHashSet 作为 Hashset的子类，可以按照添加的顺序遍历
            对于频繁遍历的操作，效率比HashSet高
         */
        Set set = new LinkedHashSet();
        set.add(123);
        set.add(456);
        // 会自动排除重复的
        set.add(456);
        set.add("AA");
        // 会自动排除重复的
        set.add("AA");
        set.add("CC");
        // 这里不会排除重复的,因为地址值不一样，除非重写equals和hashCode
        set.add(new User("Ahsoka"));
        set.add(new User("Ahsoka"));
        System.out.println(set);
    }

    @Test
    public void test2(){
        /*
            TreeSet
            按照对象的指定属性跑排序。
            必须是同一类提供
         */
        Set set = new TreeSet();
        set.add(123);
        set.add(56);
        set.add(13);
        set.add(999);
        System.out.println(set);

        // 如果排序自定义类，需要实现Comparable或者Comparator接口
        Set users = new TreeSet();
        users.add(new User(12));
        users.add(new User(32));
        users.add(new User(2));
        users.add(new User(65));
        users.add(new User(33));
        System.out.println(users);
    }

    @Test
    public void test3(){
        /*
            利用Comparator
            实现定制排序
            根据姓名首字母
        */
        Set<User> set = new TreeSet<>(
                (Object u1, Object u2) -> {
                    return ((User)u1).getName().compareTo(((User)u2).getName());
                }
        );
        set.add(new User("Ahsoka"));
        set.add(new User("Vader"));
        set.add(new User("Obiwan"));
        set.add(new User("Dooku"));
        set.add(new User("Yoda"));
        System.out.println(set);
    }

    /*
        难题，看打印结果
        参考：https://www.youtube.com/watch?v=jyqIbr_ez_Q&list=PLmOn9nNkQxJH0qBIrtV6otI0Ep4o2q67A&index=546
     */
    @Test
    public void test4(){
        HashSet set = new HashSet();
        User u1 = new User(1001, "AA");
        User u2 = new User(1002, "BB");

        set.add(u1);
        set.add(u2);

        System.out.println(set);

        u1.setName("CC");
        set.remove(u1);
        System.out.println(set);

        set.add(new User(1001, "CC"));
        System.out.println(set);
        set.add(new User(1001, "AA"));
        System.out.println(set);
    }
}