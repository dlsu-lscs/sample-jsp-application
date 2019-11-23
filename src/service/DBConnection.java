package service;

import java.sql.*;

/**
TODO: Change to your environment
**/
public class DBConnection {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String database = "dbsales";
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String password = "--";

    private static Connection connection = null;

    public static Connection getConnection () throws SQLException {

        try {
            Class.forName(driver);
            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection(url + "/" + database, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
