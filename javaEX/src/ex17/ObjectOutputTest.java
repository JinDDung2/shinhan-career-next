package ex17;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputTest {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("object.data"));
        oo.writeObject("Today"); // String 직렬화해서 파일에 저장
        oo.writeObject(new java.util.Date());
        oo.writeDouble(Math.PI);
        oo.writeObject(new A(10));

        oo.flush();
        oo.close();

    }

}

class A implements Serializable {
    int a;

    public A(int a) {
        this.a = a;
    }

}
