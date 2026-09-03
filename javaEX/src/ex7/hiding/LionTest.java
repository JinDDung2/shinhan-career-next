package ex7.hiding;

class Animal {
    int id;

    void move() {
        System.out.println("move animal");
    }
}

class Lion extends Animal {
    String id;
    void print() {
        System.out.println(id);
        System.out.println(super.id); // 부모 id는 은닉되어 있어서 호출가능
    }

    @Override
    void move() {
        System.out.println("move lion");
    }

    void moveAuto() {
        super.move(); // 부모의 move 메서드
        move();
    }
}

public class LionTest {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.id = 10;
        Lion lion = new Lion();
        lion.id = "str";
        lion.print();

        System.out.println("---");

        lion.moveAuto();
    }
}
