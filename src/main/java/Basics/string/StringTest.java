package Basics.string;

import org.junit.jupiter.api.Test;

public class StringTest {

    /*
        对比 String 常量池和在 Heap 中的区别
     */

    @Test
    public void test0(){
        // abc 是一个 String 常量，而 s1 是一个变量
        // s1 储存在 run time stack里，而 abc 储存在 String 常量池里
        String s1 = "abc";
        String s2 = s1;
        s1 += "d";
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test1(){
        // 生命在方法区里的常量池中
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1.equals(s2));
        // new 都在heap中
        String s3 = new String("ahsoka");
        String s4 = new String("ahsoka");
        System.out.println(s3.equals(s4));

        System.out.println(s1 == s4);
    }

    @Test
    public void test2(){
        /*
        常量与常量拼接，都在常量池，且常量池中都是唯一的
        只要有一个是变量，那么都在 heap中

        调用 intern(), 会让结果在常量池中声明, 返回值也就在常量池中
         */
        String s1 = "a";
        String s2 = "b";

        String s3 = "ab";
        String s4 = "a" + "b";
        String s5 = s1 + "b";
        String s6 = "a" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s6 == s7); // false

        String s8 = s7.intern();
//        String s9 = new String("ab").intern(); // true
        System.out.println(s3 == s8); // true
    }

    @Test
    public void test3(){
        String str = new String("good");
        char[] ch = {'t', 'e', 's', 't'};

        change(str, ch);

        System.out.println(str);
        System.out.println(ch);
    }

    public static void change(String str, char ch[]){
        str = "test ok";
        ch[0] = 'b';
    }

    @Test
    public void test4(){
        String s1 = "abc";
        String s2 = "a";
        // 只要变量涉及进来，就会在heap，所以是false
        String s3 = s2 + "bc";
        System.out.println(s1 == s3);

        // 注意，final 用来修饰 constant，所以 s4 现在是一个 constant
        // 那么 constant 会被放在String 常量池里，所以这次为 false
        final String s4 = "a";
        String s5 = s4 + "bc";
        System.out.println(s1 == s5);
    }
}
