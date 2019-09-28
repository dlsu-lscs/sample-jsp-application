package service;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends DAO <Product> {
    public ProductService (QueryBuilder builder) {
        super(builder);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        builder.reset();
        List <Product> products = new ArrayList<>();
        QueryBuilder query = builder
                    .select("*")
                    .from(Product.TABLE_NAME);

        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Product newProduct = toItem(rs);
            products.add(newProduct);
        }

        return products;
    }

    @Override
    public Product getOne(String id) throws SQLException {
        builder.reset();
        Product product = null;
        QueryBuilder query = builder
                .select("*")
                .from(Product.TABLE_NAME)
                .where(Product.COL_PRODUCT_CODE + " = ?");

        String stringQuery = query.getQuery();

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(stringQuery);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            product = toItem(rs);
        }

        return product;
    }

    @Override
    public boolean update(String id, Product updated) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                                .update(Product.TABLE_NAME)
                                .set(Product.COLUMNS)
                                .where(Product.ID + " = ?");

        PreparedStatement statement = DBConnection
                                        .getConnection()
                                        .prepareStatement(query.getQuery());

        statement = injectItemToStatement(updated, statement);
        statement.setString(10, id);

        return statement.execute();
    }

    @Override
    public boolean delete(String id) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .delete()
                .from(Product.TABLE_NAME)
                .where(Product.COL_PRODUCT_CODE + " = ?");

        System.out.println(query.getQuery());

        PreparedStatement statement = DBConnection
                .getConnection()
                .prepareStatement(query.getQuery());

        statement.setString(1, id);

        return statement.execute();
    }

    @Override
    public Product create(Product newItem) throws SQLException {
        builder.reset();

        QueryBuilder query = builder
                .insert(Product.TABLE_NAME)
                .columns(Product.COLUMNS)
                .values(9);

        String stringQuery = query.getQuery();
        PreparedStatement statement = injectItemToStatement(newItem, DBConnection.getConnection().prepareStatement(stringQuery));

        if (statement.execute())
            return newItem;

        return null;
    }

    @Override
    public PreparedStatement injectItemToStatement(Product product, PreparedStatement statement) throws SQLException {
        statement.setString(1, product.getCode());
        statement.setString(2, product.getDescription());
        statement.setString(3, product.getLine());
        statement.setString(4, product.getName());
        statement.setString(5, product.getScale());
        statement.setString(6, product.getVendor());
        statement.setInt(7, product.getQuantity());
        statement.setDouble(8, product.getBuyPrice());
        statement.setDouble(9, product.getMSRP());
        return statement;
    }

    @Override
    public Product toItem(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setBuyPrice(rs.getDouble(Product.COL_BUY_PRICE));
        product.setCode(rs.getString(Product.COL_PRODUCT_CODE));
        product.setMSRP(rs.getDouble(Product.COL_MSRP));
        product.setDescription(rs.getString(Product.COL_PRODUCT_DESCRIPTION));
        product.setLine(rs.getString(Product.COL_PRODUCT_LINE));
        product.setName(rs.getString(Product.COL_PRODUCT_NAME));
        product.setQuantity(rs.getInt(Product.COL_QUANTITY_STOCK));
        product.setScale(rs.getString(Product.COL_PRODUCT_SCALE));
        product.setVendor(rs.getString(Product.COL_PRODUCT_VENDOR));

        return product;
    }
}
