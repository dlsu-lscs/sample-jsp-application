package service;

import model.LineItem;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemService extends DAO<LineItem> {

    private final ProductService productService;

    public LineItemService(QueryBuilder builder, ProductService service) {
        super(builder);
        this.productService = service;
    }

    @Override
    public List<LineItem> getAll() throws SQLException {
        builder.reset();
        List <LineItem> items = new ArrayList<>();
        QueryBuilder query = builder
                .select("*")
                .from(LineItem.TABLE_NAME);

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query.getQuery());
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            LineItem item = toItem(rs);
            items.add(item);
        }

        return items;
    }

    @Override
    public LineItem getOne(String id) throws SQLException {
        builder.reset();

        String [] ids = id.split(",");

        int lineItemID = Integer.parseInt(ids[0]);
        int orderNumber = Integer.parseInt(ids[1]);

        LineItem item = null;
        QueryBuilder query = builder
                .select("*")
                .from(LineItem.TABLE_NAME)
                .where(LineItem.ID + " = ? ", "AND ",
                        LineItem.COL_ORDER_NUMBER + " = ?");

        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);

        statement.setInt(1, lineItemID);
        statement.setInt(2, orderNumber);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            item = toItem(rs);
        }

        return item;
    }

    public List <LineItem> getAllOfOrder (String orderNumber) throws SQLException {
        builder.reset();
        List <LineItem> items = new ArrayList<>();
        QueryBuilder query = builder
                .select("*")
                .from(LineItem.TABLE_NAME)
                .where(LineItem.COL_ORDER_NUMBER + "= ?");

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query.getQuery());
        statement.setInt(1, Integer.parseInt(orderNumber));
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            LineItem item = toItem(rs);
            items.add(item);
        }

        return items;
    }

    @Override
    public boolean update(String id, LineItem updated) throws SQLException {
        builder.reset();

        String [] ids = id.split(",");

        int lineItemID = Integer.parseInt(ids[0]);
        int orderNumber = Integer.parseInt(ids[1]);

        QueryBuilder query = builder
                .update(LineItem.TABLE_NAME)
                .set(LineItem.COLUMNS)
                .where(LineItem.ID + " = ? ", "AND ",
                        LineItem.COL_ORDER_NUMBER + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement = injectItemToStatement(updated, statement);

        statement.setInt(LineItem.COLUMNS.length + 1, lineItemID);
        statement.setInt(LineItem.COLUMNS.length + 2, orderNumber);

        return statement.execute();
    }

    @Override
    public boolean delete(String id) throws SQLException {

        builder.reset();

        String [] ids = id.split(",");

        int lineItemID = Integer.parseInt(ids[0]);
        int orderNumber = Integer.parseInt(ids[1]);

        QueryBuilder query = builder
                .delete()
                .from(LineItem.TABLE_NAME)
                .where(LineItem.ID + " = ? ", "AND ",
                        LineItem.COL_ORDER_NUMBER + " = ?");

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setInt(1, lineItemID);
        statement.setInt(2, orderNumber);

        return statement.execute();
    }

    @Override
    public LineItem create(LineItem newItem) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .insert(LineItem.TABLE_NAME)
                .columns(LineItem.COLUMNS)
                .values(LineItem.COLUMNS.length);

        String stringQuery = query.getQuery();
        PreparedStatement statement = injectItemToStatement(newItem, DBConnection.getConnection().prepareStatement(stringQuery));

        if (statement.execute())
            return newItem;

        return null;
    }

    @Override
    public PreparedStatement injectItemToStatement(LineItem newItem, PreparedStatement statement) throws SQLException {
        statement.setInt(1, newItem.getOrderNumber());
        statement.setInt(2, newItem.getOrderLineNumber());
        statement.setInt(3, newItem.getQuantityOrdered());
        statement.setDouble(4, newItem.getPriceEach());
        statement.setInt(5, newItem.getOrderLineNumber());
        statement.setString(6, newItem.getProduct().getCode());
        return statement;
    }

    @Override
    public LineItem toItem(ResultSet rs) throws SQLException {
        LineItem item = new LineItem();
        Product product = this.productService.getOne(rs.getString(LineItem.COL_PRODUCT_CODE));

        item.setOrderLineNumber(rs.getInt(LineItem.COL_ORDER_LINE_NUMBER));
        item.setOrderNumber(rs.getInt(LineItem.COL_ORDER_NUMBER));
        item.setQuantityOrdered(rs.getInt(LineItem.COL_QUANTITY_ORDERED));
        item.setPriceEach(rs.getDouble(LineItem.COL_PRICE_EACH));
        item.setProduct(product);
        
        return item;
    }
}
