package ex13.exception;

public class ExceptionTest5 {
    public static void main(String[] args) {
        String str = null;

        try {
            str.substring(1);
        } catch (ArithmeticException e) {
            System.out.println("catch"); // 예외 못잡음. 실행 안됨
        } finally { // 예외 발생 상관없이 반드시 수행
            System.out.println("finally");
        }
        System.out.println("종료");
    }
}
