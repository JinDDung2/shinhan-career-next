package ex8.polymorphism;

class Car {
    void run() {
        System.out.println("move Car");
    }
}

class Sonata extends Car {
    @Override
    void run() {
        System.out.println("move Sonata");
    }
}

class Genesis extends Car {
    @Override
    void run() {
        System.out.println("move Genesis");
    }
}

public class Test2 {
    static void drive(Car c) {
        c.run();
    }

    public static void main(String[] args) {
        drive( new Sonata() );
        drive( new Genesis() );
    }
}
