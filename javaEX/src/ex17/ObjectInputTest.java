package ex17;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class ObjectInputTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(
                new FileInputStream("object.data")
        );

        String s = (String) (oi.readObject());
        Date o = (Date) (oi.readObject());
        double d = oi.readDouble();
        A a = (A) oi.readObject();
    }
}
