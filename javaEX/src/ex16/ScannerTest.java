package ex16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        /**
         * nextInt() or nextDouble()는 줄바꿈문자를 버퍼에 남겨두고 안읽어옴
         */
        Scanner scan = new Scanner(System.in);      //InputStream을 생성자의 매개인자로 받음
        System.out.println(">정수를 입력하세요");
        int data1 = scan.nextInt();  		         //한단어 읽고 자동 int 변환
        System.out.println(">실수를 입력하세요");
        double data2 = scan.nextDouble();  	      //한단어 읽고 자동 double 변환
        System.out.println(">문자열을 입력하세요");
        scan.nextLine();   			      //기존에 남아 있는 개행 문자를 없애야 함.
        String data3= scan.nextLine();  		      //한 줄을 읽음(개행 문자까지 읽음)
        System.out.println(">입력한 정보는 다음과 같습니다.");
        System.out.println(data1+" " + data2+" " +data3);

        InputStreamReader r = new InputStreamReader(System.in);
        // System.in 은 InputStream( byte 단위 전송. 즉, 유니코드 무자로 바꾸지 않음)
        // Reader 는 unicode 로 바꿔주면서 읽음
        BufferedReader br = new BufferedReader(r); // 한 줄씩 읽는 메서드
    }

}
