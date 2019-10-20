package service;

import model.Customer;
import model.LineItem;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService extends DAO <Order> {
    CustomerService customerService;
    LineItemService lineItemService;

    public OrderService(QueryBuilder builder, CustomerService customerService, LineItemService lineItemService) {
        super(builder);
        this.customerService = customerService;
        this.lineItemService = lineItemService;
    }

    @Override
    public List<Order> getAll() throws SQLException {

        builder.reset();
        List <Order> orders = new ArrayList<>();
        QueryBuilder query = builder
                .select("*")
                .from(Order.TABLE_NAME)
                .join(Order.TABLE_NAME, Order.COL_CUSTOMER_ID, Customer.TABLE_NAME, Customer.COL_CUSTOMER_ID)
                .orderBy(Order.COL_ORDER_NUMBER, true);

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query.getQuery());
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Order order = toItem(rs);
            orders.add(order);
        }

        return orders;
    }

    @Override
    public Order getOne(String id) throws SQLException {
        builder.reset();
        Order order = null;
        QueryBuilder query = builder
                .select("*")
                .from(Order.TABLE_NAME + "," + Customer.ID)
                .where(Order.ID + " = ?",
                        Order.COL_CUSTOMER_ID + " = " + Customer.ID);

        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            order = toItem(rs);
        }

        return order;

    }

    @Override
    public boolean update(String id, Order updated) throws SQLException {

        builder.reset();

        QueryBuilder query = builder
                .update(Order.TABLE_NAME)
                .set(Order.COLUMNS)
                .where(Order.ID + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement = injectItemToStatement(updated, statement);
        statement.setInt(1, Integer.parseInt(id));

        return statement.execute();
    }

    @Override
    public boolean delete(String id) throws SQLException {

        builder.reset();

        QueryBuilder query = builder
                .delete()
                .from(Order.TABLE_NAME)
                .where(Order.ID + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setInt(1, Integer.parseInt(id));

        return statement.execute();
    }

    @Override
    public Order create(Order newItem) throws SQLException {

        builder.reset();

        QueryBuilder query = builder
                .insert(Order.TABLE_NAME)
                .columns(Order.COLUMNS)
                .values(Order.COLUMNS.length);

        String stringQuery = query.getQuery();
        PreparedStatement statement = injectItemToStatement(newItem, DBConnection.getConnection().prepareStatement(stringQuery));

        if (statement.execute())
            return newItem;

        return null;
    }

    @Override
    public PreparedStatement injectItemToStatement(Order newItem, PreparedStatement statement) throws SQLException {
        statement.setInt(1, newItem.getOrderNumber());
        statement.setDate(2, newItem.getOrderDate());
        statement.setDate(3, newItem.getRequiredDate());
        statement.setDate(4, newItem.getShippedDate());
        statement.setString(5, newItem.getStatus());
        statement.setString(6, newItem.getComments());
        statement.setInt(7, newItem.getCustomer().getCustomerNumber());

        return statement;
    }

    @Override
    public Order toItem(ResultSet rs) throws SQLException {

        Order order = new Order();

        Customer customer = customerService.toItem(rs);
        order.setCustomer(customer);

        order.setOrderNumber(rs.getInt(Order.COL_ORDER_NUMBER));

        List <LineItem> lineItems = lineItemService.getAllOfOrder(String.valueOf(order.getOrderNumber()));
        order.setOrderDetails(lineItems);

        order.setOrderDate(rs.getDate(Order.COL_ORDER_DATE));
        order.setRequiredDate(rs.getDate(Order.COL_REQUIRED_DATE));
        order.setShippedDate(rs.getDate(Order.COL_SHIPPED_DATE));
        order.setStatus(rs.getString(Order.COL_STATUS));
        order.setComments(rs.getString(Order.COL_COMMENTS));

        return order;
    }

    public boolean deleteOrdersWithUsers (String id) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .delete()
                .from(Order.TABLE_NAME)
                .where(Order.COL_CUSTOMER_ID + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setInt(1, Integer.parseInt(id));

        System.out.println(statement);
        return statement.execute();
    }

}
