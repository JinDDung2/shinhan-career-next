package practice.lab03.finalinterface;

public class Employee implements Payable {

    private String name;
    private int baseSalary;

    public Employee(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Employee(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculatePay() {
        return baseSalary * (1 - TAX_RATE);
    }

    final void printCompany() {
        System.out.println("print company");
    }

    public String getName() {
        return name;
    }

    public int getBaseSalary() {
        return baseSalary;
    }
}
