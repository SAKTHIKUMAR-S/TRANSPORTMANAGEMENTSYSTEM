package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBPropertyUtil {
    
    // Static variable to hold the connection object
    private static Connection connection = null;
    
    // Static method to get the database connection
    public static Connection getConnection() {
        // Properties file that holds the connection details
        String dbConfigFile = "dbconfig.properties";
        
        // Load properties and connect to the database
        try {
            // Load the properties file
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(dbConfigFile);
            properties.load(inputStream);
            
            // Get the database connection details from the properties file
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            
            // If connection is not established, create a new one
            if (connection == null) {
                connection = DriverManager.getConnection(url, user, password);
            }
            
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        
        return connection;
    }
}
