package app;

/**
 * Created by david_szilagyi on 2017.04.24..
 */

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Aksis", "root", "");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.150.86:3306/Aksis", "CodeSoldiers", "AksiS");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(String username, String email, String role, String pass) {
        String newUser = String.format("INSERT INTO Users(username, email, role, pass) " +
                        "VALUES(\"%s\", \"%s\", \"%s\", \"%s\");", username, email,
                role, sha1(pass));
        try {
            statement.executeUpdate(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeAttr(User user) {
        String check = String.format("SELECT * FROM Users WHERE email=\"%s\";", user.getEmail());
        try {
            resultSet = statement.executeQuery(check);
            if (resultSet.next()) {
                String update = String.format("UPDATE Users SET username=\"%s\",role=\"%s\",pass=\"%s\" WHERE email=\"%s\";",
                        user.getUsername(), user.getRole(), user.getPassword(), user.getEmail());
                statement.executeUpdate(update);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User loginUser(String currEmail, String currPass) {
        String check = String.format("SELECT * FROM Users WHERE email=\"%s\" AND pass=\"%s\";", currEmail, sha1(currPass));
        try {
            resultSet = statement.executeQuery(check);
            if (!resultSet.next()) {
                return null;
            }
            User loggedUser = new User(resultSet.getString("username"), resultSet.getString("email"),
                    resultSet.getString("role"), resultSet.getString("pass"));
            return loggedUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public static boolean checkInputs(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (Character.compare(string.charAt(i), '@') != 0 && Character.compare(string.charAt(i), '.') != 0) {
                if (!Character.isLetterOrDigit(string.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void deleteUser(String currEmail) {
        String check = String.format("SELECT * FROM Users WHERE email=\"%s\" AND pass=\"%s\";", currEmail);
        try {
            resultSet = statement.executeQuery(check);
            if (resultSet.next()) {
                String deleteUser = String.format("DELETE FROM Users WHERE email=\"%s\";", currEmail);
                statement.executeUpdate(deleteUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
