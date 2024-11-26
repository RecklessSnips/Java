package Java8.generics;

public class Son<T> extends Father<T>{


    public Son(String name, T type) {
        super(name, type);
    }

    /*
        范型方法，跟类的泛型参数无关，且需要有 <E> 来代表参数是泛型的，
        否则会认为有一个类叫 E
     */

    public <E> E returnAnyType(E type){
        return type;
    }

    /*
        可以是 static 的，因为泛型参数是在调用方法时确定的，而不是实例化类的时候
     */
    public static <E> E returnAnyType2(E type){
        return type;
    }
}
