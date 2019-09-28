package model;

import java.sql.Date;
import java.util.List;

public class Order {
    public static final String TABLE_NAME = "orders";
    public static final String COL_ORDER_NUMBER = "orderNumber";
    public static final String COL_ORDER_DATE = "orderDate";
    public static final String COL_REQUIRED_DATE = "requiredDate";
    public static final String COL_SHIPPED_DATE = "shippedDate";
    public static final String COL_STATUS = "status";
    public static final String COL_COMMENTS = "comments";
    public static final String COL_CUSTOMER_ID = "customerNumber";

    public static final String ID = COL_ORDER_NUMBER;
    public static final String [] COLUMNS = {
            Order.COL_ORDER_DATE,
            Order.COL_REQUIRED_DATE,
            Order.COL_SHIPPED_DATE,
            Order.COL_STATUS,
            Order.COL_COMMENTS,
            Order.COL_CUSTOMER_ID
    };

    private int orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;

    private Customer customer;
    private List <LineItem> orderDetails;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<LineItem> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
