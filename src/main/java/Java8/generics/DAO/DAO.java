package Java8.generics.DAO;

public class DAO<T> {

    /*
        DAO: data access object
        这是一个泛型的应用场景，当数据库中多个表都需要一套通用的
        方法，那么可以把这套方法作为一个类或者接口，然后让具体的
        类来继承/实现
     */

    public void add(T obj){
        System.out.println("Add object");
    }

    public T get(T obj){
        return obj;
    }

    public <E> E getValue(){
        return null;
    }
}
