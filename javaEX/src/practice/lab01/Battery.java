package practice.lab01;

public class Battery {
    static int height;
    static int width;
    int energy;

    public int charge(int energy) {
        return this.energy += energy;
    }

    public int use(int energy) {
        return this.energy -= energy;
    }

    static void printBattery() {
        System.out.println("height = " + height);
        System.out.println("height = " + width);
    }
}
