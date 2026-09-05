package ex9.init;

class Emp {
    int empNo;

    public Emp(int empNo) {
        this.empNo = empNo;
        System.out.println("Emp(int EmpNo): " + empNo);
    }

    public Emp() {
        System.out.println("Emp()");
        empNo = 99;
    }
}

public class ObjectInit {
    public static void main(String[] args) {
        Emp fir = new Emp();
        System.out.println(fir.empNo);
        Emp sec = new Emp(10);
        System.out.println(sec.empNo);
    }
}
