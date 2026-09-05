package ex15.generic;

import java.util.ArrayList;

class B {
    int i;

    public B(int i) {
        this.i = i;
    }
}

public class GenericTest {
    public static void main(String[] args) {
        ArrayList<B> list = new ArrayList<>();

        B b1 = new B(1);
        B b2 = new B(2);

        list.add(b1);
        list.add(b2);

        for (int i = 0; i < list.size(); i++) {
            B b = list.get(i); // downCasting 필요 없음
//            B b0 = (B) list.get(i); // 제네릭에 선언 안해두면 명시적으로 downCasting 해야 함

            System.out.println("b.i = " + b.i);
        }
    }
}
