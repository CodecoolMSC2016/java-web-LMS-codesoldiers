package app.servlets;

import app.DatabaseManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher login = request.getRequestDispatcher("login.jsp");
        if (dbm.checkInputs(request.getParameter("pass"))) {
            dbm.addNewUser(request.getParameter("user"),
                    request.getParameter("email"),
                    request.getParameter("role"),
                    request.getParameter("pass"));
            request.setAttribute("messageFromServlet", "User registered successfully!");
            login.forward(request, response);
        } else {
            request.setAttribute("messageFromServlet", "Only letters and numbers are allowed!");
            login.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login");
    }
}
