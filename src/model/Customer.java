package model;

public class Customer {
    public static final String TABLE_NAME = "customers";
    public static final String COL_CUSTOMER_ID = "customerNumber";
    public static final String COL_CUSTOMER_NAME = "customerName";
    public static final String COL_CONTACT_LAST_NAME = "contactFirstName";
    public static final String COL_CONTACT_FIRST_NAME = "contactLastName";
    public static final String COL_PHONE = "phone";
    public static final String COL_ADDRESS_LINE_1 = "addressLine1";
    public static final String COL_ADDRESS_LINE_2 = "addressLine2";
    public static final String COL_CITY = "city";
    public static final String COL_STATE = "state";
    public static final String COL_POSTAL_CODE = "postalCode";
    public static final String COL_COUNTRY = "country";
    public static final String COL_CREDIT_LIMIT = "creditLimit";

    public static final String ID = COL_CUSTOMER_ID;
    public static final String [] COLUMNS = {
            Customer.COL_CUSTOMER_NAME,
            Customer.COL_CONTACT_LAST_NAME,
            Customer.COL_CONTACT_FIRST_NAME,
            Customer.COL_PHONE,
            Customer.COL_ADDRESS_LINE_1,
            Customer.COL_ADDRESS_LINE_2,
            Customer.COL_CITY,
            Customer.COL_STATE,
            Customer.COL_POSTAL_CODE,
            Customer.COL_COUNTRY,
            Customer.COL_CREDIT_LIMIT
    };

    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String creditLimit;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }
}
