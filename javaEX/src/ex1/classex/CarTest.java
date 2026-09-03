package ex1.classex;

/*
    Car 클래스
    instance var: ownerName, speed
    static var: cnt
 */

public class CarTest {
    public static void main(String[] args) {
        Car Tom = new Car("Tom", 100);
        Car Henry = new Car("Henry", 200);

        System.out.println( Tom.ownerName + "의 자동차 속도는 " + Tom.speed + "km");
        System.out.println( Henry.ownerName + "의 자동차 속도는 " + Henry.speed  + "km");
        System.out.println("Car.cnt = " + Car.cnt);
    }
}

class Car {
    String ownerName;
    int speed;
    static int cnt = 0;

    public Car(String ownerName, int speed) {
        this.ownerName = ownerName;
        this.speed = speed;
        cnt++;
    }
}
