package practice.lab02.inheritance;

public class InheritanceTest {
    public static void main(String[] args) {
        Vehicle car = new Car("BMW", 200, 4);
        Truck truck = new Truck("hyundai", 100, 1000.0);

        System.out.println("car = " + car);
        System.out.println("truck = " + truck);

        car.move();
        truck.move();
    }
}
