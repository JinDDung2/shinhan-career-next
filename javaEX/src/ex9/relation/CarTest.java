package ex9.relation;

class Car {
    void run() {
        System.out.println("Car가 달린다");
    }
}

// Sonata is a Car
class Sonata extends Car {
    void run() {
        System.out.println("Sonata가 달린다");
    }
}

// Genesis is a Car
class Genesis extends Car {
    void run() {
        System.out.println("Genesis 가 달린다");
    }
}
class Person{      // Person   has-a    Car    :  Has-a 관계
    Car car ;

    public Person(Car car) {
        this.car = car;
    }

    void drive(){
        car.run();
    }
}


public class CarTest {
    public static void main(String[] args) {

        Person p1 = new Person(new Sonata());
        Person p2 = new Person(new Genesis());

        p1.drive();
        p2.drive();

        p1.car.run();
        p2.car.run();
    }
}
