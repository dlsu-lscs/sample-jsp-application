package service;

import model.Customer;
import model.LineItem;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                .from(Order.TABLE_NAME + "," + Customer.TABLE_NAME)
                .where(Order.ID + " = ? ",
                        "AND ",
                        Order.TABLE_NAME + "." + Order.COL_CUSTOMER_ID + " = " + Customer.TABLE_NAME + "." + Customer.ID);
        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);

        statement.setInt(1, Integer.parseInt(id));
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
        statement.setInt(Order.COLUMNS.length+1, Integer.parseInt(id));

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
        PreparedStatement statement = injectItemToStatement(newItem, DBConnection.getConnection().prepareStatement(stringQuery, Statement.RETURN_GENERATED_KEYS));

        int id = statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (!rs.next()) {
            return null;
        }

        id = rs.getInt(1);

        for (LineItem item: newItem.getOrderDetails()) {
            item.setOrderNumber(id);
            lineItemService.create(item);
        }

        newItem.setOrderNumber(id);
        return newItem;
    }

    @Override
    public PreparedStatement injectItemToStatement(Order newItem, PreparedStatement statement) throws SQLException {
        statement.setDate(1, newItem.getOrderDate());
        statement.setDate(2, newItem.getRequiredDate());
        statement.setDate(3, newItem.getShippedDate());
        statement.setString(4, newItem.getStatus());
        statement.setString(5, newItem.getComments());
        statement.setInt(6, newItem.getCustomer().getCustomerNumber());

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
}
