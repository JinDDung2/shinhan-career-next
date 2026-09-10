package practice.lab05;

public class CountThread extends Thread {
    private int count;

    public CountThread(String name, int count) {
        super(name);
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        CountThread t1 = new CountThread("A", 3);
        CountThread t2 = new CountThread("B", 3);

        t1.start();
        t2.start();

    }
}
