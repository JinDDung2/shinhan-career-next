package practice.lab02.inheritance;

public class Vehicle {
    protected String brand;
    protected int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void move() {
        System.out.printf("%s 브랜드 차가 시속 %dkm로 달립니다.%n", brand, speed);
    }


    @Override
    public String toString() {
        return String.format("brand: %s speed: %s", brand, speed);
    }
}
