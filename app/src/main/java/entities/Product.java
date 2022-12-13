package entities;

public class Product {

    private int id;
    private String name;
    private double price;

    public Product() {

    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }
    public double getProductPrice() {
        return price;
    }

    public void setProductPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Product(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
