package ex5.constructor;

public class ConstructTest {
    public static void main(String[] args) {
        ConstructTest test = new ConstructTest();
    }
}

class Employee {
    String name;

    public Employee() {
        System.out.println("Employee Constructor");
    }
}
