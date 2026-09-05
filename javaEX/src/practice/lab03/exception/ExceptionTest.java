package practice.lab03.exception;

public class ExceptionTest {
    public static void main(String[] args) {
        Account account = new Account(1_000);

        try {
            account.withdrawal(10_000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("거래 종료");
        }
    }
}
