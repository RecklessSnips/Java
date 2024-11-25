package Java8.collection.list;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList {
    /*
    List接口中：
        1. 元素有序
        2. 可重复
        3. 替代 array
        4. 每个元素都有一个 Integer 类型标记其在容器中的位置，可根据这个index拿元素

    arrayList, linkedList, vector
    相同点:
        1. 元素有序
        2. 可重复
    不同点：


    ArrayList: 底层使用 Object[] array 存储
        初始容量为10，不够的话扩容1.5倍

    结论：建议使用带参构造器，设定初始数组长度避免扩容，提高效率
     */
    public static void main(String[] args) {
        /*
            常用方法：因为是实现类，还有数组，所以会有自己的方法
                add()
                int indexOf
         */
        List list = new ArrayList();
        list.add(123);
        list.add("abc");
        list.add(new Person2("Ahsoka"));
        list.add(123);

        System.out.println(list.indexOf(123));
        System.out.println(list.lastIndexOf(123));
        // 重载 overload remove 方法: Object remove(int index)
        System.out.println(list.remove(2));
        System.out.println(list);
        // set
        list.set(2, 456);
        // List subList(int from, int to): 返回子集合: [from, to)
        System.out.println(list.subList(0,1));
        System.out.println(list);
    }
}

class Person2{
    String name;

    public Person2(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Person2){
            Person2 p = (Person2) obj;
            return name.equals(p.name);
        }
        return false;
    }

    @Override
    public String toString(){
        return "Person2: " + name;
    }
}