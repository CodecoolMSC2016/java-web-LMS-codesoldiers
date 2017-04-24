package app;

/**
 * Created by david_szilagyi on 2017.04.24..
 */
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    private static ArrayList<User> userList;
    private static DatabaseManager ourInstance = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return ourInstance;
    }

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Aksis", "root", "");
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
    public void addNewUser(User user) {
        String newUser = String.format("INSERT INTO Users(username, email, role, pass) " +
                "VALUES(\"%s\", \"%s\", \"%s\", sha1(\"%s\"));", user.getUsername(), user.getEmail(),
                user.getRole(), user.getPassword());
        try {
            statement.executeUpdate(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkPass(String email, String pass) {
        String tempEmail;
        String tempPass;
        try {
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                tempEmail = resultSet.getString("email");
                tempPass = resultSet.getString("pass");
                if(tempEmail.equals(email) && tempPass.equalsIgnoreCase(pass)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createUserList() {
        userList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString("username"), resultSet.getString("email"),
                        resultSet.getString("role"), resultSet.getString("pass")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String sha1(String input) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }
}
