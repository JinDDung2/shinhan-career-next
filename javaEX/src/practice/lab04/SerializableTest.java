package practice.lab04;

import java.io.*;

class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class SerializableTest {
    public static void main(String[] args)  {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("person.dat"))) {

            oos.writeObject(new Person("kim", 20));
            oos.writeObject(new Person("na", 25));
            oos.writeObject(new Person("park", 30));
            oos.writeObject(new Person("lee", 35));

        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("person.dat"))) {

            Person kim = (Person) ois.readObject();
            Person na = (Person) ois.readObject();
            Person park = (Person) ois.readObject();
            Person lee = (Person) ois.readObject();

            System.out.println(kim);
            System.out.println(na);
            System.out.println(park);
            System.out.println(lee);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }
}
