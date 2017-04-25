package app.servlets;

import app.User;

import javax.servlet.RequestDispatcher;
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

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("email");
        String pass = request.getParameter("pass");

        RequestDispatcher login = request.getRequestDispatcher("login.html");
        HttpSession session = request.getSession();

        session.removeAttribute("user");
        User logged = logIn(user, pass);
        if (logged != null) {
            session.setAttribute("user", logged);
            response.sendRedirect("curriculum");
        } else {
            out.println("<p style='margin-left: 250'>Username or password incorrect</p>");
            login.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("login.html");
        login.forward(request, response);
    }

    public User logIn(String userlogin, String userPassword) {
            // TODO: implement SQL login
            //Simulated login(non-real user):
            //student:
            //return new User("TamasMacska", "tomcat@tomcat.com", "student", "asd");
            //mentor:
            return new User("TamasMacska", "tomcat@tomcat.com", "mentor", "asd");
    }
}
