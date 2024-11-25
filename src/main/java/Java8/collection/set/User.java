package Java8.collection.set;

import java.util.Objects;

// 一般类来实现Comparable，而不是comparator, 那么此时是自然排序
public class User implements Comparable {
    String name;

    int age;

    public User(String name){
        this.name = name;
    }

    public User(int age){
        this.name = "Jedi";
        this.age = age;
    }

    public User(int age, String name){
        this.age = age;
        this.name = name;
    }

    public int getAge(){return age;}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){return name;}

    @Override
    public String toString(){
        if(age == 0){
            age = 21;
        }
        return name + ": " + age;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User的equals方法被调用了");
        if(o instanceof User){
            User u = (User) o;
            return name.equals(u.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /*
        TreeSet会根据这个来排序，如果为0则视为相同
        这个纯自定义两个类是否相等，按照自然排序
        如果有需要，可以进行二级排序等等...
     */
    @Override
    public int compareTo(Object o) {
        return Integer.compare(age, ((User)o).age);
    }
}
