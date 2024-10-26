package Java8.Map;

import Java8.Data.Jedi;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MapTest {

    /*
        所有的Key构成一个Set
        所有的Values构成一个Collection
        所有的Entry构成一个Set
    */
    @Test
    public void testMapOf(){
        /*
        Map.of()
        创建不可变的 Map 实例, 不可以使用put方法
        */
        Map<Integer, String> myMap = Map.of(
                1, "Hello",
                2,"What's up",
                3,"Yooo"
        );
        for(Map.Entry<Integer, String> entry: myMap.entrySet()){
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    @Test
    public void testPut(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, 2);
        map.put(2, new Jedi("Ahsoka"));

        // putAll() 方法用于将一个 Map 中的所有键值对复制到另一个 Map 中
        Map<Object, Object> map2 = new HashMap<>();
        map2.putAll(map);
        System.out.println("Map: " + map);
        System.out.println("Map2: " + map2);
    }

    @Test
    public void testDelete(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, 2);
        map.put(2, new Jedi("Ahsoka"));

        System.out.println("Removed: " + map.remove(1));
    }

    @Test
    public void testModify(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, 2);
        map.put(2, new Jedi("Ahsoka"));

        // 修改, 若没找到，就是添加
        Object integer = map.put(1, new Jedi("Anakin"));

        System.out.println("修改第一个: " + integer + ". 变为: " + map.get(1));
    }

    @Test
    public void testSearch(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, 2);
        map.put(2, new Jedi("Ahsoka"));

        System.out.println(map.get(2));
    }

    @Test
    public void testSize(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, 2);
        map.put(2, new Jedi("Ahsoka"));

        System.out.println(map.size());
    }

    @Test
    public void testTraversal(){
        Map<Object, Object> map = new HashMap<>();

        map.put(1, new Jedi("Anakin"));
        map.put(2, new Jedi("Ahsoka"));
        map.put(3, new Jedi("Obiwan"));
        map.put(4, new Jedi("Dooku"));
        map.put(5, new Jedi("Yoda"));

        // 遍历 Key
        Set<Object> keySet = map.keySet();
        Iterator<Object> iterator = keySet.iterator();
        for (Object o : keySet) {
            System.out.println(o);
        }
        // 增强 for
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // 遍历 Values
        Collection<Object> values = map.values();
        for(Object obj: values){
            System.out.println(obj);
        }

        // 遍历 Entry
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for(Map.Entry<Object, Object> entry: entries){
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
