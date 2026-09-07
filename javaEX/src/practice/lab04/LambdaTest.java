package practice.lab04;

import java.util.ArrayList;
import java.util.Collections;

public class LambdaTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        System.out.println("list = " + list);
        System.out.println("---");

        list.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .forEach(System.out::println);
        System.out.println("---");

        Collections.sort(list, (x , y) -> Integer.compare(y, x));
        System.out.println("list = " + list);
    }
}
