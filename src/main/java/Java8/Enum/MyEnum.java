package Java8.Enum;

import java.util.Arrays;

public class MyEnum {
    /*
        当类的对象是有限的，可以被穷举完的；有限的状态；一组常量；身份权限
        强烈推荐用ENUM

        所以，一个 Enum 类里的常量，都是这个 Enum 的一个对象实例！！！

        默认继承与 java.lang.Enum, 而不是 Object
     */

    public static void main(String[] args) {

        Season winter = Season.WINTER;
        System.out.println(winter.getDescripton());

        // values() 获取所有的enum对象
        Season[] values = Season.values();
        System.out.println(Arrays.toString(values));

        State running = State.RUNNING;
        System.out.println(running);
        // 拿到名字
        String name = running.toString();
        System.out.println(running.toString().getClass());
        System.out.println(name);

        /*
            valueOf(String name) 根据name获取同名的enum对象
            如果名字不对，则会抛出异常: IllegalArgumentException
         */
        State ready = State.valueOf("READY");
        System.out.println(ready);
        ready.common();
        ready.showState();
        State terminated = State.TERMINATED;
        terminated.showState();
    }
}

enum Season{
    // 格式：
    // 1. 提供当前 Enum 类的对象，多个对象之间用 , 隔开，最后一个对象以 ; 结尾
    SPRING("春天", "春暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    WINTER("冬天", "冰天雪地");

    // 2. 声明 enum 对象的属性，用 private final 修饰
    private final String seasonName;
    private final String descripton;

    // 3. 私有化类的构造器，并给对象属性赋值
    private Season(String name, String descripton){
        this.seasonName = name;
        this.descripton = descripton;
    }

    // 4. 其他功能：提供 toString, 而不是默认的 Enum 的 toString
    @Override
    public String toString(){
        // 不重写的话，不会打印地址值，而是对象的名字，比如 WINTER
        return "Season: {" +
                seasonName +
                ", " +
                descripton +
                "}";
    }

    // 其他功能，获取属性值
    public String getName(){
        return this.seasonName;
    }

    public String getDescripton(){
        return this.descripton;
    }
}

interface MyInterface{
    void showState();

    void common();
}

/*
    Enum 还可以 implement 接口，并且每个对象可以实现自己的接口
    否则每个对象将会拥有同样的这个方法
 */
enum State implements MyInterface{

    AWAIT{
        @Override
        public void showState() {
            System.out.println("等待中");
        }
    },
    READY{
        @Override
        public void showState() {
            System.out.println("准备就绪");
        }
    },
    RUNNING{
        @Override
        public void showState() {
            System.out.println("运行中");
        }
    },
    TERMINATED{
        @Override
        public void showState() {
            System.out.println("已退出");
        }
    };

    @Override
    public void common(){
        System.out.println("所有Enum对象拥有同一个common方法");
    }
}
