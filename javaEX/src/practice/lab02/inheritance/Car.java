package practice.lab02.inheritance;

public class Car extends Vehicle {

    private int doorCount;

    public Car(String brand, int speed, int doorCount) {
        super(brand, speed);
        this.doorCount = doorCount;
    }

    @Override
    public String toString() {
        return String.format("%s doorCount: %d", super.toString(), doorCount);
    }
}
