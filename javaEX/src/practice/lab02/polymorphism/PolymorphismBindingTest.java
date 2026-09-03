package practice.lab02.polymorphism;

public class PolymorphismBindingTest {

    static double getArea(Shape shape) {

        return shape.area();
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(3), new Rectangle(4, 5), new Circle(1) };

        for (Shape shape : shapes) {
            System.out.println("shape = " + getArea(shape));
        }
    }
}
