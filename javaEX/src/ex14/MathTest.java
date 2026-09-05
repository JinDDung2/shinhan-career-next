package ex14;

public class MathTest {
    public static void main(String[] args) {
        // 1, 2, 3 중 하나를 랜덤으로 출력하는 코드
        // 1 -> 가위
        // 2 -> 바위
        // 3 -> 보
        int random = (int) ((Math.random() * 3 )+ 1);
        String result = switch (random) {
            case 1 -> "가위";
            case 2 -> "바위";
            case 3 -> "보";
            default -> throw new IllegalStateException("Unexpected value: " + random);
        };

        System.out.println("result = " + result);
    }
}
