package practice.lab04;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(i * 10);
        }

        System.out.println(list);
        // 30 -> 33으로
        list.set(3, 33);
        System.out.println(list);

        // 40 지우기
        list.remove(Integer.valueOf(40));
        System.out.println(list);

        int total = 0;
        for (Integer num : list) {
            total += num;
        }
        double avg = total / 5;
        System.out.println("total = " + total);
        System.out.printf("avg = %.1f", avg);
    }
}
