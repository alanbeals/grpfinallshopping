package entities;

public class OrderItem {

    private int id;
    private int orderId;
    private int productId;
    private double pricePerUnit;
    private int quantity;
    private Product product;

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public int getOrderId() {

        return orderId;
    }
    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public int getProductId() {

        return productId;
    }
    public void setProductId(int productId) {

        this.productId = productId;
    }

    public double getPricePerUnit() {

        return pricePerUnit;
    }
    public void setPricePerUnit(double pricePerUnit) {

        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {

        return quantity;
    }
    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public Product getProduct() {

        return product;
    }
    public void setProduct(Product product) {

        this.product = product;
    }

    public OrderItem()
    {
        this.product = new Product();
    };

    public OrderItem(int id, int orderId, int productId, double pricePerUnit, int quantity, Product product)
    {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.product = product;
    }
}
