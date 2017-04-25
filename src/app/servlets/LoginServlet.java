package app.servlets;

import app.DatabaseManager;
import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher login = request.getRequestDispatcher("login.jsp");
        if (dbm.checkInputs(request.getParameter("pass"))) {
            String user = request.getParameter("email");
            String pass = request.getParameter("pass");

            HttpSession session = request.getSession();

            session.removeAttribute("user");
            User logged = logIn(user, pass);
            if (logged != null) {
                session.setAttribute("user", logged);
                response.sendRedirect("curriculum");
            } else {
                request.setAttribute("messageFromServlet", "Username or password incorrect");
                login.forward(request, response);
            }
        } else {
            request.setAttribute("messageFromServlet", "Only alphabetical and digital chars allowed!");
            login.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("login.jsp");
        login.forward(request, response);
    }

    public User logIn(String userLogin, String userPassword) {
        return dbm.loginUser(userLogin, userPassword);
    }
}
