package Java8.Compare;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MyComparator {

    /*
    1. 当元素类型没有实现 Comparable 接口但是不方便修改代码；或者不适合当前操作，可以考虑 Comparator
    2. 重写 compare(Object o1, Object o2) 方法, 比较 o1, o2
     */

    @Test
    public void test(){
        String[] array = new String[]{"aa", "mm", "dd", "jj", "ll", "kk", "cc", "zz", "bb"};
        // 从大到小
        Arrays.sort(array, (o1, o2) -> - o1.compareTo(o2));
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test1(){
        Product[] products = new Product[6];
        products[0] = new Product("GPro", 34);
        products[1] = new Product("Logitech", 43);
        products[2] = new Product("Razer", 12);
        products[3] = new Product("Cloud", 12);
        products[4] = new Product("HyperX", 65);
        products[5] = new Product("GProX", 90);
        // 价格从低到高，如果相同，名称低到高
        Arrays.sort(products, (p1, p2) -> {
            int compareResult = Double.compare(p1.getPrice(), p2.getPrice());
            if(0 == compareResult){
                return p1.getName().compareTo(p2.getName());
            }else{
                return compareResult;
            }
        });

        System.out.println(Arrays.toString(products));
    }
}
