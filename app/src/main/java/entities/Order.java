package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Order {

    private int id;
    private int userId;
    private String shippingAddress;
    private String orderDate;
    private ArrayList<OrderItem> orderItems;

    public int getId() {

        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public int getUserId() {

        return userId;
    }
    public void setUserId(int userId) {

        this.userId = userId;
    }

    public String getShippingAddress() {

        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {

        this.shippingAddress = shippingAddress;
    }

    public String getOrderDate() {

        return orderDate;
    }
    public void setOrderDate(String orderDate) {

        this.orderDate = orderDate;
    }

    public ArrayList<OrderItem> getOrderItems() {

        return orderItems;
    }
    public void setOrderItems(ArrayList<OrderItem> orderItems) {

        this.orderItems = orderItems;
    }

    public Order()
    {
        this.orderItems = new ArrayList<>();
    };

    public Order (int id, int userId, String shippingAddress, String orderDate)
    {
        this.id = id;
        this.userId = userId;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.orderItems = new ArrayList<>();
    }

}
