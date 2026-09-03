package ex3.staticex;

public class EmployeeTest {

    public static void main(String[] args) {
        Employee.cnt = 1;
        Employee.print();
        test();
        System.out.println("---");

        Emp.print();
    }

    static void test() {
        System.out.println("test");
    }

}

class Employee {
    static int cnt = 0;

    static void print() {
        System.out.println("cnt = " + cnt);
    }

}

class Emp {
    int empNo = 0;
    
    static void print() {
//        System.out.println("empNo = " + empNo); // 컴파일 에러: instance 변수 사용 X
        m1();
//        m2(); // 인스턴스 메서드라서 객체 없이 호출 불가

        Emp one = new Emp();
        System.out.println(one.empNo);
        one.m2();
    }

    static void m1() {
        System.out.println("m1");
    }

    void m2() {
        System.out.println("m2");
    }
}