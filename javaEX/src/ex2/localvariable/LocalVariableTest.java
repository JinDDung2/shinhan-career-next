package ex2.localvariable;

public class LocalVariableTest {
    static void m1() {
        int i = 10;      //1)   i 지역변수 생성
    }    //1) i 지역변수 사라짐

    static void m2() {
//        System.out.println(i);  // 컴파일 오류.  i 변수 없음.
    }

    static void m3() {
        int j = 10;      // j 지역변수 생성
        do {
            int i = 10;   // i 지역변수 생성
//            int j = 1;   /* 컴파일 오류. 같은 메소드 내에 같은 이름의 지역변수(매개변수 포함)가 두 개 이상 있을 수 없다. */
        } while (j < 3);     // i 지역변수 사라짐
//        System.out.println(i);    // 컴파일 오류.  i 변수 없음.
        int i = 20;    // 가능
        System.out.println(i); //  가능
    }  //  j 지역변수 사라짐

    static void m4() {
        int result = 10;   // result 지역변수 생성
    } // result 지역변수 사라짐

    public static void main(String[] args) {
        m4();
//        System.out.println(result);    // 컴파일  오류.  result 변수 없음.
        for (int i = 0; i < 3; i++) {   // i 지역변수 생성
            System.out.print(i + " ");
        } // i 지역변수 사라짐
//        System.out.println(i);   // 컴파일  오류.  i 변수 없음.
    }
}

