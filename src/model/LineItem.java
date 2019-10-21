package model;

import javax.sound.sampled.Line;

public class LineItem {
    public static final String TABLE_NAME = "orderdetails";
    public static final String COL_ORDER_NUMBER = "orderNumber";
    public static final String COL_PRODUCT_CODE = "productCode";
    public static final String COL_QUANTITY_ORDERED = "quantityOrdered";
    public static final String COL_PRICE_EACH = "priceEach";
    public static final String COL_ORDER_LINE_NUMBER = "orderLineNumber";

    public static final String ID = COL_PRODUCT_CODE;
    public static final String [] COLUMNS = {
            LineItem.COL_ORDER_NUMBER,
            LineItem.COL_PRODUCT_CODE,
            LineItem.COL_QUANTITY_ORDERED,
            LineItem.COL_PRICE_EACH,
            LineItem.COL_ORDER_LINE_NUMBER
    };

    private Product product;

    private int orderNumber;
    private int orderLineNumber;
    private int quantityOrdered;
    private double priceEach;
    private double total;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderLineNumber() {
        return orderLineNumber;
    }

    public void setOrderLineNumber(int orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
        this.total = 0;
        this.total += priceEach * quantityOrdered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
        this.total = 0;
        this.total += priceEach * quantityOrdered;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
