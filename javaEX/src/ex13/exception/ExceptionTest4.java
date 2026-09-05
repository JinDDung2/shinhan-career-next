package ex13.exception;

public class ExceptionTest4 {
    public static void main(String args[]) {

        try {
            int i = Integer.parseInt("aaa");
            //abc를 숫자로 바꾸지 못해 NumberFormatException발생
            int r = 100 / 0;
            //숫자를 0으로 바꾸지 못해 ArithmeticException발생
        } catch (ArithmeticException e) {
            System.out.println("예외발생1");
        } catch (NumberFormatException e) {
            System.out.println("예외발생2");
        } catch (Exception e) { //상위 타입 Exception을 뒤쪽에 작성
            System.out.println("예외발생3");
        }
        System.out.println("종료");
    }
}

