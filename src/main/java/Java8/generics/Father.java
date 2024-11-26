package Java8.generics;

public class Father<T> {

    String name;

    T type;

    public Father(String name, T type){
        this.name = name;
        this.type = type;
    }
}
