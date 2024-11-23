package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tms";
    private static final String USER = "root";
    private static final String PASSWORD = "Sakthi@4156";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection failed", e);
        }
    }
}
