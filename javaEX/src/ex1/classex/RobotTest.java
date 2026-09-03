package ex1.classex;

import java.awt.*;

public class RobotTest {

    public static void main(String[] args) {
        Robot r1 = new Robot();
        r1.name = "robot 1";
        r1.energy = 100;
        Robot.cnt = 1;

        Robot r2 = new Robot();
        r2.name = "robot 2";
        r2.energy = 200;
        Robot.cnt = 2;

        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);
        System.out.println("Robot.cnt = " + Robot.cnt);

        r1.print();
        r2.print();
    }
}

class Robot {
    String name; // instance (맴버)변수
    int energy; // instance (맴버)변수
    static int cnt; // stsatic (맴버)변수

    void print() {
        String name = "지역변수";
        System.out.println("name = " + name); // 지역변수 먼저 탐색 -> 없으면 맴버변수
    }


    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                ", energy=" + energy +
                '}';
    }
}
