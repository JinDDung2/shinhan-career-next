package ex7.Inheritance;

public class SalarySystem {
    public static void main(String[] args) {
        Permanet permanet = new Permanet();
        System.out.println(permanet
        );
    }
}

class Employee {
    int empNo;
    String empName;

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empNo +
                ", empName='" + empName + '\'' +
                '}';
    }
}

class Permanet extends Employee {
    int baseSalary;
}
