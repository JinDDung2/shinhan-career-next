package ex9.casting;


class P {
    void a() {
        System.out.println("P");
    }
    void b() {
        a();
    }
}

class C extends P {
    @Override
    void a() {
        System.out.println("C");
    }
}

public class CastingEx2 {
    public static void main(String[] args) {
        C c = new C();
        c.b(); // 동적 바인딩
    }
}
