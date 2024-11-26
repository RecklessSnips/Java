package Java8.generics;

import java.util.List;

public class Example {
    /*
        T extends/super E
        说明要明确说明T，要小于等于，或者大于等于 E 的实现类或者子类，取决于 E 是接口还是类
     */
    public static void main(String[] args) {
        Person<Contact> person = new Person<>();
        Person<Introduction> person1 = new Person<>();
    }

    private interface info{}

    private static class Contact implements info{}

    private static class Introduction implements info{}

    /*
        注意 generic T 只能用 Extends 而不能用 Super
     */
    private static class Person<T extends info>{
        public void getI(List<? extends T> list){}

        public List<? super T> getX(){
            return null;
        }

        public <T extends info> void getY(T item) {
            System.out.println(item);
        }

    }
}
