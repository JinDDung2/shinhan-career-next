package ex6;

import java.util.Objects;

public class StringTest {
    public static void main(String[] args) {
        String s1 = " Test Test ";
        System.out.println(s1.length());
        System.out.println("앞뒤 공백 제거:" + s1.trim()); // 기존 값이 바뀌는 것은 아님
        System.out.println("소문자롭 변경:" + s1.toLowerCase()); // 기존 값이 바뀌는 것은 아님

        s1 = s1.trim(); // 앞뒤 공백 제거 후 덮어쓰기
        System.out.println("s1=" + s1);
        s1 = s1.toLowerCase(); // 소문자 변경 후 덮어쓰기
        System.out.println("s1=" + s1);

        s1 = s1.toUpperCase();
        System.out.println("대문자로 변경" + s1);            // TEST TEST

        String s4 = new String("amit");
        System.out.println("s4의 문자열 대체 : " + s4.replace('m', 'r'));  //arit
        System.out.println("문자열 대체 이후의 s4 : " + s4);             //amit

        String str1 = "abc";
        String str2 = "AbC";

        //대소문자 구별. 문자열 내용 같으면 true
        System.out.println(str1.equals(str2));                    //false
        //대소문자 구별하지 않고 문자열 내용 같으면 true
        System.out.println(str1.equalsIgnoreCase(str2));          //true
        //같으면 0, 다르면 음수 또는 양수
        System.out.println(str1.compareTo(str2));                //32

        String str = "Hello Java";
        // He로 시작하면 true
        System.out.println(str.startsWith("He"));                 //true
        // va로 끝나면 true
        System.out.println(str.endsWith("va"));                  //true
        // 0번 인덱스 문자 하나를 char형으로 반환(자바문자는 0인덱스부터 시작 )
        char ch1 = str.charAt(0);      //H
        // 1번 인덱스 문자 하나를 char형으로 반환
        char ch2 = str.charAt(1);       //e
        System.out.println(ch1 + "" + ch2);    //He

        //str 문자열에서 "el" 문자열 찾아서 그 인덱스를 반환. 못 찾으면 -1반환
        System.out.println(str.indexOf("el"));     //1
        //str 문자열에서 "l" 문자열 찾아서(뒤에서부터) 그 인덱스를 반환. 못 찾으면 -1반환
        System.out.println(str.lastIndexOf("l"));  //3
        //인덱스 1부터 3바로 앞까지 복사.
        String str3 = str.substring(1, 3); //el
//인덱스 1부터 끝까지 복사.
        String str4 = str.substring(1);   //ello Java
        System.out.println(str3);       // el
        System.out.println(str4);       // ello Java
        //문자열 이어붙이기
        System.out.println(str.concat(str3)); //Hello Javael
        System.out.println(str + str3);// Hello Javael

        String a = "aaa"; // String pool에 생성
        String b = new String("aaa"); // Heap 메모리에 생성
        String c = "aaa"; // String pool에 생성

        System.out.println(a == b); // false: 메모리 주소가 다름
        System.out.println(a == c); // true: 메모리 주소가 같음
        System.out.println(a.equals(b)); // true: 문자열이 서로 같음

        String d = null;
//        System.out.println(d.equals(a)); // NPE 발생. 비교 주체가 null이니 -> Objects.equals()
        System.out.println(Objects.equals(d, a)); // null-safe

    }
}
