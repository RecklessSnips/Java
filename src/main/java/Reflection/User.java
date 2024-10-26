package Reflection;


public class User extends Creature<String>{

    private String name;
    private int age;

    public User(){
        System.out.println("User()...");
    }

    public User(String name){
        this.name = name;
    }

    public User(int age){
        this.age = age;
    }

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
