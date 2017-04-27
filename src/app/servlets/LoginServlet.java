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

public class LoginServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (dbm.checkInputs(request.getParameter("pass")) && dbm.checkInputs(request.getParameter("email")))  {
            String user = request.getParameter("email");
            String pass = request.getParameter("pass");

            HttpSession session = request.getSession();

            session.removeAttribute("user");
            User logged = logIn(user, pass);
            if (logged != null) {
                session.setAttribute("user", logged);
                response.sendRedirect("profile");
            } else {
                response.sendRedirect("login?loginerror");
            }
        } else {
            response.sendRedirect("login?formaterror");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("login.jsp");
        if (request.getParameterMap().containsKey("loginerror")) {
            request.setAttribute("messageFromServlet", "Email or password incorrect");
        } else if (request.getParameterMap().containsKey("formaterror")) {
            request.setAttribute("messageFromServlet", "Only letters and numbers are allowed!");
        }
        login.forward(request, response);
    }

    public User logIn(String userLogin, String userPassword) {
        return dbm.loginUser(userLogin, userPassword);
    }
}
