package practice.lab04;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIOTest {
    public static void main(String[] args) {
        FileWriter fw = null;

        // 파일 쓰기
        try {
            fw = new FileWriter("students.txt");

            fw.write("hong,90\n");
            fw.write("kim,85\n");
            fw.write("lee,95\n");
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("파일 닫기 오류: " + e.getMessage());
                }
            }
        }

        // 파일 읽기

        try (Scanner sc = new Scanner(new FileReader("students.txt"))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // ","로 구분 -> [이름, 점수]
                String[] data = line.split(",");

                String name = data[0];
                String score = data[1];

                System.out.printf("이름:%s 점수:%s\n", name, score);
            }

        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
        }

    }
}
