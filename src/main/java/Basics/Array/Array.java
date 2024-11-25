package Basics.Array;

import java.util.Arrays;

public class Array {
    /*
    注意，array属于reference type。任何reference type的直接赋值都是分配地址
    而不是值，所以直接赋值 int[] a2 = a1; 会共享 heap 中的唯一地址！！！
     */

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3};
        int[] a2 = a1;
        a2[0] = 0;
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
    }

    /*
    实现数组的复制
     */
    public static void arrayCopy(){
        int[] a1 = {1, 2, 3};
        int[] a2 = a1.clone(); // 或者使用 Arrays.copyOf(a1, a1.length)
    }
}
