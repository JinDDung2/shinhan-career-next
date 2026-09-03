package practice.lab02.inheritance;

public class Truck extends Vehicle {

    private double loadWeight;

    public Truck(String brand, int speed, double loadWeight) {
        super(brand, speed);
        this.loadWeight = loadWeight;
    }

    @Override
    public String toString() {
        return String.format("%s loadWeight: %f", super.toString(), loadWeight);
    }
}
