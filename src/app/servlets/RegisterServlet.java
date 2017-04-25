package app.servlets;

import app.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        if (checkInputs(request.getParameter("pass"))) {
            dbm.addNewUser(request.getParameter("user"),
                    request.getParameter("email"),
                    request.getParameter("role"),
                    request.getParameter("pass"));
        }
        response.sendRedirect("login");
        PrintWriter out = response.getWriter();
        out.println("<p style='margin-left: 250'>Successful registration!</p>");
    }

    public boolean checkInputs(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetterOrDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login");
    }
}
