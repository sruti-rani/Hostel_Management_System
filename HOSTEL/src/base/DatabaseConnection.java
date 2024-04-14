package base;
import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set the connection parameters
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String password = "";

            // Create a connection to the database
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

