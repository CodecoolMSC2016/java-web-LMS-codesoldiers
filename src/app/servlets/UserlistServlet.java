package app.servlets;

import app.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: read from SQL
        // TODO: write to webpage
        /*PageWriter pageWriter = new PageWriter();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        out.print(session.getAttribute("user"));
        out.print(pageWriter.getHTMLHead("Users"));
        out.print(pageWriter.getHTMLSidebar(Pages.USERS ,user.getUsername()));
        out.print("<section id='content'>");
        try {
            CSVRW db = new CSVRW("userdatabase.csv");
            List<User> userdb = db.readUserDatabase();
            for (User cUser : userdb) {
                if (user.getRole().equals("mentor") || cUser.getRole().equals("student")) {
                    String cUserRole = cUser.getRole();
                    String cUserEmail = cUser.getEmail();
                    out.print(pageWriter.getCard(cUserRole, cUserEmail, ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print("</section>");*/

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.150.86:3306/Aksis", "CodeSoldiers", "AksiS");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()) {
                System.out.printf("id: %s%n", resultSet.getString("id"));
                System.out.printf("Username: %s%n", resultSet.getString("username"));
                System.out.printf("Email: %s%n", resultSet.getString("email"));
                System.out.printf("Role: %s%n", resultSet.getString("role"));
                System.out.printf("Password: %s%n", resultSet.getString("pass"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(session.getAttribute("user"));


    }
}
