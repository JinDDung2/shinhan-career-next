package practice.lab04;

import java.util.HashMap;
import java.util.Map;

class Product {
    private int productId;
    private String productName;
    private int price;

    public Product(int productId, String productName, int price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}

public class ProductService {

    Map<Integer, Product> map = new HashMap<>();

    public void addProduct(Product product) {
        map.put(product.getProductId(), product);
    }

    public Product getProductById(int productId) {
        return map.get(productId);
    }

    public void removeProductById(int productId) {
        map.remove(productId);
    }

    public void printAll() {
        System.out.println(map);
    }

    public static void main(String[] args) {
        Product p1 = new Product(100, "아메키라노", 3800);
        Product p2 = new Product(102, "라떼", 4100);
        Product p3 = new Product(104, "에이드", 4500);
        Product p4 = new Product(106, "스무디", 4600);
        Product p5 = new Product(108, "귀찮", 1000);

        ProductService productService = new ProductService();
        productService.addProduct(p1);
        productService.printAll();

        productService.getProductById(p1.getProductId());
        productService.removeProductById(p1.getProductId());
        productService.printAll();

        productService.addProduct(p2);
        productService.addProduct(p3);
        productService.addProduct(p4);
        productService.addProduct(p5);
        productService.printAll();
    }
}
