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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printUsers() {
        try {
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                System.out.printf("id: %s%n", resultSet.getString("id"));
                System.out.printf("Username: %s%n", resultSet.getString("username"));
                System.out.printf("Email: %s%n", resultSet.getString("email"));
                System.out.printf("Role: %s%n", resultSet.getString("role"));
                System.out.printf("Password: %s%n", resultSet.getString("pass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addNewUser(String username, String email, String role, String pass) {
        String newUser = String.format("INSERT INTO Users(username, email, role, pass) " +
                "VALUES(\"%s\", \"%s\", \"%s\", sha1(\"%s\"));", username, email, role, pass);
        System.out.println(newUser);
        try {
            statement.executeUpdate(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}