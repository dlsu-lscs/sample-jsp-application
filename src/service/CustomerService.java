package service;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService extends DAO <Customer> {

    public CustomerService(QueryBuilder builder) {
        super(builder);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        builder.reset();
        List <Customer> customers = new ArrayList<>();
        QueryBuilder query = builder
                .select("*")
                .from(Customer.TABLE_NAME)
                .orderBy(Customer.COL_CUSTOMER_ID, true);

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query.getQuery());
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Customer customer = toItem(rs);
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public Customer getOne(String id) throws SQLException {
        builder.reset();
        Customer customer = null;
        QueryBuilder query = builder
                .select("*")
                .from(Customer.TABLE_NAME)
                .where(Customer.ID + " = ?");

        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            customer = toItem(rs);
        }

        return customer;
    }

    @Override
    public boolean update(String id, Customer updated) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .update(Customer.TABLE_NAME)
                .set(Customer.COLUMNS)
                .where(Customer.ID + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setInt(Customer.COLUMNS.length + 1, Integer.parseInt(id));
        statement = injectItemToStatement(updated, statement);

        System.out.println(statement);

        return statement.execute();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .delete()
                .from(Customer.TABLE_NAME)
                .where(Customer.ID + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setInt(1, Integer.parseInt(id));

        return statement.execute();
    }

    @Override
    public Customer create(Customer newItem) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .insert(Customer.TABLE_NAME)
                .columns(Customer.COLUMNS)
                .values(Customer.COLUMNS.length);

        String stringQuery = query.getQuery();
        PreparedStatement statement = injectItemToStatement(newItem, DBConnection.getConnection().prepareStatement(stringQuery));

        if (statement.execute())
            return newItem;

        return null;
    }

    @Override
    public PreparedStatement injectItemToStatement(Customer newItem, PreparedStatement statement) throws SQLException {
        statement.setString(1, newItem.getCustomerName());
        statement.setString(2, newItem.getContactLastName());
        statement.setString(3, newItem.getContactFirstName());
        statement.setString(4, newItem.getPhone());
        statement.setString(5, newItem.getAddressLine1());
        statement.setString(6, newItem.getAddressLine2());
        statement.setString(7, newItem.getCity());
        statement.setString(8, newItem.getState());
        statement.setString(9, newItem.getPostalCode());
        statement.setString(10, newItem.getCountry());
        statement.setString(11, newItem.getCreditLimit());
        return statement;
    }

    @Override
    public Customer toItem(ResultSet rs) throws SQLException {
        Customer customer = new Customer();

        customer.setCustomerNumber(rs.getInt(Customer.COL_CUSTOMER_ID));
        customer.setCustomerName(rs.getString(Customer.COL_CUSTOMER_NAME));
        customer.setContactFirstName(rs.getString(Customer.COL_CONTACT_FIRST_NAME));
        customer.setContactLastName(rs.getString(Customer.COL_CONTACT_LAST_NAME));
        customer.setPhone(rs.getString(Customer.COL_PHONE));
        customer.setAddressLine1(rs.getString(Customer.COL_ADDRESS_LINE_1));
        customer.setAddressLine2(rs.getString(Customer.COL_ADDRESS_LINE_2));
        customer.setCity(rs.getString(Customer.COL_CITY));
        customer.setState(rs.getString(Customer.COL_STATE));
        customer.setPostalCode(rs.getString(Customer.COL_POSTAL_CODE));
        customer.setCountry(rs.getString(Customer.COL_COUNTRY));
        customer.setCreditLimit(rs.getString(Customer.COL_CREDIT_LIMIT));

        return customer;
    }
}
