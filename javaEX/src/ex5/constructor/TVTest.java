package ex5.constructor;

public class TVTest {
    public static void main(String[] args) {
        TV tv = new TV();
    }
}

class TV {
    TV() {
//        System.out.println("hello"); // 이렇게 되면 this()가 두 번째 줄이라 오류
        this(10); // 첫번째 문장에만 가능
//        this(20); // 두번째 문장이라 오류
    }

    TV(int c) {
        System.out.println(c);
    }
}
