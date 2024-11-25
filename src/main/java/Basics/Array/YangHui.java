package Basics.Array;

import java.util.Arrays;

public class YangHui {

    public static void main(String[] args) {
        print(yanghui(7));
        System.out.println();
        printPretty(yanghui(7));
    }

    /*
    打印出 x 行杨辉三角
     */
    public static int[][] yanghui(int rows){
        // 初始化 2D array
        int[][] yanghui = new int[rows][];
        // 初始化每一行
        for (int i = 1; i <= rows; i++) {
            yanghui[i-1] = new int[i];
            // 每行第一个和最后一个都为1
            yanghui[i-1][0] = yanghui[i-1][i - 1] = 1;
        }
        /*
         每行动态生成数组
         1. 从第三行开始, 第二个到倒数第二个开始
         2. 上一行的第一个和下一个相加
         3. 直到末尾被加上
         */
        for(int i = 2; i < rows; i++){
            for(int j = 1; j < yanghui[i].length - 1; j++){
                yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
            }
        }

        return yanghui;
    }

    public static void print(int[][] array){
        for(int[] row: array){
            System.out.println(Arrays.toString(row));
        }
    }

    // 按照金字塔的方式打印
    public static void printPretty(int[][] array){
        for(int i = 0; i < array.length; i++){
            /*
             打印每一行之前要计算多少个空格
             空格数 = 总行数 - 当前行数

             右下角不会完美的，因为数字会有长度
             */
            int space = array.length - (i + 1);
            System.out.print(" ".repeat(space * 2));
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.print(" ".repeat(space * 2));
        }
    }
}
