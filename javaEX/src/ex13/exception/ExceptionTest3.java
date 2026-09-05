package ex13.exception;

public class ExceptionTest3 {
    public static void main(String[] args) {
        try {
            int i = 0;
            int result = 10 / i;
            System.out.println("정상");
        } catch (Exception e) {
            System.out.println("예외 발생: " + e);
        }

        System.out.println("종료");
    }
}
