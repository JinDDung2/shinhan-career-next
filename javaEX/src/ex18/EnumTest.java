package ex18;

class OrderStatus1 {
    public static int PAID = 0;
    public static int DELIVERED = 0;
    public static int CANCELED = 0;
}

enum OrderStatus2 {
    PAID,
    DELIVERED,
    CANCELED
}

public class EnumTest {
    public static void main(String[] args) {
        System.out.println(OrderStatus1.PAID);
        System.out.println(OrderStatus2.PAID);

        int orderState1 = OrderStatus1.DELIVERED;
        System.out.println(orderState1); // 숫자라서 오류를 못찾아냄
    }
}
