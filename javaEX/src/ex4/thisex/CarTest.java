package ex4.thisex;

public class CarTest {
    public static void main(String[] args) {
        Car myCar = new Car();
        Car yourCar = new Car();
        myCar.setSpeed(10);
        myCar.run();
        yourCar.setSpeed(20);
        yourCar.run();
    }
}

class Car {
    String id;
    int speed;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void run() {
        System.out.println(this.speed + "km 속도로 달리는 중");
    }
}
