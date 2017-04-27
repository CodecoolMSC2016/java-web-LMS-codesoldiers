package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.sql.*;
import java.util.*;

import static app.DatabaseManager.checkInputs;

public class PageLoader {
    private static PageLoader ourInstance = new PageLoader();
    private Connection connection;
    private Statement statement;

    private PageLoader() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://192.168.150.86:3306/Aksis", "CodeSoldiers", "AksiS");
            statement = connection.createStatement();
        } catch (SQLException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PageLoader getInstance() {
        return ourInstance;
    }

    public List<Page> loadPages() {
        List<Page> pages = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Pages");
            SortedMap<Integer, Page> sm = new TreeMap<>();
            while (resultSet.next()) {
                int pageType = resultSet.getInt("pageType");
                int order = resultSet.getInt("ord");

                int id = resultSet.getInt("id");
                boolean published = resultSet.getBoolean("published");
                String title = resultSet.getString("title");

                switch (pageType) {
                    case 0:  // assignment page
                        String question = resultSet.getString("question");
                        int maxScore = resultSet.getInt("maxScore");
                        // pages.add();
                        sm.put(order, new AssignmentPage(id, published, title, question, maxScore));
                        break;
                    case 1:  // text page
                        String content = resultSet.getString("content");
                        // pages.add();
                        sm.put(order, new TextPage(id, published, title, content));
                        break;
                    default:
                        break;
                }
                pages = new ArrayList<Page>(sm.values());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pages;
    }

    public boolean updateOrder(Map<Integer, Integer> orderList) {
        try {
            for (int key : orderList.keySet()) {
                String q = String.format("UPDATE Pages SET ord = %d WHERE id = %d;", orderList.get(key), key);
                statement.executeUpdate(q);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addAssignmentPage(AssignmentPage page) {
        try {
            if (!checkInputs(page.getTitle()) || checkInputs(page.getQuestion())) {
                throw new SQLException("SQL injection protection");
            }
            String countQ = "SELECT COUNT(*) FROM Pages";
            ResultSet countSet = statement.executeQuery(countQ);
            countSet.next();
            int count = countSet.getInt(1);
            String q = "INSERT INTO Pages(ord, published, title, pagetype, question, maxScore) ";
            q += String.format("VALUES(%d, 0, \"%s\", 0, \"%s\", %d);",
                    count, page.getTitle(), page.getQuestion(), page.getMaxScore());
            statement.executeUpdate(q);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addTextPage(TextPage page) {
        try {
            if (!checkInputs(page.getTitle()) || checkInputs(page.getContent())) {
                throw new SQLException("SQL injection protection");
            }
            String countQ = "SELECT COUNT(*) FROM Pages";
            ResultSet countSet = statement.executeQuery(countQ);
            countSet.next();
            int count = countSet.getInt(1);
            String q = "INSERT INTO Pages(ord, published, title, pagetype, content) ";
            q += String.format("VALUES(%d, 0, \"%s\", 1, \"%s\");",
                    count, page.getTitle(), page.getContent());
            statement.executeUpdate(q);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
