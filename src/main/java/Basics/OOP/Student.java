package Basics.OOP;

public class Student extends Person{

    int x = 2;

    public Student(){
        /*
         为什么子类对象的heap空间含有父类所有的属性和方法，就是因为
         默认调用了super构造器
         */
    }

    public Student(String name, int age){
        // 如果不写super构造器，默认调用 super()
        super(name, age);
    }

    @Override
    public void show(){
        System.out.println("Name hi: " + this.name);
    }

    public static void main(String[] args) {
        Person person = new Person("Jedi");
        Person.country = "China";
        person.show();

        var student = new Student("Ahsoka", 14);
        System.out.println("Country: " + Student.country);
        student.show();

        // 测试父类构造器被调用
        Person st = new Student();
        System.out.println(st.x);

        Student st1 = new Student();
        System.out.println(st1.x);
    }
}
