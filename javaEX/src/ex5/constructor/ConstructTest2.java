package ex5.constructor;

public class ConstructTest2 {
    public static void main(String[] args) {
        Emp me = new Emp("Kim");
        System.out.println(me.name);
        System.out.println(Emp.cnt);
    }
}

class Emp {
    String name;
    static int cnt;

    public Emp(String name) {
        this.name = name;
        cnt++;
    }
}
