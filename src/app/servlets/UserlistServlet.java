package app.servlets;

import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!LoginHelper.checkLogin(request.getSession())) {
            response.sendRedirect("/login?loginerror");
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.150.86:3306/Aksis", "CodeSoldiers", "AksiS");
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            if (role.equals("mentor")) {
                resultSet = statement.executeQuery("SELECT * FROM Users");
            }
            else if (role.equals("student")) {
                resultSet = statement.executeQuery("SELECT * FROM Users WHERE role='student'");
            }

            List users = new ArrayList<String[]>();

            while(resultSet.next()) {
                String[] userDatas = new String[]{
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                };
                users.add(userDatas);
            }

            request.setAttribute("userlist", users.toArray());

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("userlist.jsp");
            requestDispatcher.forward(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
