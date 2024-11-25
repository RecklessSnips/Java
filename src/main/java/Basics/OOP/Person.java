package Basics.OOP;

public class Person {
     String name;
     int age;
     int x = 1;

     protected static String country;

     public Person(){
          System.out.println("默认父类构造器被调用");
     }

     public Person(String name){
          this.name = name;
     }

     public Person(String name, int age){
          this(name);
          this.age = age;
     }

     public void show(){
          System.out.println("Name: " + this.name);
     }
}
