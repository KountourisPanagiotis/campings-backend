package gr.aueb.cf.cfcampingsjax.service.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBUtil is a utility class that provides methods to manage connections to the database.
 * It uses Apache Commons DBCP (Database Connection Pooling) to manage connections.
 * Retrieves the username and password from environmental variables.
 *
 * @author Kountouris Panagiotis.
 */
public class DBUtil {

    private final static BasicDataSource ds = new BasicDataSource();
    private static Connection conn;

    /**
     * Private constructor to prevent instantiation. No instances of this class should be available
     */
    private DBUtil(){ }

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/campdb?serverTimeZone=UTC");
        ds.setUsername(System.getenv("CAMPDB_USERNAME"));
        ds.setPassword(System.getenv("CAMPDB_PASSWORD"));
        ds.setInitialSize(8);
        ds.setMaxTotal(32);
        ds.setMinIdle(8);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = ds.getConnection();
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
