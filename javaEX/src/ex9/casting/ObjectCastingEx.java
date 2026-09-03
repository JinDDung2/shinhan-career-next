package ex9.casting;

class Car {
    void run() {
        System.out.println("Car 달리는 중");
    }
}

class Sonata extends Car {
    @Override
    void run() {
        System.out.println("Sonata 달리는 중");
    }
}

class Genesis extends Car {
    @Override
    void run() {
        System.out.println("Genesis 달리는 중");
    }

    void autoPark() {
        System.out.println("자동 주차 중");
    }
}

public class ObjectCastingEx {

    static void drive(Car c) {
        // c.autoPark(); // 컴파일러는 객체 확인 못함
        // c의 자료형이 Car에 autoPark() 없기 때문
        c.run();
        if ( c instanceof Genesis ) {
            Genesis g = (Genesis) c;
            g.autoPark();
        }
    }

    public static void main(String[] args) {
        Car sonata = new Sonata();
        Car genesis = new Genesis();

        drive(sonata);
        drive(genesis);
    }
}
