package Basics.string.practice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class Practice {
    /*
    给定两个string，找到它们共同的，最大 subString。
    s1 = "dgyawgbhellouksdhawb"
    s2 = "dugauhelloyg"

    共同：hello

    提示：将短的那个string进行长度依次递减的substring与长的比较
        相当于首尾一个指针，在指针的范围内不断遍历 sub string

    第一次：查看 dugauhelloyg
    第二次：长度减1: 所以要么是 dugauhelloy（末尾去掉）或者 （头部去掉）ugauhelloyg
    ...
    其中，每一次循环这个substring，从头到尾：
    比如某一次长度减了4，那么应该从 dugauihellod 开始查看：
        第一次：dugauihellod
        第二次： ugauihellodb
        第三次：  gauhello
        第四次：   auhelloy
        第五次：    uhelloyq
    跟这个逻辑来
     */

    public static String findMaxSubString(String s1, String s2){
        String bigger = (s1.length() >= s2.length()) ? s1 : s2;
        String smaller = (s1.length() < s2.length()) ? s1 : s2;
        ArrayList<String> subStrings = new ArrayList<>();
        for (int i = 0; i < bigger.length(); i++) {
            int head = 0;
            int tail = bigger.length() - i;
            while (tail <= smaller.length()){
                String substring = smaller.substring(head, tail);
                if(bigger.contains(substring)){
                    subStrings.add(substring);
                }
                head++;
                tail++;
            }
        }
        return subStrings.stream()
                // s -> s.length()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static String findMaxSubString2(String s1, String s2){
        // 用 for loop 的另一种写法
        // 这个是返回第一个substring
        String bigger = (s1.length() >= s2.length()) ? s1 : s2;
        String smaller = (s1.length() < s2.length()) ? s1 : s2;
        int length = bigger.length();
        for (int i = 0; i < length; i++) {
            for(int head = 0, tail = length - i;
                tail <= smaller.length();
                head++, tail++){
                String substring = smaller.substring(head, tail);
                if(bigger.contains(substring)){
                    return substring;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String maxSubString = findMaxSubString("dgyawgbhellouksdhawbwhatsupp", "dugauhelloygwhatsupp");
        String maxSubString2 = findMaxSubString2("dgyawgbhellouksdhawb", "dugauhelloyg");
        System.out.println(maxSubString);
        System.out.println(maxSubString2);
    }
}
