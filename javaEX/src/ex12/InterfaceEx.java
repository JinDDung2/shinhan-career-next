package ex12;

interface Drawable {
    int PLAIN_PEN = 1;
    int BOLD_PEN = 2;

    void draw(); // 기본적으로 public abstract
    void move(int x, int y);
}

class A1 implements Drawable{

    @Override
    public void draw() {

    }

    @Override
    public void move(int x, int y) {

    }
}

public class InterfaceEx {
}
