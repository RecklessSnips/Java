package Java8.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MyCollection {

    /*
        Collection: 接口
        注意，如果要在collection中让contains，remove方法正常使用，需要重写
        相对应类的 equals 方法
     */

    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(new Date());
        collection.add(new Person("Ahsoka"));

        // 这里哪怕是又new了一个，contains调用的是equals方法
        System.out.println(collection.contains(new Person("Ahsoka"))); // true

        // boolean remove(Object o)
        System.out.println(collection.remove(1));
        System.out.println(collection);
    }
}

class Person{
    String name;

    public Person(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Person){
            Person p = (Person) obj;
            return name.equals(p.name);
        }
        return false;
    }

    @Override
    public String toString(){
        return "Person: " + name;
    }
}
