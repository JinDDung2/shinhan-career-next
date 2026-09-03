package practice.lab01;

public class RobotTest {
    /**
     * 1 베터리 객체를 생성하고 에너지 충전메소드 호출하여 200으로 충전.
     * 2 로봇 객체를 생성하고, 1에서 생성한 베터리 객체를  로봇의 베터리객체로 할당.
     * 3. 로봇 객체의 move 메소드 및 run메소드를 호출하고, 베터리 에너지의 남은 량을 출력한다.
     */

    public static void main(String[] args) {
        Battery battery = new Battery();
        battery.charge(200);

        Robot robot = new Robot("optimus", battery);
        robot.move(Robot.moveEnergy);
        robot.run(Robot.runEnergy);
        System.out.println("energy = " + robot.battery.energy);

    }
}
