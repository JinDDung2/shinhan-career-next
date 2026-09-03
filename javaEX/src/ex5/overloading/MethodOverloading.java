package ex5.overloading;

public class MethodOverloading {
    static int add(int a, int b) {
        System.out.println(" add(int a, int b) 호출 ");
        return a + b;
    }

    static double add(double a, double b) {
        System.out.println(" add(double a, double b) ");
        return a + b;
    }

    public static void main(String[] args) {
        int a1 = 10, b1 = 20;
        double a2 = 10, b2 = 20;
        System.out.println(add(a1, b1));
        System.out.println(add(a2, b2));
    }
}
