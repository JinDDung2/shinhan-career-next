package practice.lab01;

public class Robot {
    String name;
    Battery battery;

    static int moveEnergy = 2;
    static int runEnergy = 5;

    public Robot(String name, Battery battery) {
        this.name = name;
        this.battery = battery;
    }

    public void move(int energy) {
        battery.energy -= energy;
    }

    public void run(int energy) {
        battery.energy -= energy;
    }
}
