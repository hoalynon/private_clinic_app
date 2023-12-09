/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Le Xuan
 */
public class DatabaseConnector {
    // Database connection parameters

    // Connection instance
    private static Connection connection;

    // Constructor
    public DatabaseConnector() {
        try {
            // Load the JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Connect to the database
    public void connect(String user) {
    	String DatabaseURL = "";
    	String DatabaseUsername = "";
    	String DatabasePassword = "";
    
    	if (user == "BS") {
    		DatabaseURL = "jdbc:oracle:thin:@localhost:1521/PIMpdb";
    		DatabaseUsername = "bacsi";
    		DatabasePassword = "mypassword";
    	}
    	else if (user == "BN") {
    		DatabaseURL = "jdbc:oracle:thin:@localhost:1521/PIMpdb";
    		DatabaseUsername = "benhnhan";
    		DatabasePassword = "mypassword";
    	}
    	else if (user == "QL") {
    		DatabaseURL = "jdbc:oracle:thin:@localhost:1521/PIMpdb";
    		DatabaseUsername = "quanly";
    		DatabasePassword = "mypassword";
    	}
        try {
            connection = DriverManager.getConnection(DatabaseURL, DatabaseUsername, DatabasePassword);
            connection.setAutoCommit(true);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Disconnect from the database
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the database connection
    public Connection getConnection() {
        return connection;
    }
}