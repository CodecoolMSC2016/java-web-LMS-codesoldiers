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
        if (dbm.checkInputs(request.getParameter("pass")) && dbm.checkInputs(request.getParameter("email"))
                && dbm.checkInputs(request.getParameter("user"))) {
            dbm.addNewUser(request.getParameter("user"),
                    request.getParameter("email"),
                    request.getParameter("role"),
                    request.getParameter("pass"));
            response.sendRedirect("login?regsuccess");
        } else {
            response.sendRedirect("login?formaterror");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login");
    }
}
