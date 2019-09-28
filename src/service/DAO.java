package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO <T> {
    protected QueryBuilder builder;
    public DAO (QueryBuilder builder) {
        this.builder = builder;
    }

    public abstract List<T> getAll () throws SQLException;
    public abstract T getOne (String id) throws SQLException;
    public abstract boolean update (String id, T updated) throws SQLException;
    public abstract boolean delete (String id) throws SQLException;
    public abstract T create (T newItem) throws SQLException;
    public abstract PreparedStatement injectItemToStatement (T newItem, PreparedStatement statement) throws SQLException;
    public abstract T toItem (ResultSet rs) throws SQLException;
}
