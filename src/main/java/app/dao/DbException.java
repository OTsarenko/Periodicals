package app.dao;

import java.sql.SQLException;

public class DbException extends SQLException {

    public DbException() {
        super();
    }

    public DbException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }

    public DbException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public DbException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public DbException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public DbException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public DbException(String reason) {
        super(reason);
    }

    public DbException(Throwable cause) {
        super(cause);
    }
}
