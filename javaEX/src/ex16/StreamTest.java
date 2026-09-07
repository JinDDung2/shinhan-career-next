package ex16;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        List<String> names =
                Arrays.asList(
                        "홍길동",
                        "김철수",
                        "이영희",
                        "박민수",
                        "김영희",
                        "금희"
                );

        names.stream()
                .map(String::length)
                .forEach(System.out::println);

        System.out.println("---");

        long count = names.stream()
                .map(name -> name.startsWith("김"))
                .count();

        System.out.println("count = " + count);

        System.out.println("---");
        // 이름이 "희"로 끝나는 사람의 이름 글자가 3이상이면 "이름 글자 수" 출력
        names.stream()
                .filter(name -> name.endsWith("희"))
                .map(String::length)
                .filter(cnt -> cnt >= 3)
                .forEach(System.out::println);
    }
}
