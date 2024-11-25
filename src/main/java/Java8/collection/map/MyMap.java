package Java8.collection.map;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MyMap {
    /*
        Map:接口，Key value pair的双列数据
        Key: 无序，不可重复，使用 Set 储存所有的Key ->
                                                自定义的类:
                                                    HashSet，Linked：
                                                        重写 equals 和 hashCode 方法
                                                    TreeSet：
                                                        实现 Comparable 或者 Comparator 接口
        Value: 无序，可重复，使用 Collection 储存所有的Key ->
                                                自定义的类：
                                                    要想正常使用 contains 方法，需要重写 equals 方法

        Entry: Key-Value 构成一个Entry对象，无序，不可重复，使用 Set 来存储

        实现类：
            1. HashMap：线程不安全，效率高，可存储 null 的 key 和 value
                LinkedHashMap：在原有的hashMap结构上添加了指针，能够保证遍历时可以按照添加顺序。
                                对于频繁的遍历操作，效率高于 HashMap
            2. TreeMap：可以按照添加的 Key 进行排序，此时考虑 Key 的自然或者定制排序
                        底层使用红黑树
            3. HashTable 古老的实现类
                Properties
     */
    @Test
    public void test0(){
        /*
            HashMap实现：
            底层使用 Node[] table 的数组 + LinkedList + 红黑树
            首次调用 put 方法，创建 Node[] table; 的数组
            当进行 put 的时候，调用 key 的 hashCode 方法来计算 key 的 hash value，得到在 table 里的存放位置：
                1. 如果 hash value 与已经存在的数据不相同，直接添加该 entry
                2. 如果 hash value 与某一个相同：
                    (1): 用 Key 所在类的 equals 方法跟这个已经存在的 Key 比较
                        a. equals 返回 false，直接添加 entry
                        b. equals 返回 true，将新的 Key 的 value 替换现有的 value （修改了）
         */
        Map map = new HashMap();
        map.put("AA", 123);
        map.put("45", 123);
        map.put("BB", 56);

        // KeySet
        Set set = map.keySet();
        System.out.println(set);

        // Values
        Collection values = map.values();
        System.out.println(values);

        // EntrySet
        Set set1 = map.entrySet();
        System.out.println(set1);
        // 遍历
        for(Object entry: set1){
            System.out.println(entry);
        }
        /*
            如果没有声明范型 Map<String, Integer> map = new HashMap();
            则下面代码不成立
         */
//        for(Map.Entry<String, Integer> entry: map.entrySet()){
//
//        }
        System.out.println(map);
    }

    @Test
    public void test1(){
        /*
            treeMap
            因为要根据 Key 排序，所以必须同一个类创建的对象
         */
        TreeMap map = new TreeMap();

        Jedi ahsoka = new Jedi(14, "Ahsoka");
        Jedi vader = new Jedi(21, "Vader");
        Jedi obiwan = new Jedi(25, "Obiwan");
        Jedi sabine = new Jedi(14, "Sabine");

        map.put(ahsoka, 98);
        map.put(vader, 89);
        map.put(obiwan, 76);
        map.put(sabine, 100);

        System.out.println(map);
    }
}
