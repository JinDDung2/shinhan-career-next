package practice;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculatorFirst = new Calculator();

        // 연산자 우선순위 무시
        // 1000 + 50 * 5.2 / 10
        calculatorFirst.setResult(1000);
        calculatorFirst.plus(50);
        calculatorFirst.multiple(5.2);
        calculatorFirst.divide(10);
        System.out.println("result1 = " + calculatorFirst.result);

        // 90 * 1000 / 30 - 10
        Calculator calculatorSecond = new Calculator();
        calculatorSecond.setResult(90);
        calculatorSecond.multiple(1000);
        calculatorSecond.divide(30);
        calculatorSecond.subtract(10);
        System.out.println("result2 = " + calculatorSecond.result);
    }
}
