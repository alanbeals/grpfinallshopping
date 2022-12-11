package entities;

public class Product {

//    private int id;
    private String name;
    private double price;

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

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
