package ex9.casting;

public class CastingEx1 {
    public static void main(String[] args) {
        byte b = 10;
        short s = b;  // 묵시적 형변환
        int i = s;       // 묵시적 형변환
        long l = i;     // 묵시적 형변환

        b = (byte) s;   // 명시적 형변환     (  ) 캐스팅 연산자라고 함.
        i = (int) l;       // 명시적 형변환

        double d2 = 0.3; // 실수는 기본적으로 double형으로 생성됨
        float f = 0.3f;     // 또는 0.3F (숫자 뒤에 f를 붙이면 float 형으로 생성)

        double d1 = f;   //묵시적 형변환
        f = (float) d1;   //명시적 형변환

    }
}
