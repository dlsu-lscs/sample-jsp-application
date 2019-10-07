package service;

public class QueryBuilder {
    private String query;

    public QueryBuilder () {
        this.query = "";
    }

    public QueryBuilder reset () {
        this.query = "";
        return this;
    }

    public QueryBuilder select (String ... columns) {
        this.query += "SELECT ";

        if (columns.length == 0)
            this.query = "* ";

        for (int i = 0; i < columns.length; i++) {
            this.query += columns[i];

            if (i != columns.length - 1) {
                this.query += ", ";
            }
        }

        this.query += " ";

        return this;
    }


    public QueryBuilder delete () {
        this.query += "DELETE ";
        return this;
    }

    public QueryBuilder from (String table) {
        this.query += "FROM " + table + " ";
        return this;
    }

    public QueryBuilder insert (String table) {
        this.query = "INSERT INTO " + table + " ";
        return this;
    }

    public QueryBuilder values (int n) {
        this.query += "VALUES ";
        String [] columns = new String [n];

        for (int i = 0; i < n; i++)
            columns[i] = "?";

        return getCombined(true, columns);
    }

    public QueryBuilder columns (String ... values) {
        return getCombined(true, values);
    }

    private QueryBuilder getCombined(boolean withParenthesis, String ...values) {
        if (withParenthesis)
            this.query += "(";

        for (int i = 0; i < values.length; i++) {
            this.query += values[i];

            if (i != values.length - 1) {
                this.query += ", ";
            }
        }

        if (withParenthesis)
            this.query += ")";

        this.query += " ";

        return this;
    }


    public QueryBuilder where(String ... whereConds) {
        this.query += "WHERE ";
        for (String whereCond: whereConds) {
            this.query += whereCond;
        }

        this.query += " ";

        return this;
    }

    public String getQuery() {
        return query;
    }

    public QueryBuilder update(String tableName) {
        this.query += "UPDATE " + tableName + " ";
        return this;
    }

    public QueryBuilder set(String ... updatedPairs) {
        this.query += "SET ";

        for (int i = 0; i < updatedPairs.length; i++) {
            updatedPairs[i] += " = ?";
        }

        return this.getCombined(false, updatedPairs);
    }

    public QueryBuilder orderBy(String colProductCode, boolean desc) {
        this.query += "ORDER BY " + colProductCode + " ";
        this.query += desc ? "DESC " : "";
        return this;
    }
}
