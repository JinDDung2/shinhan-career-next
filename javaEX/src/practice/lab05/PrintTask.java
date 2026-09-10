package practice.lab05;

public class PrintTask implements Runnable{

    private String message;
    private int count;

    public PrintTask(String message, int count) {
        this.message = message;
        this.count = count;
    }

    @Override
    public void run() {
        while (count-- > 0) {
            System.out.println(message);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        PrintTask task1 = new PrintTask("티워어어어언", 5);
        PrintTask task2 = new PrintTask("제엔쥐이이이", 5);

        new Thread(task1).start();
        new Thread(task2).start();

    }
}
