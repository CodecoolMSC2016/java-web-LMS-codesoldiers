package app;

/**
 * Created by david_szilagyi on 2017.04.24..
 */
import java.sql.*;
public class DatabaseManager {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    private static DatabaseManager ourInstance = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return ourInstance;
    }

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://192.168.150.86:3306/Aksis", "CodeSoldiers", "AksiS");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                System.out.printf("id: %s%n", resultSet.getString("id"));
                System.out.printf("Username: %s%n", resultSet.getString("username"));
                System.out.printf("Email: %s%n", resultSet.getString("email"));
                System.out.printf("Role: %s%n", resultSet.getString("role"));
                System.out.printf("Password: %s%n", resultSet.getString("pass"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
