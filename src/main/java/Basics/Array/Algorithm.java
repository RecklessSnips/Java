package Basics.Array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Algorithm {

    @Test
    public void reverseArrayInPlace(){
        /*
        In place 反转一个数组
         */

        int[] array = {1, 2, 3, 4};

        /*
        for循环，利用tmp变量保存后面的，然后跟前面的交换
         */
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            int tmp = array[i];
            /*
             - i 因为要看对称位置的index, 所以截止到倒数第几个，就是 减几（跟2d array 一样）
             而如果要一直循环到最后一个，就不减了（- 0）
             */
            array[i] = array[length - i - 1];
            array[length - i - 1] = tmp;
        }
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void reverseDiagonal(){
        /*
        将 n * n 数组，n为奇数，的两条对角线上的数基于中心反转
         */
        /*
        思路：
        1. 先反转主diagonal
        2. 再反转主diagonal
         */
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
//        int[][] matrix = {
//                {1, 2, 3, 4, 5},
//                {16, 17, 18, 19, 6},
//                {15, 24, 25, 20, 7},
//                {14, 23, 22, 21, 8},
//                {13, 12, 11, 10, 9}
//        };
        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            int tmp = matrix[i][i];
            // 对称位置, 外面先找哪个数组，里面再找哪个位置
            matrix[i][i] = matrix[length - i - 1][length - i - 1];
            matrix[length - i - 1][length - i - 1] = tmp;
        }
        // 这个col又跟上面col对称
        for (int i = 0; i < length / 2; i++) {
            int tmp = matrix[i][length - i - 1];
            // 对称位置
            matrix[i][length - i - 1] = matrix[length - i - 1][i];
            matrix[length - i - 1][i] = tmp;
        }

        for(int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }
    }

    /*
    创建一个长度为6的数组，要求范围在1-30之间且随机赋值，不能重复
     */
    @Test
    public void assign(){
        // 也可以用 set做，不断添加到set，直到加满
        /*
        1  2  3  4  5  6
        __ __ __ __ __ __
         */
        /*
        1. 创建数组
        2. 随机赋值第一个
        3. 从第二个开始，每一个赋值后要和前面的比较
            不重复：赋值
            重复：重新赋值
         */
        int[] lottery = new int[6];
        lottery[0] = (int) (Math.random() * 30) + 1;
        for(int i = 1; i < lottery.length; i++){
            // 设置一个flag，检查所有之前的，只要有重复则为true
            boolean repeat = false;
            do {
                // 假设不重复
                int num = (int) (Math.random() * 30) + 1;
                for(int j = 0; j < i; j++){
                    // 注意这里要检查所有之前的数字才能断定检查完毕
                    if(lottery[j] == num){
                        repeat = true;
                        break;
                    }
                }
                if(!repeat){
                    lottery[i] = num;
                }
            }while(repeat);
        }
        System.out.println(Arrays.toString(lottery));
    }

    public static void main(String[] args) {
        paperClip2(5);
        System.out.println();
        paperlipt(5);
        System.out.println();
        paperClip3(3);
    }

    public static void paperlipt(int x){
        /*
         回形数组，输入一个数字 x，形成一个 x * x的数组,
         但是要求回形赋值：
         input 3:
         output:
                 1 2 3
                 8 9 4
                 7 6 5

         input 2:
         output:
                 1 2
                 4 3

         input 4:
         output:
                 1   2   3   4
                 12  13  14  5
                 11  16  15  6
                 10  9   8   7
         */
        /*
        思路：
        外层向内赋值
        1. 按照长度赋值外层
        2. 公示：每次外圈填补算一层，这样一层层下来，需要填补 ( x + 1 ) / 2 层
         */
        int[][] matrix = new int[x][x];
        int num = 1; // 起始填充的数字

        // 一层一层地填充
        for (int layer = 0; layer < (x + 1) / 2; layer++) {
            // 小总结，凡是从结尾往前开始选，选哪个，总长度 - 1 即可

            /*
            上
             col变row不变，第几层就从第几行开始
             填补row的时候，起始点为layer的层数,
             col的起点是当前 layer层数，终点是右边距：总列数 - layer层数
             */
            for (int i = layer; i < x - layer; i++) {
                matrix[layer][i] = num++;
            }
            /*
            右
            row变col不变
            col的位置是最右边，也就是长度-层数，
            row的起点是layer + 1也就是下一层，终点是下边距：总行数 - layer层数
             */
            for (int i = layer + 1; i < x - layer; i++) {
                matrix[i][x - layer - 1] = num++;
            }
            /*
            下
            col变row不变
            row的位置是层数
            col的起点是最后第二个一直到开头, -2因为选右边倒数第二行
            注意，这块到 layer就停止了，也就是说没有补完左下角
             */
            for (int i = x - layer - 2; i > layer; i--) {
                // -1 是因为要选最后一行
                matrix[x - layer - 1][i] = num++;
            }
            /*
            左
            row变col不变
            row的位置是层数，
            col的起点是左下角到最上面
            左下角开始
             */
            for (int i = x - layer - 1; i > layer; i--) {
                matrix[i][layer] = num++;
            }
        }

        for (int[] rows : matrix) {
            for (int nums : rows) {
                System.out.print(nums + "\t");
            }
            System.out.println();
        }
    }

    public static void paperClip2(int x){
        int[][] matrix = new int[x][x];

        // 定义方向数组（右、下、左、上）
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        /*
        这两个数组的含义如下：
        右方向：dx[0] = 0 和 dy[0] = 1
        行数不变（dx[0] = 0），列数加 1（dy[0] = 1），表示向右移动。
        下方向：dx[1] = 1 和 dy[1] = 0
        行数加 1（dx[1] = 1），列数不变（dy[1] = 0），表示向下移动。
        左方向：dx[2] = 0 和 dy[2] = -1
        行数不变（dx[2] = 0），列数减 1（dy[2] = -1），表示向左移动。
        上方向：dx[3] = -1 和 dy[3] = 0
        行数减 1（dx[3] = -1），列数不变（dy[3] = 0），表示向上移动。
         */

        int row = 0, col = 0, dir = 0; // 起始位置和方向
        for (int i = 1; i <= x * x; i++) {
            matrix[row][col] = i; // 填充当前数字

            // 计算下一个位置
            int nextRow = row + dx[dir];
            int nextCol = col + dy[dir];

            // 检查是否需要改变方向
            if (nextRow < 0 || nextRow >= x || nextCol < 0 || nextCol >= x || matrix[nextRow][nextCol] != 0) {
                dir = (dir + 1) % 4; // 改变方向, % 4 是因为只有四个方向，来回切换
                nextRow = row + dx[dir];
                nextCol = col + dy[dir];
            }

            // 更新行和列位置
            row = nextRow;
            col = nextCol;
        }

        // 打印数组
        for (int[] rows : matrix) {
            for (int num : rows) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }

    /*
    不再递增，而是递减
    n = 3:
    output:
    9 8 7
    2 1 6
    3 4 5
     */
    public static void paperClip3(int x){
       int[][] matrix = new int[x][x];
       int num = x * x;
        //        右  下  左  上
        // 方向数组：向下是行数加，向左是列数减
       int[] dx = {0, 1, 0, -1};
       int[] dy = {1, 0, -1, 0};
       // direction 判断去哪，默认向右
       int direction = 0, row = 0, col = 0;
        for (int i = 0; i < x * x; i++) {
            matrix[row][col] = num--;

            int newRow = row + dx[direction];
            int newCol = col + dy[direction];

            // row和col必须在 >= 停止
            if(newRow >= x || newRow < 0 || newCol >= x || newCol < 0 || matrix[newRow][newCol] != 0){
                // 改变方向
                direction = (direction + 1) % 4;
                newRow = row + dx[direction];
                newCol = col + dy[direction];
            }

            row = newRow;
            col = newCol;
        }

        // 打印数组
        for (int[] rows : matrix) {
            for (int nums : rows) {
                System.out.print(nums + "\t");
            }
            System.out.println();
        }
    }
}