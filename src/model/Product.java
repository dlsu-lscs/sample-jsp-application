package model;

public class Product {
    public static final String TABLE_NAME = "products";
    public static final String COL_PRODUCT_CODE = "productCode";
    public static final String COL_PRODUCT_NAME = "productName";
    public static final String COL_PRODUCT_LINE = "productLine";
    public static final String COL_PRODUCT_SCALE = "productScale";
    public static final String COL_PRODUCT_VENDOR = "productVendor";
    public static final String COL_PRODUCT_DESCRIPTION = "productDescription";
    public static final String COL_QUANTITY_STOCK = "quantityInStock";
    public static final String COL_BUY_PRICE = "buyPrice";
    public static final String COL_MSRP = "MSRP";

    public static final String ID = COL_PRODUCT_CODE;
    public static final String [] COLUMNS = {
            Product.COL_PRODUCT_CODE,
            Product.COL_PRODUCT_DESCRIPTION,
            Product.COL_PRODUCT_LINE,
            Product.COL_PRODUCT_NAME,
            Product.COL_PRODUCT_SCALE,
            Product.COL_PRODUCT_VENDOR,
            Product.COL_QUANTITY_STOCK,
            Product.COL_BUY_PRICE,
            Product.COL_MSRP
    };

    private String code;
    private String name;
    private String line;
    private String scale;
    private String vendor;
    private String description;
    private int quantity;
    private double buyPrice;
    private double MSRP;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getMSRP() {
        return MSRP;
    }

    public void setMSRP(double MSRP) {
        this.MSRP = MSRP;
    }
}
