package ex14.stringEx;

public class StringTest2 {
    public static void main(String[] args) {    //split 메소드로도 가능
        String str1 = "Hi~ Java World";
        String[] strs = str1.split(" ");

        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }

        String str2 = "aaa:bbb:ccc";
        strs = str2.split(":");

        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }

    }
}
