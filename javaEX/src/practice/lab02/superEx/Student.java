package practice.lab02.superEx;

public class Student extends Person{

    private int studentNo;

    public Student(String name, int studentNo) {
        super(name);
        this.studentNo = studentNo;
    }

    @Override
    public String toString() {
        return String.format("%s, 학번: %d", super.toString(), studentNo);
    }
}
