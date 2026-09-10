package practice.lab05;

public class Counter extends Thread {
    private int i = 0;
    private int j = 0;

    public void increment() {
        this.i++;
    }

    public synchronized void incrementWithSync() {
        this.j++;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println("no sync 최종값: " + counter.i);
        System.out.println("---");

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementWithSync();
            }
        });

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementWithSync();
            }
        });

        t3.start();
        t4.start();

        t4.join();
        t3.join();
        System.out.println("sync 최종값: " + counter.getJ());
    }
}
