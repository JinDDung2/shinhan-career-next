package ex8.polymorphism;

class Shape {
    void draw() {
        System.out.println(" 도형 그림 ");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println(" 원그림 ");
    }
}

class Rectangle extends Shape {
    void draw() {
        System.out.println(" 사각형 그림 ");
    }
}


public class Test {
    public static void test(Shape s) {
        s.draw();
    }

    public static void main(String[] args) {
        Shape s = new Shape();
        Shape c = new Circle();
        Shape r = new Rectangle();
        test(s);    //  test(new Shape());     	//과 실행결과 같음
        test(c);    //  test(new Circle());     	//과 실행결과 같음
        test(r);    //  test(new Rectangle()); 	//과 실행결과 같음
    }

}
