package practice.lab03.datastructure;

import practice.lab03.finalinterface.Employee;

import java.util.ArrayList;
import java.util.Collections;

public class DataTest {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("kim", 1_000_000));
        employees.add(new Employee("park",3_000_000));
        employees.add(new Employee("hong",2_000_000));
        employees.add(new Employee("lee", 4_000_000));
        employees.add(new Employee("heo", 5_000_000));

        double totalAmount = 0;
        for (Employee employee : employees) {
            totalAmount += employee.calculatePay();
            System.out.printf("%s의 세후 급여는 %.0f 입니다.\n", employee.getName(), employee.calculatePay());
        }

        System.out.printf("총합은 %.0f입니다.\n", totalAmount);
        System.out.println("---");


        employees.sort((e1, e2) ->
                Double.compare(e2.getBaseSalary(), e1.getBaseSalary())
        );

        for (Employee employee : employees) {
            System.out.printf("%s의 세후 급여는 %.0f 입니다.\n", employee.getName(), employee.calculatePay());
        }

    }

}
