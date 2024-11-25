package Java8.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Iterator {

    public static void main(String[] args) {
        Collection collection = new ArrayList();
        collection.add(1);
        collection.add(new Date());
        collection.add(new Person("Ahsoka"));

        /*
            for(Object obj: collection)底层调的就是 Iterator

            每次调用 iterator 都会返回一个全新的iterator，
            所以不可以这样：
            while(collection.iterator().hasNext())
            next会让指针下移，所以也不可以：
            while(iterator.next() != null)
         */
        java.util.Iterator iterator = collection.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
            iterator.remove();
            System.out.println("当前List:" + collection);
        }
        System.out.println("迭代后List: " + collection);
    }
}
