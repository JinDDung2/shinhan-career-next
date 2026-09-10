package practice.lab05;

public class SumThread extends Thread{

    private long total;

    @Override
    public void run() {
        for (int i = 1; i <= 1000000; i++) {
            total += i;
        }
    }

    public long getTotal() {
        return total;
    }

    public static void main(String[] args) throws InterruptedException {
        SumThread thread = new SumThread();

        thread.start(); // 스레드 시작

        // join()이 없으면 계산이 끝나기 전에 출력될 수 있음
        // why? join()은 다른 스레드가 끝날 때까지 현재 스레드를 기다리게 하는 메서드
        System.out.println("join 전 합계: " + thread.getTotal());
        thread.join();

        System.out.println("join 후 합계: " + thread.getTotal());
    }
}
