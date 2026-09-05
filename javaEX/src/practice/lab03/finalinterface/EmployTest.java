package practice.lab03.finalinterface;

public class EmployTest {
    public static void main(String[] args) {
        Payable[] payables = new Employee[3];
        payables[0] = new Employee(3_000_000);
        payables[1] = new Employee(2_000_000);
        payables[2] = new Employee(4_000_000);

        for (Payable payable : payables) {
            System.out.println(payable.calculatePay());
        }
    }
}
