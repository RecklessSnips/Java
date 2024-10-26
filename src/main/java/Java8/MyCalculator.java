package Java8;

//import org.testng.annotations.Test;

import java.util.function.Predicate;

public class MyCalculator{

//    @Test
    public void testInt(){
        // 创建一个实现了Calculator interface的对象
        Calculator<Integer, Integer> calculator = (Integer a, Integer b, String operator) -> {
            int result = 0;
            switch (operator){
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
            }
            return result;
        };

        System.out.println(calculator.calculete(1, 1, "+"));
        System.out.println(calculator.calculete(1, 1, "-"));
        System.out.println(calculator.calculete(1, 1, "*"));
        System.out.println(calculator.calculete(1, 1, "/"));
        System.out.println("--------------------------------------");

        Calculator<Integer, Integer> calculator1 = calculator :: calculete;
        System.out.println(calculator1.calculete(1, 1, "+"));

    }
}
