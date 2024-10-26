package Reflection;

//import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class UserTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {

        Class clazz = Class.forName("Reflection.User");
        Field nameField = clazz.getDeclaredField("name");
        System.out.println(nameField.getName());
        Constructor[] cons = clazz.getDeclaredConstructors();
        for (Constructor c: cons) {
            System.out.println(c);
        }

        Constructor con = clazz.getDeclaredConstructor(int.class);
        System.out.println("YOOOO" + con);
    }

//    @Test
    public void loveYou(){
        for (int i = 0; i < 25; i++) {
            System.out.println();
            for (int j = 0; j < 25; j++) {
                System.out.print("😘 ");
            }
        }
    }
}
