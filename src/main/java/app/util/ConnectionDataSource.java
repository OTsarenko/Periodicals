package app.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * The class, that represents Connection Pool.
 */
public class ConnectionDataSource {

    final static Logger logger = LogManager.getLogger(ConnectionDataSource.class);
    private static ConnectionDataSource instance;

    /**
     * The constructor is private.
     */
    private ConnectionDataSource() {
    }

    /**
     * Method used to get ConnectionPool instance.
     *
     * @return ConnectionPool instance
     */
    public static ConnectionDataSource getInstance() {
        if (instance == null)
            instance = new ConnectionDataSource();
        return instance;
    }
    /**
     * Method used to get connection from ConnectionPool.
     *
     * @return DB connection
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            DataSource ds = null;
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            conn = ds.getConnection();

        } catch (SQLException | NamingException e) {
            logger.error("Unable to create connection.");
        }
        return conn;
    }
}
