package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/prototype";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver Not Found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
        }
        return connection;
    }

    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(" Database Connection Closed.");
            } catch (SQLException e) {
                System.err.println(" Error Closing Connection: " + e.getMessage());
            }
        }
    }
}
