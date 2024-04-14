package base;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) {
        // Create a DatabaseConnection object
        DatabaseConnection db = new DatabaseConnection();

        try {
            // Get a connection to the database
            Connection connection = db.getConnection();

            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute a query
            String query = "SELECT * FROM `login`";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                System.out.println("ID: " + id);
                System.out.println("Username: " + name);
                System.out.println("Password: " + password);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

